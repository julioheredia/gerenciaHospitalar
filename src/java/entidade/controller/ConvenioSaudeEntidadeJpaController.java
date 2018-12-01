/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.ConvenioSaudeEntidade;
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
public class ConvenioSaudeEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(ConvenioSaudeEntidade convenioSaudeEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(convenioSaudeEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ConvenioSaudeEntidade convenioSaudeEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            convenioSaudeEntidade = em.merge(convenioSaudeEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = convenioSaudeEntidade.getId();
                if (findConvenioSaudeEntidade(id) == null) {
                    throw new NonexistentEntityException("The convenioSaudeEntidade with id " + id + " no longer exists.");
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
            ConvenioSaudeEntidade convenioSaudeEntidade;
            try {
                convenioSaudeEntidade = em.getReference(ConvenioSaudeEntidade.class, id);
                convenioSaudeEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The convenioSaudeEntidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(convenioSaudeEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ConvenioSaudeEntidade> findConvenioSaudeEntidadeEntities() {
        return findConvenioSaudeEntidadeEntities(true, -1, -1);
    }

    public List<ConvenioSaudeEntidade> findConvenioSaudeEntidadeEntities(int maxResults, int firstResult) {
        return findConvenioSaudeEntidadeEntities(false, maxResults, firstResult);
    }

    private List<ConvenioSaudeEntidade> findConvenioSaudeEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ConvenioSaudeEntidade.class));
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

    public ConvenioSaudeEntidade findConvenioSaudeEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ConvenioSaudeEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getConvenioSaudeEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ConvenioSaudeEntidade> rt = cq.from(ConvenioSaudeEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
