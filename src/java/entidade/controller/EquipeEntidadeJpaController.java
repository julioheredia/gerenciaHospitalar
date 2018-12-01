/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.controller;


import entidade.EquipeEntidade;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidade.EscalaEquipeEntidade;
import entidade.ProfissionalEntidade;
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
public class EquipeEntidadeJpaController implements Serializable {

    public EntityManager getEntityManager() {
        return JpaMySqlConector.getInstancia().getEntidadeManager();
    }
    public void create(EquipeEntidade equipeEntidade) throws IllegalOrphanException {
        if (equipeEntidade.getProfissionalList() == null) {
            equipeEntidade.setProfissionalList(new ArrayList<ProfissionalEntidade>());
        }
        List<String> illegalOrphanMessages = null;
        EscalaEquipeEntidade escalaOrphanCheck = equipeEntidade.getEscala();
        if (escalaOrphanCheck != null) {
            EquipeEntidade oldEquipeOfEscala = escalaOrphanCheck.getEquipe();
            if (oldEquipeOfEscala != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The EscalaEquipeEntidade " + escalaOrphanCheck + " already has an item of type EquipeEntidade whose escala column cannot be null. Please make another selection for the escala field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EscalaEquipeEntidade escala = equipeEntidade.getEscala();
            if (escala != null) {
                escala = em.getReference(escala.getClass(), escala.getId());
                equipeEntidade.setEscala(escala);
            }
            List<ProfissionalEntidade> attachedProfissionalList = new ArrayList<ProfissionalEntidade>();
            for (ProfissionalEntidade profissionalListProfissionalEntidadeToAttach : equipeEntidade.getProfissionalList()) {
                profissionalListProfissionalEntidadeToAttach = em.getReference(profissionalListProfissionalEntidadeToAttach.getClass(), profissionalListProfissionalEntidadeToAttach.getId());
                attachedProfissionalList.add(profissionalListProfissionalEntidadeToAttach);
            }
            equipeEntidade.setProfissionalList(attachedProfissionalList);
            em.persist(equipeEntidade);
            if (escala != null) {
                escala.setEquipe(equipeEntidade);
                escala = em.merge(escala);
            }
            for (ProfissionalEntidade profissionalListProfissionalEntidade : equipeEntidade.getProfissionalList()) {
                EquipeEntidade oldEquipeOfProfissionalListProfissionalEntidade = profissionalListProfissionalEntidade.getEquipe();
                profissionalListProfissionalEntidade.setEquipe(equipeEntidade);
                profissionalListProfissionalEntidade = em.merge(profissionalListProfissionalEntidade);
                if (oldEquipeOfProfissionalListProfissionalEntidade != null) {
                    oldEquipeOfProfissionalListProfissionalEntidade.getProfissionalList().remove(profissionalListProfissionalEntidade);
                    oldEquipeOfProfissionalListProfissionalEntidade = em.merge(oldEquipeOfProfissionalListProfissionalEntidade);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EquipeEntidade equipeEntidade) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EquipeEntidade persistentEquipeEntidade = em.find(EquipeEntidade.class, equipeEntidade.getId());
            EscalaEquipeEntidade escalaOld = persistentEquipeEntidade.getEscala();
            EscalaEquipeEntidade escalaNew = equipeEntidade.getEscala();
            List<ProfissionalEntidade> profissionalListOld = persistentEquipeEntidade.getProfissionalList();
            List<ProfissionalEntidade> profissionalListNew = equipeEntidade.getProfissionalList();
            List<String> illegalOrphanMessages = null;
            if (escalaNew != null && !escalaNew.equals(escalaOld)) {
                EquipeEntidade oldEquipeOfEscala = escalaNew.getEquipe();
                if (oldEquipeOfEscala != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The EscalaEquipeEntidade " + escalaNew + " already has an item of type EquipeEntidade whose escala column cannot be null. Please make another selection for the escala field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (escalaNew != null) {
                escalaNew = em.getReference(escalaNew.getClass(), escalaNew.getId());
                equipeEntidade.setEscala(escalaNew);
            }
            List<ProfissionalEntidade> attachedProfissionalListNew = new ArrayList<ProfissionalEntidade>();
            for (ProfissionalEntidade profissionalListNewProfissionalEntidadeToAttach : profissionalListNew) {
                profissionalListNewProfissionalEntidadeToAttach = em.getReference(profissionalListNewProfissionalEntidadeToAttach.getClass(), profissionalListNewProfissionalEntidadeToAttach.getId());
                attachedProfissionalListNew.add(profissionalListNewProfissionalEntidadeToAttach);
            }
            profissionalListNew = attachedProfissionalListNew;
            equipeEntidade.setProfissionalList(profissionalListNew);
            equipeEntidade = em.merge(equipeEntidade);
            if (escalaOld != null && !escalaOld.equals(escalaNew)) {
                escalaOld.setEquipe(null);
                escalaOld = em.merge(escalaOld);
            }
            if (escalaNew != null && !escalaNew.equals(escalaOld)) {
                escalaNew.setEquipe(equipeEntidade);
                escalaNew = em.merge(escalaNew);
            }
            for (ProfissionalEntidade profissionalListOldProfissionalEntidade : profissionalListOld) {
                if (!profissionalListNew.contains(profissionalListOldProfissionalEntidade)) {
                    profissionalListOldProfissionalEntidade.setEquipe(null);
                    profissionalListOldProfissionalEntidade = em.merge(profissionalListOldProfissionalEntidade);
                }
            }
            for (ProfissionalEntidade profissionalListNewProfissionalEntidade : profissionalListNew) {
                if (!profissionalListOld.contains(profissionalListNewProfissionalEntidade)) {
                    EquipeEntidade oldEquipeOfProfissionalListNewProfissionalEntidade = profissionalListNewProfissionalEntidade.getEquipe();
                    profissionalListNewProfissionalEntidade.setEquipe(equipeEntidade);
                    profissionalListNewProfissionalEntidade = em.merge(profissionalListNewProfissionalEntidade);
                    if (oldEquipeOfProfissionalListNewProfissionalEntidade != null && !oldEquipeOfProfissionalListNewProfissionalEntidade.equals(equipeEntidade)) {
                        oldEquipeOfProfissionalListNewProfissionalEntidade.getProfissionalList().remove(profissionalListNewProfissionalEntidade);
                        oldEquipeOfProfissionalListNewProfissionalEntidade = em.merge(oldEquipeOfProfissionalListNewProfissionalEntidade);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = equipeEntidade.getId();
                if (findEquipeEntidade(id) == null) {
                    throw new NonexistentEntityException("The equipeEntidade with id " + id + " no longer exists.");
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
            EquipeEntidade equipeEntidade;
            try {
                equipeEntidade = em.getReference(EquipeEntidade.class, id);
                equipeEntidade.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipeEntidade with id " + id + " no longer exists.", enfe);
            }
            EscalaEquipeEntidade escala = equipeEntidade.getEscala();
            if (escala != null) {
                escala.setEquipe(null);
                escala = em.merge(escala);
            }
            List<ProfissionalEntidade> profissionalList = equipeEntidade.getProfissionalList();
            for (ProfissionalEntidade profissionalListProfissionalEntidade : profissionalList) {
                profissionalListProfissionalEntidade.setEquipe(null);
                profissionalListProfissionalEntidade = em.merge(profissionalListProfissionalEntidade);
            }
            em.remove(equipeEntidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EquipeEntidade> findEquipeEntidadeEntities() {
        return findEquipeEntidadeEntities(true, -1, -1);
    }

    public List<EquipeEntidade> findEquipeEntidadeEntities(int maxResults, int firstResult) {
        return findEquipeEntidadeEntities(false, maxResults, firstResult);
    }

    private List<EquipeEntidade> findEquipeEntidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EquipeEntidade.class));
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

    public EquipeEntidade findEquipeEntidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EquipeEntidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipeEntidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EquipeEntidade> rt = cq.from(EquipeEntidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
