package net.learnbyproject.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.learnbyproject.model.User;
import net.learnbyproject.service.UserService;

@WebServlet(urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("search-friend");
            List<User> results = UserService.searchFriend(keyword);
            request.setAttribute("results", results);
            
            String action = request.getParameter("action");
            RequestDispatcher dis;
            if (action != null && action.equals("ajax-search")) {
                dis = request.getRequestDispatcher("WEB-INF/search-result.jsp");
            }else {
                dis = request.getRequestDispatcher("search.jsp");
            }
            dis.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
