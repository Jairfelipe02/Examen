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
import net.learnbyproject.helper.Keys;
import net.learnbyproject.helper.Validator;
import net.learnbyproject.model.User;
import net.learnbyproject.service.UserService;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String firstName = request.getParameter("first-name").trim();
            String lastName = request.getParameter("last-name").trim();
            String emailOrMobile = request.getParameter("mobile-or-email").trim();
            String password = request.getParameter("user-password").trim();
            String day = request.getParameter("day").trim();
            String month = request.getParameter("month").trim();
            String year = request.getParameter("year").trim();
            String sex = request.getParameter("sex").trim();
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            String birthday = String.format("%s-%s-%s", day, month, year);
            // Add to database
            
            if (UserService.addNewUser(firstName, lastName, emailOrMobile,
                    password, birthday, sex) == Validator.SUCCESS) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute(Keys.ERROR, "Something wrong! Please, try again!");
                dis.forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
