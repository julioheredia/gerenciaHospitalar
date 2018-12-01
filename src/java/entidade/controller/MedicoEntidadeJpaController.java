/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.EquipeEntidade;
import entidade.MedicoEntidade;
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
public class MedicoEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(MedicoEntidade medicoEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipeEntidade equipe = medicoEntidade.getEquipe();
            if (equipe != null) {
                equipe = em.getReference(equipe.getClass(), equipe.getId());
                medicoEntidade.setEquipe(equipe);
            }
            em.persist(medicoEntidade);
            if (equipe != null) {
                equipe.getProfissionalList().add(medicoEntidade);
                equipe = em.merge(equipe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicoEntidade medicoEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicoEntidade persistentMedicoEntidade = em.find(MedicoEntidade.class, medicoEntidade.getId());
            EquipeEntidade equipeOld = persistentMedicoEntidade.getEquipe();
            EquipeEntidade equipeNew = medicoEntidade.getEquipe();
            if (equipeNew != null) {
                equipeNew = em.getReference(equipeNew.getClass(), equipeNew.getId());
                medicoEntidade.setEquipe(equipeNew);
            }
            medicoEntidade = em.merge(medicoEntidade);
            if (equipeOld != null && !equipeOld.equals(equipeNew)) {
                equipeOld.getProfissionalList().remove(medicoEntidade);
                equipeOld = em.merge(equipeOld);
            }
            if (equipeNew != null && !equipeNew.equals(equipeOld)) {
                equipeNew.getProfissionalList().add(medicoEntidade);
                equipeNew = em.merge(equipeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicoEntidade.getId();
                if (findMedicoEntidade(id) == null) {
                    throw new NonexistentEntityException("The medicoEntidade with id " + id + " no longer exists.");
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
            MedicoEntidade medicoEntidade;
            try {
                medicoEntidade = em.getReference(MedicoEntidade.class, id);
                medicoEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicoEntidade with id " + id + " no longer exists.", enfe);
            }
            EquipeEntidade equipe = medicoEntidade.getEquipe();
            if (equipe != null) {
                equipe.getProfissionalList().remove(medicoEntidade);
                equipe = em.merge(equipe);
            }
            em.remove(medicoEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicoEntidade> findMedicoEntidadeEntities() {
        return findMedicoEntidadeEntities(true, -1, -1);
    }

    public List<MedicoEntidade> findMedicoEntidadeEntities(int maxResults, int firstResult) {
        return findMedicoEntidadeEntities(false, maxResults, firstResult);
    }

    private List<MedicoEntidade> findMedicoEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicoEntidade.class));
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

    public MedicoEntidade findMedicoEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicoEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicoEntidade> rt = cq.from(MedicoEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
