/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.EquipeEntidade;
import entidade.FarmaceuticoEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.mysql.JpaMySqlConector;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author julio
 */
public class FarmaceuticoEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(FarmaceuticoEntidade farmaceuticoEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipeEntidade equipe = farmaceuticoEntidade.getEquipe();
            if (equipe != null) {
                equipe = em.getReference(equipe.getClass(), equipe.getId());
                farmaceuticoEntidade.setEquipe(equipe);
            }
            em.persist(farmaceuticoEntidade);
            if (equipe != null) {
                equipe.getProfissionalList().add(farmaceuticoEntidade);
                equipe = em.merge(equipe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FarmaceuticoEntidade farmaceuticoEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FarmaceuticoEntidade persistentFarmaceuticoEntidade = em.find(FarmaceuticoEntidade.class, farmaceuticoEntidade.getId());
            EquipeEntidade equipeOld = persistentFarmaceuticoEntidade.getEquipe();
            EquipeEntidade equipeNew = farmaceuticoEntidade.getEquipe();
            if (equipeNew != null) {
                equipeNew = em.getReference(equipeNew.getClass(), equipeNew.getId());
                farmaceuticoEntidade.setEquipe(equipeNew);
            }
            farmaceuticoEntidade = em.merge(farmaceuticoEntidade);
            if (equipeOld != null && !equipeOld.equals(equipeNew)) {
                equipeOld.getProfissionalList().remove(farmaceuticoEntidade);
                equipeOld = em.merge(equipeOld);
            }
            if (equipeNew != null && !equipeNew.equals(equipeOld)) {
                equipeNew.getProfissionalList().add(farmaceuticoEntidade);
                equipeNew = em.merge(equipeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = farmaceuticoEntidade.getId();
                if (findFarmaceuticoEntidade(id) == null) {
                    throw new NonexistentEntityException("The farmaceuticoEntidade with id " + id + " no longer exists.");
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
            FarmaceuticoEntidade farmaceuticoEntidade;
            try {
                farmaceuticoEntidade = em.getReference(FarmaceuticoEntidade.class, id);
                farmaceuticoEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The farmaceuticoEntidade with id " + id + " no longer exists.", enfe);
            }
            EquipeEntidade equipe = farmaceuticoEntidade.getEquipe();
            if (equipe != null) {
                equipe.getProfissionalList().remove(farmaceuticoEntidade);
                equipe = em.merge(equipe);
            }
            em.remove(farmaceuticoEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FarmaceuticoEntidade> findFarmaceuticoEntidadeEntities() {
        return findFarmaceuticoEntidadeEntities(true, -1, -1);
    }

    public List<FarmaceuticoEntidade> findFarmaceuticoEntidadeEntities(int maxResults, int firstResult) {
        return findFarmaceuticoEntidadeEntities(false, maxResults, firstResult);
    }

    private List<FarmaceuticoEntidade> findFarmaceuticoEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FarmaceuticoEntidade.class));
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

    public FarmaceuticoEntidade findFarmaceuticoEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FarmaceuticoEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getFarmaceuticoEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FarmaceuticoEntidade> rt = cq.from(FarmaceuticoEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
