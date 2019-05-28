package com.mitrais.rms.controller;

import javax.servlet.http.HttpServlet;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractController extends HttpServlet
{
    public static final String VIEW_PREFIX = "/WEB-INF/jsp";
    public static final String VIEW_SUFFIX = ".jsp";
    public static final String projectName = "/"+getProjectName();

    protected String getTemplatePath(String path)
    {
        if (path.equalsIgnoreCase("/"))
        {
            return VIEW_PREFIX + path + "index" + VIEW_SUFFIX;
        }
        else
        {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }

     private static String getProjectName() {
        try (InputStream input = AbstractController.class.getClassLoader().getResourceAsStream("database.properties")){
            Properties p = new Properties();
            p.load(input);
            return p.getProperty("project.name");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
