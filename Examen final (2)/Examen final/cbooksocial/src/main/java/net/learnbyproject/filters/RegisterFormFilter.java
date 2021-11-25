/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import net.learnbyproject.helper.Keys;
import net.learnbyproject.helper.Validator;
import net.learnbyproject.service.UserService;

/**
 *
 * @author Admin filterName 
 */
@WebFilter(urlPatterns = {"/register"})
public class RegisterFormFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {        
        // Do some initialization
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        try {
            String firstName = request.getParameter("first-name").trim();
            String lastName = request.getParameter("last-name").trim();
            String emailOrMobile = request.getParameter("mobile-or-email").trim();
            String password = request.getParameter("user-password").trim();
            
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            if (!Validator.checkName(firstName)) {
                request.setAttribute(Keys.ERROR, "First name is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkName(lastName)) {
                request.setAttribute(Keys.ERROR, "Last name is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkEmailOrPhone(emailOrMobile)) {
                request.setAttribute(Keys.ERROR, "Email is invalid");
                dis.forward(request, response);
            } else if (!Validator.checkPassword(password)) {
                request.setAttribute(Keys.ERROR, "Password is invalid [mix: 4, max: 8, one digit, one upper case]");
                dis.forward(request, response);
            } else if (UserService.isDuplicateEmailOrPhone(emailOrMobile)) {
                request.setAttribute(Keys.ERROR, "Email/Phone is duplicated");
                dis.forward(request, response);
            } else {
                chain.doFilter(request, response);
            }            
        } catch (Exception ex) {
            Logger.getLogger(RegisterFormFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {  
        // do some closable resources
    }
}
