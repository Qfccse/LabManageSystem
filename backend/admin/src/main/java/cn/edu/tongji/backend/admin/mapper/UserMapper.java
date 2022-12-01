package cn.edu.tongji.backend.admin.mapper;


import cn.edu.tongji.backend.admin.pojo.Student;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import cn.edu.tongji.backend.admin.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Insert("insert into user values (#{u_id},#{name},#{password},#{status},#{role},#{email})")
    public void insertIntoUser(User user);

    @Select("select * from user where u_id=#{u_id}")
    public User selectUserById(String u_id);

    @Insert("insert into student values (#{s_id},#{name})")
    public void insertIntoStudent(Student student);

    @Insert("insert into teacher values (#{t_id},#{name})")
    public void insertIntoTeacher(Teacher teacher);

    @Select("select * from user")
    public List<User> selectAllUser();

    @Delete("delete from user where u_id=#{u_id}")
    public void deleteUser(String u_id);

    @Delete("delete from student where s_id=#{s_id}")
    public void deleteStudent(String s_id);

    @Delete("delete from teacher where t_id=#{t_id}")
    public void deleteTeacher(String t_id);

    @Insert("insert into takes values(#{s_id},#{c_id},#{role},null)")
    public void insertTakes(String s_id,int c_id,int role);

    @Insert("insert into teaches values(#{t_id},#{c_id},#{role})")
    public void insertTeaches(String t_id,int c_id,int role);
}
