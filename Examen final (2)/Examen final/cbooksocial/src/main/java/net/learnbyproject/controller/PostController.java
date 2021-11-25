/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.learnbyproject.helper.Keys;
import net.learnbyproject.model.User;
import net.learnbyproject.service.PostService;

@WebServlet(urlPatterns = {"/posts"})
public class PostController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> options = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Keys.USER);

        String action = request.getParameter("action");
        if (action != null && action.equals("add-post")) {
            try {
                String postContent = request.getParameter("post-content");
                PostService.addNewPost(user.getId(), postContent);

                options.put("result", "OK");
            } catch (SQLException ex) {
                options.put("result", "FAILED");
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action != null && action.equals("add-comment")) {
            try {
                int postId = Integer.parseInt(request.getParameter("post-id"));
                String commentContent = request.getParameter("comment-content");
                PostService.addNewComment(user.getId(), postId, commentContent);

                options.put("result", "OK");
            } catch (SQLException ex) {
                options.put("result", "FAILED");
                Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String json = new Gson().toJson(options);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
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
