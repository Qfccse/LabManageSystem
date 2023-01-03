package cn.edu.tongji.backend.login.controller;

import cn.edu.tongji.backend.login.pojo.Result;
import cn.edu.tongji.backend.login.pojo.User;
import cn.edu.tongji.backend.login.service.MailService;
import cn.edu.tongji.backend.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/userLogin")
    public Result<User> login(@RequestParam("u_id") String u_id, @RequestParam("password") String password){
        System.out.println(u_id + " " + password);
        Result<User> result = new Result<>();
        try {
            if (userService.countUser(u_id)==0)
            {
                result.setMsg("uid错误");
                result.setErrorCode(1);
            }
            else if (userService.checkPassword(u_id,password)==null){
                result.setMsg("密码错误");
                result.setErrorCode(2);
            }
            else if(userService.selectUserStatus(u_id)!=1){
                result.setMsg("未激活");
                result.setErrorCode(3);
            }
            else {
                result.setMsg("登陆成功");
                result.setErrorCode(0);
                result.setDetail(userService.selectUserInfo(u_id));
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestParam("u_id") String u_id,@RequestParam("password") String password,@RequestParam("password_new") String password_new){
        if (userService.selectCountUser(u_id, password)==0){
            return false;
        }else {
            userService.changePassword(u_id,password_new);
            return true;
        }
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email, HttpSession session){
        return mailService.mailAPI(email,session);
    }


    @PostMapping("/userActivate")
    public Result<User> userActivate(@RequestBody User user, HttpSession session){
        System.out.println(user);
        Result<User> result = new Result<>();
        if (userService.countUser(user.getU_id())==0)
        {
            result.setMsg("uid错误");
            result.setErrorCode(1);
        }
        else if(userService.selectUserStatus(user.getU_id())==1){
            result.setMsg("你已经激活");
            result.setErrorCode(10);
        }
        else if(userService.checkEmail(user.getEmail())!=0) {
            result.setMsg("邮箱已注册");
            result.setErrorCode(4);
        }
        else {
            //获取session中的验证信息
            String email = (String) session.getAttribute("email");
            String code = (String) session.getAttribute("code");
            //获取表单中的提交的验证信息
            String voCode = user.getCode();
            //如果email数据为空，或者不一致，注册失败
            if (email == null || email.isEmpty()) {
                result.setMsg("邮箱状态变化");
                result.setErrorCode(6);
            } else if (!code.equals(voCode)) {
                result.setMsg("验证码错误");
                result.setErrorCode(5);
            }
            else {
                //将数据写入数据库
                userService.updateUserEmailStatus(user.getU_id(), user.getEmail(), user.getPassword(), 1);
                result.setMsg("激活成功");
                result.setErrorCode(9);
                result.setDetail(userService.selectUserInfo(user.getU_id()));
            }
        }
        return result;
    }

    @PostMapping("/recoverUserPassword")
    public Result<User> findPassword(@RequestBody User user, HttpSession session){
        System.out.println(user);
        Result<User> result = new Result<>();
        if (userService.countUser(user.getU_id())==0)
        {
            result.setMsg("uid错误");
            result.setErrorCode(1);
        }
        else if(userService.checkEmail(user.getEmail())==0) {
            result.setMsg("邮箱未找到");
            result.setErrorCode(7);
        }
        else {
            //获取session中的验证信息
            String email = (String) session.getAttribute("email");
            String code = (String) session.getAttribute("code");
            //获取表单中的提交的验证信息
            String voCode = user.getCode();
            //如果email数据为空，或者不一致，注册失败
            if (email == null || email.isEmpty()) {
                result.setMsg("邮箱状态变化");
                result.setErrorCode(6);
            } else if (!code.equals(voCode)) {
                result.setMsg("验证码错误");
                result.setErrorCode(5);
            }
            else {
                //将数据写入数据库
                userService.changePassword(user.getU_id(),user.getPassword());
                result.setMsg("更换密码成功");
                result.setErrorCode(8);
                result.setDetail(userService.selectUserInfo(user.getU_id()));
            }
        }
        return result;
    }

    @PostMapping("/changeEmail")
    public Result<User> changeEmail(@RequestBody User user, HttpSession session){
        Result<User> result = new Result<>();
        if(userService.checkEmail(user.getEmail())!=0) {
            result.setMsg("邮箱已注册");
            result.setErrorCode(4);
        }
        else {
            //获取session中的验证信息
            String email = (String) session.getAttribute("email");
            String code = (String) session.getAttribute("code");
            //获取表单中的提交的验证信息
            String voCode = user.getCode();
            //如果email数据为空，或者不一致，注册失败
            if (email == null || email.isEmpty()) {
                result.setMsg("邮箱状态变化");
                result.setErrorCode(6);
            } else if (!code.equals(voCode)) {
                result.setMsg("验证码错误");
                result.setErrorCode(5);
            }
            else {
                result.setMsg("邮箱更换成功");
                result.setErrorCode(11);
                userService.updateEmail(user.getU_id(), user.getEmail());
            }
        }
        return result;
    }
}

