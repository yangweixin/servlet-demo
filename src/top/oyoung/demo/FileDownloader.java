package top.oyoung.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Method: top.oyoung.demo
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/3/28 17:14
 */
@WebServlet(name="download", urlPatterns = "/download")
public class FileDownloader extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(new File("").getCanonicalPath());
        File file = new File("F:/IDEA/young/servlet-demo/out/artifacts/servlet_demo_Web_exploded2/upload/conf.cfg");
        FileInputStream in = new FileInputStream(file);
        String filename = URLEncoder.encode(file.getName(),"utf-8");
        byte[] buffer = new byte[in.available()];
        in.read(buffer);

        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-Disposition","attachment; filename="+filename+"");
        resp.getOutputStream().write(buffer);
        resp.getOutputStream().flush();
        resp.getOutputStream().close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
