/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.MedicoCandidatoEntidade;
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
public class MedicoCandidatoEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(MedicoCandidatoEntidade medicoCandidatoEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medicoCandidatoEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicoCandidatoEntidade medicoCandidatoEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medicoCandidatoEntidade = em.merge(medicoCandidatoEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicoCandidatoEntidade.getId();
                if (findMedicoCandidatoEntidade(id) == null) {
                    throw new NonexistentEntityException("The medicoCandidatoEntidade with id " + id + " no longer exists.");
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
            MedicoCandidatoEntidade medicoCandidatoEntidade;
            try {
                medicoCandidatoEntidade = em.getReference(MedicoCandidatoEntidade.class, id);
                medicoCandidatoEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicoCandidatoEntidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(medicoCandidatoEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicoCandidatoEntidade> findMedicoCandidatoEntidadeEntities() {
        return findMedicoCandidatoEntidadeEntities(true, -1, -1);
    }

    public List<MedicoCandidatoEntidade> findMedicoCandidatoEntidadeEntities(int maxResults, int firstResult) {
        return findMedicoCandidatoEntidadeEntities(false, maxResults, firstResult);
    }

    private List<MedicoCandidatoEntidade> findMedicoCandidatoEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicoCandidatoEntidade.class));
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

    public MedicoCandidatoEntidade findMedicoCandidatoEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicoCandidatoEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoCandidatoEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicoCandidatoEntidade> rt = cq.from(MedicoCandidatoEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
