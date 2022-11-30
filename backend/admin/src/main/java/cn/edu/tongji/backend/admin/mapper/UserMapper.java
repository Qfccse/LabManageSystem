package cn.edu.tongji.backend.admin.mapper;


import cn.edu.tongji.backend.admin.pojo.Student;
import cn.edu.tongji.backend.admin.pojo.Teacher;
import cn.edu.tongji.backend.admin.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("insert into user values (#{u_id},#{name},#{password},#{status},#{role},#{email})")
    public void insertIntoUser(User user);

    @Select("select * from user where u_id=#{u_id}")
    public User selectUserById(String u_id);

    @Insert("insert into student values (#{s_id},#{name})")
    public void insertIntoStudent(Student student);
    @Insert("insert into teacher values (#{t_id},#{name})")
    public void insertIntoTeacher(Teacher teacher);
}
