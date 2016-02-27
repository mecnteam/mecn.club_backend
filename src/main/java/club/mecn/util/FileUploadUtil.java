package club.mecn.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.*;

/**
 * Created by Administrator on 2016/2/1.
 */
public class FileUploadUtil {

    /**
     *
     * @param files 上传的文件
     * @param uploadPath /WEB-INF/static/img
     * @param username  用户名
     */
    public static void saveFile(CommonsMultipartFile[] files,String uploadPath,String username)
    {

        CommonsMultipartFile uploadfile = files[0];
        if(!uploadfile.isEmpty())
        {
            try {
                String originalFilename = uploadfile.getOriginalFilename();
                String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")+1);

                String filename = username + "_avatar." + suffix;
                //get outputstream
                FileOutputStream out = new FileOutputStream("D:/mecnoutput/avatar/"+filename);

                InputStream in =  uploadfile.getInputStream();

                int b = 0;
                while((b = in.read()) != -1)
                {
                    out.write(b);
                }

                out.flush();
                out.close();
                in.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
