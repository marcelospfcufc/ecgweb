/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.services;

import br.ufc.deti.ecgweb.domain.Client;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class to provide services for Clients entity.
 * @author Marcelo Araujo Lima
 */
@Path("/client")
public class ClientService {
    
    EntityManager em;
    
    @GET
    @Path("/")
    @Produces("application/json")
    public Response getClients() throws NamingException {
        
        List<Client> listClients;
        
        em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        
        listClients = em.createQuery("select c from Client c").getResultList();
        
        em.getTransaction().commit();
        em.close();
        
        return Response.ok(listClients).build();
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)    
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(Client client) throws NamingException {
        
        Response rs;
        
        em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();        
        try {
            em.persist(client);
            em.getTransaction().commit();
            rs = Response.ok().build();
        }catch(Exception ex) {
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        }finally {
            em.close();            
        }
        return rs;
    }
}
