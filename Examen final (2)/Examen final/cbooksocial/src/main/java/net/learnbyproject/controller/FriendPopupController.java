package net.learnbyproject.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.learnbyproject.model.User;
import net.learnbyproject.service.PostService;
import net.learnbyproject.service.UserService;

@WebServlet(urlPatterns = {"/friend-popup"})
public class FriendPopupController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String emailOrMobile = request.getParameter("emailOrMobile");
            User user = UserService.getUser(emailOrMobile);
            int totalPosts = PostService.countUserPosts(user.getId());
            int totalComments = PostService.countUserComments(user.getId());
            // convert array of bytes to base 64
            String imgData = "data:image/jpeg;base64," + user.getAvatar();
            Map<String,String> options = new LinkedHashMap<>();
            options.put("imageString", imgData);
            options.put("totalPosts", String.valueOf(totalPosts));
            options.put("totalComments", String.valueOf(totalComments));
            options.put("fullName", String.format("%s %s", 
                            user.getFirstName(), user.getLastName()));
            String json = new Gson().toJson(options);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(FriendPopupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
