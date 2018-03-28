package top.oyoung.demo;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @Method: ${PACKAGE_NAME}
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/3/24 9:19
 */
//@WebServlet(name = "servlet",urlPatterns = {"/servlet"}/*, loadOnStartup = 1*/,initParams = {WebInitParam(name="", value="")})
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> recvMap = getJson(request);
        recvMap.put("mark", System.currentTimeMillis());
        String rsStr = new Gson().toJson(recvMap);
        System.out.println(rsStr);

        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        Writer out = response.getWriter();
        out.write(rsStr);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(getInitParameter("master")+": hello ~ this is a servlet demo | user: "+ getServletContext().getInitParameter("user"));
    }

    public Map<String, Object> getJson(HttpServletRequest request) throws IOException{
        BufferedReader reader = new BufferedReader(request.getReader());

        StringBuffer strBuf = new StringBuffer();
        String line = null;
        while((line = reader.readLine()) != null){
            strBuf.append(line);
        }

        return new Gson().fromJson(strBuf.toString(),Map.class);
    }
}
