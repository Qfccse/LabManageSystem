package cn.edu.tongji.backend.login.controller;

import cn.edu.tongji.backend.login.pojo.Result;
import cn.edu.tongji.backend.login.pojo.User;
import cn.edu.tongji.backend.login.service.UserService;
import cn.edu.tongji.backend.login.service.MailService;
import cn.edu.tongji.backend.login.pojo.UserVo;
import cn.edu.tongji.backend.login.pojo.UserVo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    /**
     * 登录
     * @param user 参数封装
     * @return Result
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }


    @PostMapping("/sendEmail")
    public String sendEmail(String email, HttpSession httpSession){
        mailService.sendMimeMail(email, httpSession);
        return "success";
    }

    @PostMapping("/activate")
    @ResponseBody
    public String activate(@RequestBody UserVo userVo, HttpSession session){
        mailService.registered(userVo,session);
        return "success";
    }

    @PostMapping("/findPassword")
    @ResponseBody
    public User findPassword(@RequestBody UserVo userVo, HttpSession session){
        return mailService.find(userVo,session);
    }

    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestBody UserVo2 userVo2){
        return userService.changeMinePassword(userVo2);
    }
}

