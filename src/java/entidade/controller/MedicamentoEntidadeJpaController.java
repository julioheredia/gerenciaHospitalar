/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.MedicamentoEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.controller.exceptions.PreexistingEntityException;
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
public class MedicamentoEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(MedicamentoEntidade medicamentoEntidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medicamentoEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicamentoEntidade(medicamentoEntidade.getCodigo()) != null) {
                throw new PreexistingEntityException("MedicamentoEntidade " + medicamentoEntidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicamentoEntidade medicamentoEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medicamentoEntidade = em.merge(medicamentoEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicamentoEntidade.getCodigo();
                if (findMedicamentoEntidade(id) == null) {
                    throw new NonexistentEntityException("The medicamentoEntidade with id " + id + " no longer exists.");
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
            MedicamentoEntidade medicamentoEntidade;
            try {
                medicamentoEntidade = em.getReference(MedicamentoEntidade.class, id);
                medicamentoEntidade.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamentoEntidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(medicamentoEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicamentoEntidade> findMedicamentoEntidadeEntities() {
        return findMedicamentoEntidadeEntities(true, -1, -1);
    }

    public List<MedicamentoEntidade> findMedicamentoEntidadeEntities(int maxResults, int firstResult) {
        return findMedicamentoEntidadeEntities(false, maxResults, firstResult);
    }

    private List<MedicamentoEntidade> findMedicamentoEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicamentoEntidade.class));
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

    public MedicamentoEntidade findMedicamentoEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicamentoEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentoEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicamentoEntidade> rt = cq.from(MedicamentoEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
