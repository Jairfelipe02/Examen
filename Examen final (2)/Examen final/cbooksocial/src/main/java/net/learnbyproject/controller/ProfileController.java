/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.learnbyproject.helper.Keys;
import net.learnbyproject.helper.Validator;
import net.learnbyproject.model.User;
import net.learnbyproject.service.UserService;

@WebServlet(urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sess = request.getSession();
        sess.setAttribute(Keys.ERROR, "");
        sess.setAttribute(Keys.SUCCESS, "");
        // Step 2
        String action = request.getParameter("action");
        if (action != null && action.equals("update-profile")) {
            try {
                String firstName = request.getParameter("first-name").trim();
                String lastName = request.getParameter("last-name").trim();
                String newEmailOrMobile = request.getParameter("mobile-or-email").trim();
                String userPass = request.getParameter("user-password").trim();
                String day = request.getParameter("day").trim();
                String month = request.getParameter("month").trim();
                String year = request.getParameter("year").trim();
                String sex = request.getParameter("sex").trim();
                String birthday = String.format("%s-%s-%s", day, month, year);
                
                String currentEmailOrMobile = ((User) sess.getAttribute("user")).getEmailOrMobile();

                if (UserService.updateUser(firstName, lastName, newEmailOrMobile,
                            userPass, birthday, sex, currentEmailOrMobile) == Validator.SUCCESS) {
                    // update session
                    User user = UserService.getUser(newEmailOrMobile);
                    sess.setAttribute(Keys.USER, user); 
                    sess.setAttribute(Keys.SUCCESS, "Data has been saved!");
                    
                } else {
                    sess.setAttribute(Keys.ERROR, "Something wrong! Please, try again!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Step 1
        RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/profile.jsp");
        dis.forward(request, response);
//        if (sess.getAttribute(Keys.USER) != null) {
//            RequestDispatcher dis = request.getRequestDispatcher("WEB-INF/profile.jsp");
//            dis.forward(request, response);
//        } else { // not logged in
//            response.sendRedirect("login.jsp");
//        }
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
