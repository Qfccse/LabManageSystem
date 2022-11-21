package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.CourseMapper;
import cn.edu.tongji.backend.test.mapper.FileUpLoaderMapper;
import cn.edu.tongji.backend.test.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploaderService {
    @Autowired
    private FileUpLoaderMapper fileUpLoaderMapper;

    public void uploadImage(String name, String path) {
         fileUpLoaderMapper.uploadImage(name,path);
    }
}
