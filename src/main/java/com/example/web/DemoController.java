package com.example.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "demoShiro", urlPatterns = "/")
public class DemoController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("zhangsan".equals(username) && "123".equals(password))
        {
            req.setAttribute("username", username);
            req.getRequestDispatcher("/main").forward(req, resp);
        }
        else
        {
            if (null != username)
                req.setAttribute("errorMsg", "账号或密码有误");
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
        }
    }
}