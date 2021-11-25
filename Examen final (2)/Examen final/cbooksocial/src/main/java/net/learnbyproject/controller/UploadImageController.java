/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import net.learnbyproject.helper.Keys;
import net.learnbyproject.helper.StringHelper;
import net.learnbyproject.helper.Validator;
import net.learnbyproject.model.User;
import net.learnbyproject.service.UserService;

@MultipartConfig(maxFileSize = 16177215) // upload file's size up to 16MB
@WebServlet(urlPatterns = {"/upload-image"})
public class UploadImageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute(Keys.ERROR, "");
        InputStream inputStream = null;

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("userCoverPhoto");
        if (filePart != null) {
            // get input stream of the uploaded file
            inputStream = filePart.getInputStream();
            try {
                String emailOrMobile = ((User) session.getAttribute(Keys.USER)).getEmailOrMobile();
                if (UserService.updateUserAvatar(inputStream, emailOrMobile) == Validator.SUCCESS) {
                    User user = UserService.getUser(emailOrMobile);
                    session.setAttribute(Keys.USER, user);

                } else {
                    session.setAttribute(Keys.ERROR, "Cannot upload your avatar. Try again later!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UploadImageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.sendRedirect("profile");
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
