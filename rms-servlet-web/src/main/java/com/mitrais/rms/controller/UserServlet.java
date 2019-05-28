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
import java.util.List;

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());

        if ("/list".equalsIgnoreCase(req.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String act = req.getParameter("action");

        switch (act) {
            case "delete": {
                Long id = Long.parseLong(req.getParameter("id"));
                UserDao userDao = UserDaoImpl.getInstance();
                if (id != req.getSession().getAttribute("id")){
                    boolean response = userDao.deleteById(id);
                    req.setAttribute("deleteResponse", response);
                }
                else {
                    req.setAttribute("deleteResponse", "false");
                }
                RequestDispatcher rd = findAllUserAndGetDispatcher(req,userDao);
                rd.forward(req, resp);
                //resp.sendRedirect(projectName + "/users/list");
                break;
            }
            case "edit" : {
                Long id = Long.parseLong(req.getParameter("id"));
                UserDao userDao = UserDaoImpl.getInstance();
                boolean response = userDao.updateUser(req.getParameter("username"), req.getParameter("userpass"), id);
                req.setAttribute("editResponse", response);
                RequestDispatcher rd = findAllUserAndGetDispatcher(req, userDao);
                rd.forward(req, resp);
                break;
            }
            case "insert": {
                String username = req.getParameter("username");
                String userpass = req.getParameter("userpass");
                UserDao userDao = UserDaoImpl.getInstance();
                boolean response = userDao.saveUser(username, userpass);
                req.setAttribute("insertResponse", response);
                RequestDispatcher rd = findAllUserAndGetDispatcher(req,userDao);
                rd.forward(req, resp);
                break;
            }
        }
    }
    private RequestDispatcher findAllUserAndGetDispatcher(HttpServletRequest req, UserDao userDao) {
        List<User> users = userDao.findAll();
        req.setAttribute("users", users);
        String path = getTemplatePath(req.getServletPath()+"/list");
        return req.getRequestDispatcher(path);
    }

}
