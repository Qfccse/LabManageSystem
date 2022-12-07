package cn.edu.tongji.backend.login.mapper;

import cn.edu.tongji.backend.login.pojo.User;
import cn.edu.tongji.backend.login.pojo.UserVo;
import cn.edu.tongji.backend.login.pojo.UserVo2;
import org.apache.ibatis.annotations.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Repository;

/**
 * mapper的具体表达式
 */
//@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
//@Repository
public interface UserMapper {
    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param username
     * @return
     */
    @Select(value = "select u.name,u.password from user u where u.name=#{username}")
    @Results
            ({@Result(property = "username",column = "username"),
              @Result(property = "password",column = "password")})
    User findUserByName(@Param("username") String username);

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select u_id from user  where u_id = #{u_id} and password = #{password}")
    String login(User user);


    @Select("select u_id from user  where u_id = #{u_id} and password = #{password}")
    String login2(UserVo2 userVo2);

    @Select("select u_id from user  where u_id = #{u_id} and password = #{password}")
    String check(UserVo user);

    @Update("update user set email=#{email},status=1 where u_id=#{u_id}")
    void insertEmailAndStatus(UserVo userVo);

    @Select("select email from user where email=#{email}")
    String checkEmail(UserVo user);

    @Select("select * from user where email=#{email}")
    User findPassword(UserVo userVo);

    @Update("update user set password=#{newPassword} where u_id=#{u_id}")
    void changePassword(UserVo2 userVo2);
}
