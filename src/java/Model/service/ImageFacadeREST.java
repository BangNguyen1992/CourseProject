/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.service;

import Model.Image;
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
 * @author ddahuy
 */
@Stateless
@Path("model.image")
public class ImageFacadeREST extends AbstractFacade<Image> {
    @PersistenceContext(unitName = "ImgUpload5PU")
    private EntityManager em;
    private String[] galleryImages = new String[6];

    public ImageFacadeREST() {
        super(Image.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Image entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Image entity) {
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
    public Image find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Image> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Image> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    
    @GET
    @Path("viewgallery")
    //@Produces("text/html")
    @Produces({MediaType.APPLICATION_XML})
    public List<Image> viewGallery() {
        // save the results in a list
        List<Image> image = em.createNamedQuery("Image.findAll").setMaxResults(6).getResultList();

        // proceed
        int b;
        b = image.size()-1;
        int a = 0;
        for(int index = b; index >= b-5; index--){
            if(image.get(index)==null){
                return image;
            }
            galleryImages[a]=(image.get(index)).getPath();
           
            a++;
//            for (Image i: image){
//            if (i.getPath().endsWith("jpg") || i.getPath().endsWith("png") ||i.getPath().endsWith("JPG")) {
//                galleryImages.add("Picture title " + i.getPath() + "<br>" + "<img src=\"http://192.168.56.1/test/" + i.getPath() + "\" height=\"200\" width=\"200\"><br>");
//            } else {
//                galleryImages.add("Found upload with title " + i.getPath() + " that is not an image <br>.");
//            }
//        }
        }
        // return the ArrayList with the html tags
        return image;
        
    }
        
      
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
