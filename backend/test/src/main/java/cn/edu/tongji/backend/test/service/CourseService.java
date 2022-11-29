package cn.edu.tongji.backend.test.service;

import cn.edu.tongji.backend.test.mapper.CourseMapper;
import cn.edu.tongji.backend.test.pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseMapper userMapper;

    public Course queryById(Long id) {
        return userMapper.selectById(id);
    }

    public void insert(int num){
        userMapper.insertTest(num);
    }
}
