/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.ClinicaEntidade;
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
public class ClinicaEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(ClinicaEntidade clinicaEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clinicaEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClinicaEntidade clinicaEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clinicaEntidade = em.merge(clinicaEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clinicaEntidade.getId();
                if (findClinicaEntidade(id) == null) {
                    throw new NonexistentEntityException("The clinicaEntidade with id " + id + " no longer exists.");
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
            ClinicaEntidade clinicaEntidade;
            try {
                clinicaEntidade = em.getReference(ClinicaEntidade.class, id);
                clinicaEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clinicaEntidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(clinicaEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClinicaEntidade> findClinicaEntidadeEntities() {
        return findClinicaEntidadeEntities(true, -1, -1);
    }

    public List<ClinicaEntidade> findClinicaEntidadeEntities(int maxResults, int firstResult) {
        return findClinicaEntidadeEntities(false, maxResults, firstResult);
    }

    private List<ClinicaEntidade> findClinicaEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClinicaEntidade.class));
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

    public ClinicaEntidade findClinicaEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClinicaEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getClinicaEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClinicaEntidade> rt = cq.from(ClinicaEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
