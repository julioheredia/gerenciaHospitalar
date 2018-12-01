/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.EquipeEntidade;
import entidade.ProfissionalEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.mysql.JpaMySqlConector;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author julio
 */
public class ProfissionalEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(ProfissionalEntidade profissionalEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipeEntidade equipe = profissionalEntidade.getEquipe();
            if (equipe != null) {
                equipe = em.getReference(equipe.getClass(), equipe.getId());
                profissionalEntidade.setEquipe(equipe);
            }
            em.persist(profissionalEntidade);
            if (equipe != null) {
                equipe.getProfissionalList().add(profissionalEntidade);
                equipe = em.merge(equipe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProfissionalEntidade profissionalEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProfissionalEntidade persistentProfissionalEntidade = em.find(ProfissionalEntidade.class, profissionalEntidade.getId());
            EquipeEntidade equipeOld = persistentProfissionalEntidade.getEquipe();
            EquipeEntidade equipeNew = profissionalEntidade.getEquipe();
            if (equipeNew != null) {
                equipeNew = em.getReference(equipeNew.getClass(), equipeNew.getId());
                profissionalEntidade.setEquipe(equipeNew);
            }
            profissionalEntidade = em.merge(profissionalEntidade);
            if (equipeOld != null && !equipeOld.equals(equipeNew)) {
                equipeOld.getProfissionalList().remove(profissionalEntidade);
                equipeOld = em.merge(equipeOld);
            }
            if (equipeNew != null && !equipeNew.equals(equipeOld)) {
                equipeNew.getProfissionalList().add(profissionalEntidade);
                equipeNew = em.merge(equipeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profissionalEntidade.getId();
                if (findProfissionalEntidade(id) == null) {
                    throw new NonexistentEntityException("The profissionalEntidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProfissionalEntidade profissionalEntidade;
            try {
                profissionalEntidade = em.getReference(ProfissionalEntidade.class, id);
                profissionalEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profissionalEntidade with id " + id + " no longer exists.", enfe);
            }
            EquipeEntidade equipe = profissionalEntidade.getEquipe();
            if (equipe != null) {
                equipe.getProfissionalList().remove(profissionalEntidade);
                equipe = em.merge(equipe);
            }
            em.remove(profissionalEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProfissionalEntidade> findProfissionalEntidadeEntities() {
        return findProfissionalEntidadeEntities(true, -1, -1);
    }

    public List<ProfissionalEntidade> findProfissionalEntidadeEntities(int maxResults, int firstResult) {
        return findProfissionalEntidadeEntities(false, maxResults, firstResult);
    }

    private List<ProfissionalEntidade> findProfissionalEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ProfissionalEntidade> p = cq.from(ProfissionalEntidade.class);
            cq.select(p);
            cq.orderBy(cb.asc(p.get("nome")));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProfissionalEntidade findProfissionalEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProfissionalEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfissionalEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProfissionalEntidade> rt = cq.from(ProfissionalEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<ProfissionalEntidade> searchProfessional(String nome,
            Boolean searchNome, String type, Boolean searchType) {

        String search = null;
        if (searchNome && !searchType) {
            search = "p.nome LIKE '%" + nome + "%'";
        }
        if (!searchNome && searchType) {
            search = "p.discriminator = '" + type + "'";
        }
        if (searchNome && searchType) {
            search = "p.nome LIKE '%" + nome + "%' AND p.discriminator = '" + type + "'";
        }

        EntityManager em = getEntityManager();
        try {
            Query q = null;
            try {
                q = em.createQuery("SELECT p FROM ProfissionalEntidade p WHERE " + search +"order by p.nome");
            } catch (Exception e) {
                System.out.println(e);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
