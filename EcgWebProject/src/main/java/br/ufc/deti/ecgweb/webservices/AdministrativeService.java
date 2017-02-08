/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.webservices;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author javadev
 */
@Path("/administrative")
public class AdministrativeService {

    EntityManager em;

    @POST
    @Path("/populatedb")
    @Produces("application/json")
    @Consumes("application/json")
    public Response populateDatabase(String key) throws NamingException {
        
        /*if(!key.equals("qpalzm")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();            
        }
        
        Response response;
        em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();

        ResearchDatabase researchDatabase = new ResearchDatabase();
        researchDatabase.setName("MIT-BIH");
        researchDatabase.setDescription("TK_RESEARCH_DATABASE_DESCRIPTION");
        try {
            em.persist(researchDatabase);
            em.getTransaction().commit();
            response = Response.ok().build();
        } catch (Exception ex) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
        } finally {
            em.close();
        }

        return response;
        */
        return null;
    }

}
