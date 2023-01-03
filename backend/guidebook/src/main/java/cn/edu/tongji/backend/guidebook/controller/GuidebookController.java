package cn.edu.tongji.backend.guidebook.controller;

import cn.edu.tongji.backend.guidebook.pojo.Guidebook;
import cn.edu.tongji.backend.guidebook.service.GuidebookService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/guidebook")
public class GuidebookController {
    @Autowired
    GuidebookService guidebookService;

    @GetMapping("/getLabGuidebooks")
    public List<Guidebook> getAllGuidebooks(@RequestParam("l_id") int l_id){
        return guidebookService.findAllGuidebook(l_id);
    }

    @GetMapping("/getCourseGuidebooks")
    public List<Guidebook> getCourseGuidebooks(@RequestParam("c_id") int c_id){
        List<Guidebook> courseGuidebook = guidebookService.findCourseGuidebook(c_id);
        return courseGuidebook;
    }

    @PostMapping("/postGuidebookNewName")
    public void postGuidebookNewName(@RequestParam("g_id") int g_id,@RequestParam("name")String name){
        guidebookService.renewGuidebookName(g_id,name);
    }

    @DeleteMapping("/deleteGuidebook")
    public void removeGuidebook(@RequestParam("g_id")int g_id){
        guidebookService.deleteGuidebook(g_id);
    }

    @PostMapping("/postLabGuidebook")
    public String uploadGuideBook(@RequestParam("id")String guidebook, @RequestParam("file") MultipartFile[]  multipartFile) throws IOException {
        System.out.println(guidebook);
        Guidebook book = JSON.parseObject(guidebook,Guidebook.class);
        System.out.println(book);
        for (MultipartFile file : multipartFile) {
            System.out.println("file is " + file.getOriginalFilename());
            String dirPath = "D:\\TJ-LabResource";
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
            // 保存文件到路径
            file.transferTo(localFile);

            book.setName(fileName);
            book.setPath(localFileName);
            // 保存路径到服务器
            guidebookService.addGuidebook(book);
        }

        return null;
    }

    @GetMapping("/getGuidebookBytes")
    public void download(HttpServletResponse response, @RequestParam("g_id") int g_id){
        Guidebook guidebook = guidebookService.findGuidebook(g_id);
        File file = new File("D:\\TJ-LabResource" + File.separator + guidebook.getPath());
        //System.out.println(file.exists());
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
