package cn.edu.tongji.backend.course.mapper;

import cn.edu.tongji.backend.course.pojo.Teaches;
import cn.edu.tongji.backend.course.pojo.info.TeacherForCourse;

import java.util.List;

public interface TeachesMapper {
    void addTeaches(Teaches teaches);

    List<TeacherForCourse> getTeachersByCid(int c_id);
}
