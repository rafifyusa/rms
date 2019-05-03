package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String userpass = req.getParameter("userpass");

        UserDao userDao = UserDaoImpl.getInstance();
        if (userDao.findByUserName(username).isPresent()){
            User user = userDao.findByUserName(username).get();

            if (user.getPassword().equals(userpass)) {
                req.getSession().setAttribute("name", username);
                resp.sendRedirect(getAppName()+"/index.jsp");
            }
            else {
                System.out.println("Invalid Password");
                req.setAttribute("errorMessage", "Invalid Password");
                String path = getTemplatePath(req.getServletPath());
                RequestDispatcher rd = req.getRequestDispatcher(path);
                rd.include(req, resp);
            }
        }
        else {
            System.out.println("Invalid User");
            req.setAttribute("errorMessage", "Invalid User");
            String path = getTemplatePath(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(path);
            rd.include(req, resp);
        }

    }
}
