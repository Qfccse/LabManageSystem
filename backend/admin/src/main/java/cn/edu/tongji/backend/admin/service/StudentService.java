package cn.edu.tongji.backend.admin.service;

import cn.edu.tongji.backend.admin.mapper.StudentMapper;
import cn.edu.tongji.backend.admin.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public void addStudent(Student student){
        studentMapper.insertIntoStudent(student);
    }

    public void addTakes(String s_id,int c_id, int role){
        studentMapper.insertTakes(s_id, c_id, role);
    }

    public void deleteStudentById(String s_id){
        studentMapper.deleteStudent(s_id);
    }

    public List<Student> selectCourseStudent(int c_id){
        return studentMapper.selectCourseStudent(c_id);
    }

    public void updateTakesRole(String s_id,int c_id,int role){
        studentMapper.updateStudentTakesRole(s_id, c_id, role);
    }


}
