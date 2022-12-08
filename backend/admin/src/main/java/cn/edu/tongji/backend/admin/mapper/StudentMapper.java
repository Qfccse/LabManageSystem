package cn.edu.tongji.backend.admin.mapper;

import cn.edu.tongji.backend.admin.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {

    @Insert("insert into student values (#{s_id},#{name})")
    public void insertIntoStudent(Student student);

    @Delete("delete from student where s_id=#{s_id}")
    public void deleteStudent(String s_id);

    @Insert("insert into takes values(#{s_id},#{c_id},#{role},null)")
    public void insertTakes(String s_id,int c_id,int role);

    @Update("update takes set takes.role=#{role} where s_id=#{s_id} and c_id=#{c_id}")
    public void updateStudentTakesRole(String s_id,int c_id, int role);

    @Select("select DISTINCT student.s_id,student.name,takes.role from " +
            "student LEFT OUTER JOIN takes on " +
            "takes.s_id = student.s_id and c_id=#{c_id} WHERE c_id;")
    public List<Student> selectCourseStudent(int c_id);
}
