/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.services;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author javadev
 */
public class EntityManagerUtils {

    private static EntityManagerFactory emf = null;;

    public static EntityManager getEntityManager() throws NamingException {

        if(emf == null)
            emf = Persistence.createEntityManagerFactory("br.ufc.deti.ecgweb_pu");        
        
        return emf.createEntityManager();
    }
}
