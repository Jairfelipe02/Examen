/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.learnbyproject.listeners;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import net.learnbyproject.service.DBService;

/**
 * Web application lifecycle listener.
 *
 * @author HP
 */
@WebListener()
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        DBService.DRIVER = app.getInitParameter("db.driver");
        DBService.URL = app.getInitParameter("db.url");
        DBService.USER = app.getInitParameter("db.user");
        DBService.PASSWORD = app.getInitParameter("db.password");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("The web app has been shut down");
    }
}
