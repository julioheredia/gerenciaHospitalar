/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;

import entidade.EnfermeiroEntidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidade.SetorEntidade;
import entidade.EquipeEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.mysql.JpaMySqlConector;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author julio
 */
public class EnfermeiroEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(EnfermeiroEntidade enfermeiroEntidade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SetorEntidade setor = enfermeiroEntidade.getSetor();
            if (setor != null) {
                setor = em.getReference(setor.getClass(), setor.getId());
                enfermeiroEntidade.setSetor(setor);
            }
            EquipeEntidade equipe = enfermeiroEntidade.getEquipe();
            if (equipe != null) {
                equipe = em.getReference(equipe.getClass(), equipe.getId());
                enfermeiroEntidade.setEquipe(equipe);
            }
            em.persist(enfermeiroEntidade);
            if (setor != null) {
                setor.getEnfermeiroList().add(enfermeiroEntidade);
                setor = em.merge(setor);
            }
            if (equipe != null) {
                equipe.getProfissionalList().add(enfermeiroEntidade);
                equipe = em.merge(equipe);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EnfermeiroEntidade enfermeiroEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EnfermeiroEntidade persistentEnfermeiroEntidade = em.find(EnfermeiroEntidade.class, enfermeiroEntidade.getId());
            SetorEntidade setorOld = persistentEnfermeiroEntidade.getSetor();
            SetorEntidade setorNew = enfermeiroEntidade.getSetor();
            EquipeEntidade equipeOld = persistentEnfermeiroEntidade.getEquipe();
            EquipeEntidade equipeNew = enfermeiroEntidade.getEquipe();
            if (setorNew != null) {
                setorNew = em.getReference(setorNew.getClass(), setorNew.getId());
                enfermeiroEntidade.setSetor(setorNew);
            }
            if (equipeNew != null) {
                equipeNew = em.getReference(equipeNew.getClass(), equipeNew.getId());
                enfermeiroEntidade.setEquipe(equipeNew);
            }
            enfermeiroEntidade = em.merge(enfermeiroEntidade);
            if (setorOld != null && !setorOld.equals(setorNew)) {
                setorOld.getEnfermeiroList().remove(enfermeiroEntidade);
                setorOld = em.merge(setorOld);
            }
            if (setorNew != null && !setorNew.equals(setorOld)) {
                setorNew.getEnfermeiroList().add(enfermeiroEntidade);
                setorNew = em.merge(setorNew);
            }
            if (equipeOld != null && !equipeOld.equals(equipeNew)) {
                equipeOld.getProfissionalList().remove(enfermeiroEntidade);
                equipeOld = em.merge(equipeOld);
            }
            if (equipeNew != null && !equipeNew.equals(equipeOld)) {
                equipeNew.getProfissionalList().add(enfermeiroEntidade);
                equipeNew = em.merge(equipeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enfermeiroEntidade.getId();
                if (findEnfermeiroEntidade(id) == null) {
                    throw new NonexistentEntityException("The enfermeiroEntidade with id " + id + " no longer exists.");
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
            EnfermeiroEntidade enfermeiroEntidade;
            try {
                enfermeiroEntidade = em.getReference(EnfermeiroEntidade.class, id);
                enfermeiroEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfermeiroEntidade with id " + id + " no longer exists.", enfe);
            }
            SetorEntidade setor = enfermeiroEntidade.getSetor();
            if (setor != null) {
                setor.getEnfermeiroList().remove(enfermeiroEntidade);
                setor = em.merge(setor);
            }
            EquipeEntidade equipe = enfermeiroEntidade.getEquipe();
            if (equipe != null) {
                equipe.getProfissionalList().remove(enfermeiroEntidade);
                equipe = em.merge(equipe);
            }
            em.remove(enfermeiroEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EnfermeiroEntidade> findEnfermeiroEntidadeEntities() {
        return findEnfermeiroEntidadeEntities(true, -1, -1);
    }

    public List<EnfermeiroEntidade> findEnfermeiroEntidadeEntities(int maxResults, int firstResult) {
        return findEnfermeiroEntidadeEntities(false, maxResults, firstResult);
    }

    private List<EnfermeiroEntidade> findEnfermeiroEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EnfermeiroEntidade.class));
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

    public EnfermeiroEntidade findEnfermeiroEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EnfermeiroEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfermeiroEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EnfermeiroEntidade> rt = cq.from(EnfermeiroEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
