/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.service;

import Model.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ddahuy
 */
@Stateless
@Path("model.users")

public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "ImgUpload5PU")

    private EntityManager em;
    private List<Users> userList;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(
            @FormParam("username") String username,
            @FormParam("password") String password) {

        Users user = new Users();
        this.userList = em.createNamedQuery("Users.findAll").getResultList();
        user.setUsername(username);
        user.setPassword(password);

        super.create(user);
        return Response.status(200)
                .entity("addUser is called, name : " + username + ", pass : " + password)
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @Context
    private HttpServletRequest request;

    @GET
    //@Override
    //@Produces({"application/xml", "application/json"})
    @Path("userLogin/{username}/{password}")
    public String userLogin(
            @PathParam("username") String username,
            @PathParam("password") String password) {
        this.userList = em.createNamedQuery("Users.findAll").getResultList();
        for (Users u : this.userList) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userid", u.getId());
                session.setAttribute("user", username);
                return "User No: " + u.getId() + " with Username: " + u.getUsername() + " has logged in";
            }
        }
        return "false";

    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
