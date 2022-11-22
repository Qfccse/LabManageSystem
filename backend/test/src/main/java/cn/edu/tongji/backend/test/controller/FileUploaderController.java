package cn.edu.tongji.backend.test.controller;

import cn.edu.tongji.backend.test.pojo.FileUploader;
import cn.edu.tongji.backend.test.service.FileUploaderService;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class FileUploaderController {

    @Autowired
    private FileUploaderService fileUploaderService;


    @PostMapping("/uploadFile")
    public String receiveImages(@RequestParam("test") int text,@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        System.out.println(text);
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String dirPath = "C:\\Users\\ASUS\\Desktop\\s";
            //https://www.jianshu.com/p/dd0f20a6e44f
            String fileName = file.getOriginalFilename();
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            String localFileName = System.currentTimeMillis() + fileSuffix;
            String filePath = dirPath + File.separator + localFileName;
            File localFile = new File(filePath);
            File imagePath = new File(dirPath);
            if (!imagePath.exists()) {
                imagePath.mkdirs();
            }
            file.transferTo(localFile);

            fileUploaderService.uploadFile(fileName,filePath);

        }

        return null;
    }

    @GetMapping("/getFilePathById")
    public FileUploader receiveImages(@RequestParam("id") Long id){
        return fileUploaderService.getFile(id);
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam("path") String path){
        File file = new File(path);
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
