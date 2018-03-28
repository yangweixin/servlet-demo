package top.oyoung.demo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Method: top.oyoung.demo
 * @Desc:
 * @Author: Yang Weixin
 * @Date: 2018/3/28 16:26
 */
@WebServlet(name = "upload",urlPatterns = "/upload")
public class FileUploader extends HttpServlet {

    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello uploader");
        if(!ServletFileUpload.isMultipartContent(req)){
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File("tmpdir"));

        ServletFileUpload uploader = new ServletFileUpload(factory);
        uploader.setFileSizeMax(MAX_FILE_SIZE);
        uploader.setSizeMax(MAX_REQUEST_SIZE);

        String uploadPath = req.getServletContext().getRealPath("./") + File.separator + "upload";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try {
            List<FileItem> list = uploader.parseRequest(req);
            if(list == null || list.size() <= 0){
                return ;
            }

            for (FileItem item : list){
                String fileName = new File(item.getName()).getName();
                String filePath = uploadPath + File.separator + fileName;
                File storeFile = new File(filePath);
                // 在控制台输出文件的上传路径
                System.out.println(filePath);
                item.write(storeFile);
                System.out.println("----->save success");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
