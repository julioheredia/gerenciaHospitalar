/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade.mysql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author CLIENTE
 */
public class JpaMySqlConector {

    private static final long serialVersionUID = 1L;
    private static JpaMySqlConector instancia;
    private EntityManagerFactory factory;

    private JpaMySqlConector() {
        factory = Persistence.createEntityManagerFactory("GerenciaHospitalarPU");
    }

    public static JpaMySqlConector getInstancia() {

        if (instancia == null) {
            instancia = new JpaMySqlConector();
        }
        return instancia;
    }

    public EntityManager getEntidadeManager() {
        return factory.createEntityManager();
    }
}
