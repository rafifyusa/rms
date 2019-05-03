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

                if (userDao.find(id).isPresent()){
                    try {
                        User user = userDao.find(id).get();
                        userDao.delete(user);

                        resp.sendRedirect(getAppName()+ "/users/list");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "edit" : {
                Long id = Long.parseLong(req.getParameter("id"));
                UserDao userDao = UserDaoImpl.getInstance();

                if (userDao.find(id).isPresent()){
                    User user = userDao.find(id).get();
                    user.setUserName(req.getParameter("username"));
                    user.setPassword(req.getParameter("userpass"));
                    userDao.update(user);
                    resp.sendRedirect(getAppName()+ "/users/list");
                }
                break;
            }
            case "insert": {
                String username = req.getParameter("username");
                System.out.println("New User : " + username);
                String userpass = req.getParameter("userpass");

                User newUser = new User(username, userpass);

                UserDao userDao = UserDaoImpl.getInstance();
                userDao.save(newUser);

                resp.sendRedirect(getAppName()+ "/users/list");
                break;
            }
        }
    }

}
