package cn.edu.tongji.backend.admin.service;

import cn.edu.tongji.backend.admin.mapper.UserMapper;
import cn.edu.tongji.backend.admin.pojo.Student;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import cn.edu.tongji.backend.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> selectAllUser(){
        return userMapper.selectAllUser();
    }

    public void deleteUserById(String u_id){
        userMapper.deleteUser(u_id);
    }

    public void deleteStudentById(String s_id){
        userMapper.deleteStudent(s_id);
    }

    public void deleteTeacherById(String t_id){
        userMapper.deleteTeacher(t_id);
    }
}
