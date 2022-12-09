package cn.edu.tongji.backend.login.mapper;

import cn.edu.tongji.backend.login.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * mapper的具体表达式
 */
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param username
     * @return
     */
    @Select("select count(*) from user where u_id = #{u_id}")
    int countUser(String u_id);

    @Select("select u_id from user  where u_id = #{u_id} and user.password = #{password}")
    String checkUserPassword(String u_id, String password);

    @Select("select u_id,status,role,name,email from user where u_id=#{u_id}")
    User checkDetail(String u_id);

    @Update("update user set email=#{email},user.password=#{password},user.status=#{status} where u_id=#{u_id}")
    void insertEmailAndStatus(String u_id,String email,String password,int status);

    @Select("select count(*) from user where email=#{email}")
    int checkEmail(String email);

    @Update("update user set user.password=#{password_new} where u_id=#{u_id}")
    void updateUserPassword(String u_id,String password_new);

    @Select("select count(*) from user where u_id=#{u_id} and user.password=#{password}")
    int selectCountUser(String u_id,String password);

    @Select("select status from user where u_id=#{u_id}")
    int selectUserStatus(String u_id);

    @Update("update user set user.email=#{email} where u_id=u_id")
    void updateUserEmail(String u_id,String email);

}
