package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.CourseMapper;
import cn.edu.tongji.backend.test.mapper.FileUpLoaderMapper;
import cn.edu.tongji.backend.test.pojo.Course;
import cn.edu.tongji.backend.test.pojo.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploaderService {
    @Autowired
    private FileUpLoaderMapper fileUpLoaderMapper;

    public void uploadFile(String name, String path) {
         fileUpLoaderMapper.uploadFile(name,path);
    }

    public FileUploader getFile(Long id) {
       return fileUpLoaderMapper.getFile(id);
    }
}
