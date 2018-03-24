package top.oyoung.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Method: ${PACKAGE_NAME}
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/3/24 9:19
 */
//@WebServlet(name = "servlet",urlPatterns = {"/servlet"}/*, loadOnStartup = 1*/,initParams = {WebInitParam(name="", value="")})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(getInitParameter("master")+": hello ~ this is a servlet demo | user: "+ getServletContext().getInitParameter("user"));

    }
}
