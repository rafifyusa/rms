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
import java.util.Optional;

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
        Optional<User> user = userDao.findByUserNameAndPassword(username,userpass);
        if (user.isPresent()){
            req.getSession().setAttribute("name", username);
            req.getSession().setAttribute("id",user.get().getId());
            resp.sendRedirect(projectName + "/index.jsp");
        }
        else {
            req.setAttribute("errorMessage", "Invalid login info");
            String path = getTemplatePath(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(path);
            rd.include(req, resp);
        }
    }
}
