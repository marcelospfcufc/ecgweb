/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.deti.ecgweb.services;

import br.ufc.deti.ecgweb.domain.Client;
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
    
    @GET
    @Path("/list")
    @Produces("application/json")
    public Response getClients() {
        return Response.ok("TODO ..").build();
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)    
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(Client client) {
        System.out.println(client);
        return Response.ok("deu certo").build();
    }
}
