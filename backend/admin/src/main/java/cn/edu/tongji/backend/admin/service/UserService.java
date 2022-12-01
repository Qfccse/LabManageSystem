package cn.edu.tongji.backend.admin.service;

import cn.edu.tongji.backend.admin.mapper.UserMapper;
import cn.edu.tongji.backend.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.insertIntoUser(user);
    }


    public User findUser(String s_id){
      return userMapper.selectUserById(s_id);
    }

    public List<User> selectAllUser(){
        return userMapper.selectAllUser();
    }

    public void deleteUserById(String u_id){
        userMapper.deleteUser(u_id);
    }
}
