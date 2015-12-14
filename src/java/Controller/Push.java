package Controller;

import Model.Image;
import Model.Tag;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import java.lang.String;


/**
 *
 * @author patricka
 */
@WebServlet(name = "Push", urlPatterns = {"/Push"})
@MultipartConfig(location = "/var/www/html/test")
public class Push extends HttpServlet {

    String link = "http://192.168.56.1/test/";

    EntityManager em;
    EntityManagerFactory emf;
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Wtf</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Wtf at " + request.getContextPath() + "</h1>");
//            out.println("PLEASE!!!!!!");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        try {
            request.getPart("file").write(request.getPart("file").getSubmittedFileName());
            out.println(request.getPart("file").getSubmittedFileName() + " File uploaded successfully! User "+request.getSession().getAttribute("userid"));           
        
            // In database, store just the getSubmittedFileName
            emf = Persistence.createEntityManagerFactory("ImgUpload5PU");
            
            try {
                
               
                Image img = new Image(request.getPart("file").getSubmittedFileName(), request.getParameter("description") , new Date(), (int)request.getPart("file").getSize());
                
               
                //out.println(user.getUsername() + "<br>");                
                UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
                
                transaction.begin();
                em = emf.createEntityManager();
                Users user =em.find(Users.class,request.getSession().getAttribute("userid"));
                
                Tag tag = new Tag(request.getParameter("tagName"), user, img);
                //em.getTransaction().begin();
                //em.persist(u);
                em.persist(img);
                em.persist(tag);
                //em.getTransaction().commit();
                transaction.commit();
                
                //out.println("DONE");
                
                
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
                        
            out.println("</head>");
            out.println("<body>");
            out.println(img.getImgid() + "<br>");
            out.println(img.getPath() + "<br>");
            out.println(img.getDescription() + "<br>");
            out.println("<img src=" + '"' + link + request.getPart("file").getSubmittedFileName() + '"' + "height=500px width=auto" + ">");
            out.println("</body>");
            out.println("</html>");
                
            } catch (Exception e) {
                out.println("Exception -->" + e.getMessage());
            }
            emf.close();
            
            }catch (Exception e) {
            out.println("Exception -->" + e.getMessage());
        } finally {
            out.close();
        }
            
    }

    }