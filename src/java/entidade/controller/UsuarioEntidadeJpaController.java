/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.UsuarioEntidade;
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
public class UsuarioEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public UsuarioEntidade login(String login, String senha) {

        EntityManager em = null;
        UsuarioEntidade usuario = null;
        try {
            em = getEntityManager();
            Query q = em.createQuery("SELECT u FROM UsuarioEntidade u WHERE u.login = '" + login
                    + "' AND u.senha = '" + senha + "'");
            List<UsuarioEntidade> usuarioList = q.getResultList();
            if (usuarioList.size() > 0) {
                for (UsuarioEntidade usuarioEntidade : usuarioList) {
                    usuario = usuarioEntidade;
                }
                return usuario;
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public void create(UsuarioEntidade usuarioEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarioEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioEntidade usuarioEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarioEntidade = em.merge(usuarioEntidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarioEntidade.getId();
                if (findUsuarioEntidade(id) == null) {
                    throw new NonexistentEntityException("The usuarioEntidade with id " + id + " no longer exists.");
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
            UsuarioEntidade usuarioEntidade;
            try {
                usuarioEntidade = em.getReference(UsuarioEntidade.class, id);
                usuarioEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioEntidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarioEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioEntidade> findUsuarioEntidadeEntities() {
        return findUsuarioEntidadeEntities(true, -1, -1);
    }

    public List<UsuarioEntidade> findUsuarioEntidadeEntities(int maxResults, int firstResult) {
        return findUsuarioEntidadeEntities(false, maxResults, firstResult);
    }

    private List<UsuarioEntidade> findUsuarioEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioEntidade.class));
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

    public UsuarioEntidade findUsuarioEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioEntidade> rt = cq.from(UsuarioEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
