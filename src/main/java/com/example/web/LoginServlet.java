package com.example.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //由于 authc.loginUrl 配置的为该请求路径, 当请求的参数被 authc 拦截器并进行匹配时, 如果匹配不成功, 则会执行该 servlet 的代码
        //由于认证失败, 则抛出异常, 异常信息存放在 request 中, shiroLoginFailure 即为异常类的全限定类名
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");

        //取出异常信息返回到 jsp 页面中
        if(null != exceptionClassName)
        {
            if(UnknownAccountException.class.getName().equals(exceptionClassName))
                req.setAttribute("errorMsg", "账号不存在");
            else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName))
                req.setAttribute("errorMsg", "用户名或密码错误");
            else
                req.setAttribute("errorMsg", "其他未知异常信息");
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
}