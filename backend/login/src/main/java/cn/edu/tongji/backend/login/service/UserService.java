package cn.edu.tongji.backend.login.service;

import cn.edu.tongji.backend.login.pojo.User;
import cn.edu.tongji.backend.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 登录
     * @param user 用户名和密码
     * @return Result
     */
    public String checkPassword(String u_id, String password) {
        return userMapper.checkUserPassword(u_id, password);
    }

    public int countUser(String u_id){
        return userMapper.countUser(u_id);
    }
    /**
     * 更改密码
     */
    public void changePassword(String u_id,String password_new){
        userMapper.updateUserPassword(u_id,password_new);
    }

    public int selectCountUser(String u_id,String password){
        return userMapper.selectCountUser(u_id,password);
    }

    public User selectUserInfo(String u_id){
        return userMapper.checkDetail(u_id);
    }

    public int selectUserStatus(String u_id){
        return userMapper.selectUserStatus(u_id);
    }

    public void updateUserEmailStatus(String u_id, String email,String password,int status){
        userMapper.insertEmailAndStatus(u_id, email, password,status);
    }

    public int checkEmail(String email){
        return userMapper.checkEmail(email);
    }

    public void updateEmail(String u_id, String email){
        userMapper.updateUserEmail(u_id, email);
    }
}
