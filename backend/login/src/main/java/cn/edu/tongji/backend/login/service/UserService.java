package cn.edu.tongji.backend.login.service;

import cn.edu.tongji.backend.login.pojo.User;
import cn.edu.tongji.backend.login.pojo.Result;
import cn.edu.tongji.backend.login.pojo.UserVo2;
import cn.edu.tongji.backend.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 登录
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            String u_id= userMapper.login(user);
            if (u_id==null){
                result.setMsg("用户名或密码错误");
            }else{
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(u_id);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更改密码
     */
    public String changeMinePassword(UserVo2 userVo2){
        String u_id=userMapper.login2(userVo2);
        if (u_id==null){
            return"密码错误";
        }else {
            userMapper.changePassword(userVo2);
            return"更改成功";
        }
    }
}
