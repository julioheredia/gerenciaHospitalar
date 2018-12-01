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
import entidade.EnfermeiroEntidade;
import java.util.ArrayList;
import java.util.List;
import entidade.PatrimonioEntidade;
import entidade.SetorEntidade;
import entidade.controller.exceptions.NonexistentEntityException;
import entidade.mysql.JpaMySqlConector;
import javax.persistence.EntityManager;

/**
 *
 * @author julio
 */
public class SetorEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }

    public void create(SetorEntidade setorEntidade) {
        if (setorEntidade.getEnfermeiroList() == null) {
            setorEntidade.setEnfermeiroList(new ArrayList<EnfermeiroEntidade>());
        }
        if (setorEntidade.getPatrimonioList() == null) {
            setorEntidade.setPatrimonioList(new ArrayList<PatrimonioEntidade>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EnfermeiroEntidade> attachedEnfermeiroList = new ArrayList<EnfermeiroEntidade>();
            for (EnfermeiroEntidade enfermeiroListEnfermeiroEntidadeToAttach : setorEntidade.getEnfermeiroList()) {
                enfermeiroListEnfermeiroEntidadeToAttach = em.getReference(enfermeiroListEnfermeiroEntidadeToAttach.getClass(), enfermeiroListEnfermeiroEntidadeToAttach.getId());
                attachedEnfermeiroList.add(enfermeiroListEnfermeiroEntidadeToAttach);
            }
            setorEntidade.setEnfermeiroList(attachedEnfermeiroList);
            List<PatrimonioEntidade> attachedPatrimonioList = new ArrayList<PatrimonioEntidade>();
            for (PatrimonioEntidade patrimonioListPatrimonioEntidadeToAttach : setorEntidade.getPatrimonioList()) {
                patrimonioListPatrimonioEntidadeToAttach = em.getReference(patrimonioListPatrimonioEntidadeToAttach.getClass(), patrimonioListPatrimonioEntidadeToAttach.getId());
                attachedPatrimonioList.add(patrimonioListPatrimonioEntidadeToAttach);
            }
            setorEntidade.setPatrimonioList(attachedPatrimonioList);
            em.persist(setorEntidade);
            for (EnfermeiroEntidade enfermeiroListEnfermeiroEntidade : setorEntidade.getEnfermeiroList()) {
                SetorEntidade oldSetorOfEnfermeiroListEnfermeiroEntidade = enfermeiroListEnfermeiroEntidade.getSetor();
                enfermeiroListEnfermeiroEntidade.setSetor(setorEntidade);
                enfermeiroListEnfermeiroEntidade = em.merge(enfermeiroListEnfermeiroEntidade);
                if (oldSetorOfEnfermeiroListEnfermeiroEntidade != null) {
                    oldSetorOfEnfermeiroListEnfermeiroEntidade.getEnfermeiroList().remove(enfermeiroListEnfermeiroEntidade);
                    oldSetorOfEnfermeiroListEnfermeiroEntidade = em.merge(oldSetorOfEnfermeiroListEnfermeiroEntidade);
                }
            }
            for (PatrimonioEntidade patrimonioListPatrimonioEntidade : setorEntidade.getPatrimonioList()) {
                SetorEntidade oldSetorOfPatrimonioListPatrimonioEntidade = patrimonioListPatrimonioEntidade.getSetor();
                patrimonioListPatrimonioEntidade.setSetor(setorEntidade);
                patrimonioListPatrimonioEntidade = em.merge(patrimonioListPatrimonioEntidade);
                if (oldSetorOfPatrimonioListPatrimonioEntidade != null) {
                    oldSetorOfPatrimonioListPatrimonioEntidade.getPatrimonioList().remove(patrimonioListPatrimonioEntidade);
                    oldSetorOfPatrimonioListPatrimonioEntidade = em.merge(oldSetorOfPatrimonioListPatrimonioEntidade);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SetorEntidade setorEntidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SetorEntidade persistentSetorEntidade = em.find(SetorEntidade.class, setorEntidade.getId());
            List<EnfermeiroEntidade> enfermeiroListOld = persistentSetorEntidade.getEnfermeiroList();
            List<EnfermeiroEntidade> enfermeiroListNew = setorEntidade.getEnfermeiroList();
            List<PatrimonioEntidade> patrimonioListOld = persistentSetorEntidade.getPatrimonioList();
            List<PatrimonioEntidade> patrimonioListNew = setorEntidade.getPatrimonioList();
            List<EnfermeiroEntidade> attachedEnfermeiroListNew = new ArrayList<EnfermeiroEntidade>();
            for (EnfermeiroEntidade enfermeiroListNewEnfermeiroEntidadeToAttach : enfermeiroListNew) {
                enfermeiroListNewEnfermeiroEntidadeToAttach = em.getReference(enfermeiroListNewEnfermeiroEntidadeToAttach.getClass(), enfermeiroListNewEnfermeiroEntidadeToAttach.getId());
                attachedEnfermeiroListNew.add(enfermeiroListNewEnfermeiroEntidadeToAttach);
            }
            enfermeiroListNew = attachedEnfermeiroListNew;
            setorEntidade.setEnfermeiroList(enfermeiroListNew);
            List<PatrimonioEntidade> attachedPatrimonioListNew = new ArrayList<PatrimonioEntidade>();
            for (PatrimonioEntidade patrimonioListNewPatrimonioEntidadeToAttach : patrimonioListNew) {
                patrimonioListNewPatrimonioEntidadeToAttach = em.getReference(patrimonioListNewPatrimonioEntidadeToAttach.getClass(), patrimonioListNewPatrimonioEntidadeToAttach.getId());
                attachedPatrimonioListNew.add(patrimonioListNewPatrimonioEntidadeToAttach);
            }
            patrimonioListNew = attachedPatrimonioListNew;
            setorEntidade.setPatrimonioList(patrimonioListNew);
            setorEntidade = em.merge(setorEntidade);
            for (EnfermeiroEntidade enfermeiroListOldEnfermeiroEntidade : enfermeiroListOld) {
                if (!enfermeiroListNew.contains(enfermeiroListOldEnfermeiroEntidade)) {
                    enfermeiroListOldEnfermeiroEntidade.setSetor(null);
                    enfermeiroListOldEnfermeiroEntidade = em.merge(enfermeiroListOldEnfermeiroEntidade);
                }
            }
            for (EnfermeiroEntidade enfermeiroListNewEnfermeiroEntidade : enfermeiroListNew) {
                if (!enfermeiroListOld.contains(enfermeiroListNewEnfermeiroEntidade)) {
                    SetorEntidade oldSetorOfEnfermeiroListNewEnfermeiroEntidade = enfermeiroListNewEnfermeiroEntidade.getSetor();
                    enfermeiroListNewEnfermeiroEntidade.setSetor(setorEntidade);
                    enfermeiroListNewEnfermeiroEntidade = em.merge(enfermeiroListNewEnfermeiroEntidade);
                    if (oldSetorOfEnfermeiroListNewEnfermeiroEntidade != null && !oldSetorOfEnfermeiroListNewEnfermeiroEntidade.equals(setorEntidade)) {
                        oldSetorOfEnfermeiroListNewEnfermeiroEntidade.getEnfermeiroList().remove(enfermeiroListNewEnfermeiroEntidade);
                        oldSetorOfEnfermeiroListNewEnfermeiroEntidade = em.merge(oldSetorOfEnfermeiroListNewEnfermeiroEntidade);
                    }
                }
            }
            for (PatrimonioEntidade patrimonioListOldPatrimonioEntidade : patrimonioListOld) {
                if (!patrimonioListNew.contains(patrimonioListOldPatrimonioEntidade)) {
                    patrimonioListOldPatrimonioEntidade.setSetor(null);
                    patrimonioListOldPatrimonioEntidade = em.merge(patrimonioListOldPatrimonioEntidade);
                }
            }
            for (PatrimonioEntidade patrimonioListNewPatrimonioEntidade : patrimonioListNew) {
                if (!patrimonioListOld.contains(patrimonioListNewPatrimonioEntidade)) {
                    SetorEntidade oldSetorOfPatrimonioListNewPatrimonioEntidade = patrimonioListNewPatrimonioEntidade.getSetor();
                    patrimonioListNewPatrimonioEntidade.setSetor(setorEntidade);
                    patrimonioListNewPatrimonioEntidade = em.merge(patrimonioListNewPatrimonioEntidade);
                    if (oldSetorOfPatrimonioListNewPatrimonioEntidade != null && !oldSetorOfPatrimonioListNewPatrimonioEntidade.equals(setorEntidade)) {
                        oldSetorOfPatrimonioListNewPatrimonioEntidade.getPatrimonioList().remove(patrimonioListNewPatrimonioEntidade);
                        oldSetorOfPatrimonioListNewPatrimonioEntidade = em.merge(oldSetorOfPatrimonioListNewPatrimonioEntidade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = setorEntidade.getId();
                if (findSetorEntidade(id) == null) {
                    throw new NonexistentEntityException("The setorEntidade with id " + id + " no longer exists.");
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
            SetorEntidade setorEntidade;
            try {
                setorEntidade = em.getReference(SetorEntidade.class, id);
                setorEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The setorEntidade with id " + id + " no longer exists.", enfe);
            }
            List<EnfermeiroEntidade> enfermeiroList = setorEntidade.getEnfermeiroList();
            for (EnfermeiroEntidade enfermeiroListEnfermeiroEntidade : enfermeiroList) {
                enfermeiroListEnfermeiroEntidade.setSetor(null);
                enfermeiroListEnfermeiroEntidade = em.merge(enfermeiroListEnfermeiroEntidade);
            }
            List<PatrimonioEntidade> patrimonioList = setorEntidade.getPatrimonioList();
            for (PatrimonioEntidade patrimonioListPatrimonioEntidade : patrimonioList) {
                patrimonioListPatrimonioEntidade.setSetor(null);
                patrimonioListPatrimonioEntidade = em.merge(patrimonioListPatrimonioEntidade);
            }
            em.remove(setorEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SetorEntidade> findSetorEntidadeEntities() {
        return findSetorEntidadeEntities(true, -1, -1);
    }

    public List<SetorEntidade> findSetorEntidadeEntities(int maxResults, int firstResult) {
        return findSetorEntidadeEntities(false, maxResults, firstResult);
    }

    private List<SetorEntidade> findSetorEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SetorEntidade.class));
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

    public SetorEntidade findSetorEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SetorEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getSetorEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SetorEntidade> rt = cq.from(SetorEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
