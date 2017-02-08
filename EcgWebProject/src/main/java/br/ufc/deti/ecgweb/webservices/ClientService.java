/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.webservices;

import br.ufc.deti.ecgweb.domain.Doctor;
import br.ufc.deti.ecgweb.domain.ClientCategory;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class to provide services for Clients entity.
 *
 * @author Marcelo Araujo Lima
 */
@Path("/client")
public class ClientService {

    EntityManager em;

    @GET
    @Path("/")
    @Produces("application/json")
    public Response getClients() throws NamingException {

        /*List<Client> listClients;

        em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();

        listClients = em.createQuery("select c from Doctor c").getResultList();

        em.getTransaction().commit();
        em.close();

        return Response.ok(listClients).build();
        */
        return null;
    }

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(Doctor client) throws NamingException {

        /*if (client.getCategory() == ClientCategory.DOCTOR) {
            Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Response rs;

        em = EntityManagerUtils.getEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(client);
            em.getTransaction().commit();
            rs = Response.ok().build();
        } catch (Exception ex) {
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } finally {
            em.close();
        }
        return rs;
        */
        return null;
    }

    @POST
    @Path("/{clientId}/addexam")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //public Response addExam(@PathParam("clientId") Long clientId, Exam exam) throws NamingException {
    public Response addExam(@PathParam("clientId") Long clientId) throws NamingException {

        /*Response rs;
        em = EntityManagerUtils.getEntityManager();

        em.getTransaction().begin();
        try {

            Doctor client = em.find(Doctor.class, clientId);

            if (client == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            exam.setClientId(clientId);
            em.persist(exam);
            client.getExams().add(exam);
            em.getTransaction().commit();
            rs = Response.ok().build();
        } catch (Exception ex) {
            System.out.println("\nMsg:\n" + ex.getMessage());
           ex.printStackTrace();
            rs = Response.status(Response.Status.BAD_REQUEST).build();
        } finally {
            em.close();
        }
        return rs;
        */
        return null;
    }

}
