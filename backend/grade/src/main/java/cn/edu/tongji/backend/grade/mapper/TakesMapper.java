package cn.edu.tongji.backend.grade.mapper;

import cn.edu.tongji.backend.grade.pojo.Takes;

import java.util.List;

public interface TakesMapper {

    List<Takes> getStudentsByCourseID(int c_id);

}
