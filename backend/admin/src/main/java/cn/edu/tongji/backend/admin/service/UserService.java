package cn.edu.tongji.backend.admin.service;

import cn.edu.tongji.backend.admin.mapper.UserMapper;
import cn.edu.tongji.backend.admin.pojo.Student;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import cn.edu.tongji.backend.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insertIntoUser(user);
    }

    public void addTeacher(Teacher teacher){
        userMapper.insertIntoTeacher(teacher);
    }

    public void addStudent(Student student){
        userMapper.insertIntoStudent(student);
    }
    public User findUser(String s_id){
      return userMapper.selectUserById(s_id);
    }

}
