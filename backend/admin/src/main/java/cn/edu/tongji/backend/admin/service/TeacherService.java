package cn.edu.tongji.backend.admin.service;

import cn.edu.tongji.backend.admin.mapper.TeacherMapper;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    public void addTeacher(Teacher teacher){
        teacherMapper.insertIntoTeacher(teacher);
    }

    public void addTeaches(String s_id,int c_id, int role){
        teacherMapper.insertTeaches(s_id, c_id, role);
    }

    public void deleteTeacherById(String t_id){
        teacherMapper.deleteTeacher(t_id);
    }


    public List<Teacher> selectCourseTeacher(String name){
        return teacherMapper.selectCourseTeacher(name);
    }


    public void updateTeachesRole(String t_id,int c_id,int role){
        teacherMapper.updateTeacherTeachesRole(t_id, c_id, role);
    }
}
