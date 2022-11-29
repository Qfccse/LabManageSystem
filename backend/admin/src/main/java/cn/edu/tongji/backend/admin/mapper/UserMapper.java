package cn.edu.tongji.backend.admin.mapper;


import cn.edu.tongji.backend.admin.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Insert("insert into user values (#{u_id},#{name},#{password},#{status},#{role},#{email})")
    public void insertIntoUser(User user);

    @Select("select * from user where u_id=#{u_id}")
    public User selectUserById(String u_id);
}
