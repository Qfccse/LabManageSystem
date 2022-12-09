package cn.edu.tongji.backend.course.mapper;

import cn.edu.tongji.backend.course.pojo.info.StudentForCourse;

import java.util.List;

public interface TakesMapper {

    List<StudentForCourse> getStudentsByCid(int c_id);

}
