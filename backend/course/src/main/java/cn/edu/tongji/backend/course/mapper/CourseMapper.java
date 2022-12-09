package cn.edu.tongji.backend.course.mapper;

import cn.edu.tongji.backend.course.pojo.Course;
import cn.edu.tongji.backend.course.pojo.info.CourseAndRole;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface CourseMapper {



    Course getById(int id);


    /**
     * 条件查询
     * 共有三种方式
     * @return
     */
    List<Course> getByCondition(@Param("status")  int status, @Param("name") String name);

    List<Course> getByCondition(Course course);

    List<Course> getByCondition(Map map);

    List<Course> getByConditionSingle(Course course);

    /**
     * collection array list参数都要用Param改名 否则就得用默认的  不推荐
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);


    /**
     * 业务功能  上面是练习
     */

    List<Course> getAllCourses();

    List<CourseAndRole> getAllCoursesAsTeacher(@Param("t_id") String t_id);

    List<CourseAndRole> getAllCoursesAsStudent(@Param("s_id") String s_id);

    void addCourse(Course course);

    int updateCourse(Course course);

    void deleteById(int c_id);

}
