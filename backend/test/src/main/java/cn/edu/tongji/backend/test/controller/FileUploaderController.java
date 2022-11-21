package cn.edu.tongji.backend.test.controller;

import cn.edu.tongji.backend.test.service.FileUploaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/test")
public class FileUploaderController {

    @Autowired
    private FileUploaderService fileUploaderService;


    @PostMapping("/uploadImage")
    public String receiveImages(@RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
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

            fileUploaderService.uploadImage(fileName,filePath);

        }

        return null;
    }
}
