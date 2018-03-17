/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.userportal.rws;

import com.fer.userportal.modelo.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Luis Fernando
 */
@Path("/api")
@Stateless
public class ManejoUsuarioRWS {

    @PersistenceContext
    EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User pUSer) {
        pUSer = em.merge(pUSer);
        return pUSer;
    }
 
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User gerUSrXId(@PathParam("id") Integer pId) {
        return em.find(User.class, pId);
    }
 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User pUSer) {
        return em.merge(pUSer);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User delete(@PathParam("id") Integer pId) {
        User usuario = em.getReference(User.class, pId);
        if (usuario != null) {
            em.remove(usuario);
            return usuario;
        } else {
            return new User();
        }
    } 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getLstUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }
}
