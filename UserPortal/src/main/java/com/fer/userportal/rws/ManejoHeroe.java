/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fer.userportal.rws;

import com.fer.userportal.modelo.Heroe;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Luis Fernando
 */
@Stateless
@Path("/heroes")
public class ManejoHeroe {

    @PersistenceContext
    EntityManager em;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Heroe create(Heroe pHeroe) {
        pHeroe = em.merge(pHeroe);
        return pHeroe;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Heroe getHeroeXId(@PathParam("id") Integer pId) {
        return em.find(Heroe.class, pId);
    }
 
    @GET
    @Path("/heroe/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Heroe> getHeroeXNombre(@PathParam("name") String pNombre) {

        return em.createNamedQuery("Heroe.likeName").
                setParameter("name", "%" + pNombre + "%").
                getResultList();
    }
  
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Heroe update(Heroe pHeroe) {
        return em.merge(pHeroe);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Heroe delete(@PathParam("id") Integer pId) {
        Heroe heroe = em.getReference(Heroe.class, pId);
        if (heroe != null) {
            em.remove(heroe);
            return heroe;
        } else {
            return new Heroe();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Heroe> getLstHeroes() {
        return em.createNamedQuery("Heroe.findAll").getResultList();
    }
}
