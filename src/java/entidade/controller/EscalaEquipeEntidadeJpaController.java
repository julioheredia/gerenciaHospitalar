/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidade.EquipeEntidade;
import entidade.EscalaEquipeEntidade;
import entidade.controller.exceptions.IllegalOrphanException;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.mysql.JpaMySqlConector;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author julio
 */
public class EscalaEquipeEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(EscalaEquipeEntidade escalaEquipeEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipeEntidade equipe = escalaEquipeEntidade.getEquipe();
            if (equipe != null) {
                equipe = em.getReference(equipe.getClass(), equipe.getId());
                escalaEquipeEntidade.setEquipe(equipe);
            }
            em.persist(escalaEquipeEntidade);
            if (equipe != null) {
                EscalaEquipeEntidade oldEscalaOfEquipe = equipe.getEscala();
                if (oldEscalaOfEquipe != null) {
                    oldEscalaOfEquipe.setEquipe(null);
                    oldEscalaOfEquipe = em.merge(oldEscalaOfEquipe);
                }
                equipe.setEscala(escalaEquipeEntidade);
                equipe = em.merge(equipe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EscalaEquipeEntidade escalaEquipeEntidade) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EscalaEquipeEntidade persistentEscalaEquipeEntidade = em.find(EscalaEquipeEntidade.class, escalaEquipeEntidade.getId());
            EquipeEntidade equipeOld = persistentEscalaEquipeEntidade.getEquipe();
            EquipeEntidade equipeNew = escalaEquipeEntidade.getEquipe();
            List<String> illegalOrphanMessages = null;
            if (equipeOld != null && !equipeOld.equals(equipeNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain EquipeEntidade " + equipeOld + " since its escala field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (equipeNew != null) {
                equipeNew = em.getReference(equipeNew.getClass(), equipeNew.getId());
                escalaEquipeEntidade.setEquipe(equipeNew);
            }
            escalaEquipeEntidade = em.merge(escalaEquipeEntidade);
            if (equipeNew != null && !equipeNew.equals(equipeOld)) {
                EscalaEquipeEntidade oldEscalaOfEquipe = equipeNew.getEscala();
                if (oldEscalaOfEquipe != null) {
                    oldEscalaOfEquipe.setEquipe(null);
                    oldEscalaOfEquipe = em.merge(oldEscalaOfEquipe);
                }
                equipeNew.setEscala(escalaEquipeEntidade);
                equipeNew = em.merge(equipeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = escalaEquipeEntidade.getId();
                if (findEscalaEquipeEntidade(id) == null) {
                    throw new NonexistentEntityException("The escalaEquipeEntidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EscalaEquipeEntidade escalaEquipeEntidade;
            try {
                escalaEquipeEntidade = em.getReference(EscalaEquipeEntidade.class, id);
                escalaEquipeEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The escalaEquipeEntidade with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            EquipeEntidade equipeOrphanCheck = escalaEquipeEntidade.getEquipe();
            if (equipeOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EscalaEquipeEntidade (" + escalaEquipeEntidade + ") cannot be destroyed since the EquipeEntidade " + equipeOrphanCheck + " in its equipe field has a non-nullable escala field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(escalaEquipeEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EscalaEquipeEntidade> findEscalaEquipeEntidadeEntities() {
        return findEscalaEquipeEntidadeEntities(true, -1, -1);
    }

    public List<EscalaEquipeEntidade> findEscalaEquipeEntidadeEntities(int maxResults, int firstResult) {
        return findEscalaEquipeEntidadeEntities(false, maxResults, firstResult);
    }

    private List<EscalaEquipeEntidade> findEscalaEquipeEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EscalaEquipeEntidade.class));
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

    public EscalaEquipeEntidade findEscalaEquipeEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EscalaEquipeEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getEscalaEquipeEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EscalaEquipeEntidade> rt = cq.from(EscalaEquipeEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
