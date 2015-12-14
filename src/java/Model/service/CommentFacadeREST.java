/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.service;

import Model.Comment;
import Model.Image;
import Model.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ddahuy
 */
@Stateless
@Path("model.comment")
public class CommentFacadeREST extends AbstractFacade<Comment> {
    @PersistenceContext(unitName = "ImgUpload5PU")
    private EntityManager em;

    public CommentFacadeREST() {
        super(Comment.class);
    }

//    @POST
//    @Override
//    @Consumes({"application/xml", "application/json"})
//    public void create(Comment entity) {
//        super.create(entity);
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Consumes("application/x-www-form-urlencoded")
    //@Produces("text/plain")
    public String createComment(
            @FormParam("username") int id,
            @FormParam("imgid") int imgid,
            @FormParam("text") String text
            ) {
        Users u = (Users) em.createNamedQuery("Users.findById").setParameter("username", id).getSingleResult();
        Image i = (Image) em.createNamedQuery("Image.findByImgid").setParameter("imgid", imgid).getSingleResult();
        Comment comment = new Comment(u,i,text);
        comment.setText(text);
        super.create(comment);
        return "haha";
    }
    

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Comment entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Comment find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Comment> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Comment> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
