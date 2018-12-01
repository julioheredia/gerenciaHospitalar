/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.PatrimonioEntidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidade.SetorEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.controller.exceptions.PreexistingEntityException;
import entidade.mysql.JpaMySqlConector;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author julio
 */
public class PatrimonioEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(PatrimonioEntidade patrimonioEntidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SetorEntidade setor = patrimonioEntidade.getSetor();
            if (setor != null) {
                setor = em.getReference(setor.getClass(), setor.getId());
                patrimonioEntidade.setSetor(setor);
            }
            em.persist(patrimonioEntidade);
            if (setor != null) {
                setor.getPatrimonioList().add(patrimonioEntidade);
                setor = em.merge(setor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPatrimonioEntidade(patrimonioEntidade.getId()) != null) {
                throw new PreexistingEntityException("PatrimonioEntidade " + patrimonioEntidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PatrimonioEntidade patrimonioEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PatrimonioEntidade persistentPatrimonioEntidade = em.find(PatrimonioEntidade.class, patrimonioEntidade.getId());
            SetorEntidade setorOld = persistentPatrimonioEntidade.getSetor();
            SetorEntidade setorNew = patrimonioEntidade.getSetor();
            if (setorNew != null) {
                setorNew = em.getReference(setorNew.getClass(), setorNew.getId());
                patrimonioEntidade.setSetor(setorNew);
            }
            patrimonioEntidade = em.merge(patrimonioEntidade);
            if (setorOld != null && !setorOld.equals(setorNew)) {
                setorOld.getPatrimonioList().remove(patrimonioEntidade);
                setorOld = em.merge(setorOld);
            }
            if (setorNew != null && !setorNew.equals(setorOld)) {
                setorNew.getPatrimonioList().add(patrimonioEntidade);
                setorNew = em.merge(setorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = patrimonioEntidade.getId();
                if (findPatrimonioEntidade(id) == null) {
                    throw new NonexistentEntityException("The patrimonioEntidade with id " + id + " no longer exists.");
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
            PatrimonioEntidade patrimonioEntidade;
            try {
                patrimonioEntidade = em.getReference(PatrimonioEntidade.class, id);
                patrimonioEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The patrimonioEntidade with id " + id + " no longer exists.", enfe);
            }
            SetorEntidade setor = patrimonioEntidade.getSetor();
            if (setor != null) {
                setor.getPatrimonioList().remove(patrimonioEntidade);
                setor = em.merge(setor);
            }
            em.remove(patrimonioEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PatrimonioEntidade> findPatrimonioEntidadeEntities() {
        return findPatrimonioEntidadeEntities(true, -1, -1);
    }

    public List<PatrimonioEntidade> findPatrimonioEntidadeEntities(int maxResults, int firstResult) {
        return findPatrimonioEntidadeEntities(false, maxResults, firstResult);
    }

    private List<PatrimonioEntidade> findPatrimonioEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PatrimonioEntidade.class));
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

    public PatrimonioEntidade findPatrimonioEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PatrimonioEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getPatrimonioEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PatrimonioEntidade> rt = cq.from(PatrimonioEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
