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

    public void addTakes(String s_id,int c_id, int role){
        userMapper.insertTakes(s_id, c_id, role);
    }

    public void addTeaches(String s_id,int c_id, int role){
        userMapper.insertTeaches(s_id, c_id, role);
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

    public List<Student> selectCourseStudent(int c_id){
        return userMapper.selectCourseStudent(c_id);
    }

    public List<Teacher> selectCourseTeacher(String name){
        return userMapper.selectCourseTeacher(name);
    }

    public void updateTakesRole(String s_id,int c_id,int role){
        userMapper.updateStudentTakesRole(s_id, c_id, role);
    }

    public void updateTeachesRole(String t_id,int c_id,int role){
        userMapper.updateTeacherTeachesRole(t_id, c_id, role);
    }
}
