package cn.edu.tongji.backend.admin.mapper;

import cn.edu.tongji.backend.admin.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TeacherMapper {

    @Insert("insert into teacher values (#{t_id},#{name})")
    public void insertIntoTeacher(Teacher teacher);

    @Delete("delete from teacher where t_id=#{t_id}")
    public void deleteTeacher(String t_id);


    @Insert("insert into teaches values(#{t_id},#{c_id},#{role})")
    public void insertTeaches(String t_id,int c_id,int role);


    @Update("update teaches set teaches.role=#{role} where t_id=#{t_id} and c_id=#{c_id}")
    public void updateTeacherTeachesRole(String t_id,int c_id, int role);


    //@Select("select * from user where u_id in " +
    //        "(select t_id from teaches where c_id in " +
    //        "(select c_id from course where course.name like (#{name}+'%')))")
    //public List<User> selectCourseTeacher(String name);
    @Select("SELECT teacher.t_id,teacher.name,teaches.c_id,teaches.role " +
            "from teaches LEFT JOIN  teacher " +
            "on teacher.t_id=teaches.t_id " +
            "where c_id in " +
            "(select c_id from course " +
            "where course.name=#{name})")
    //where course.name like concat('%',#{name},'%')
    public List<Teacher> selectCourseTeacher(String name);
}
