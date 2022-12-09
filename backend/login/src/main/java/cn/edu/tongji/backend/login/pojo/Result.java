package cn.edu.tongji.backend.login.pojo;

import lombok.Data;

/**
 * 向前端返回信息封装
 * @param <T> 可变类型
 */
@Data
public class Result<T> {
    private String msg;
    //0 成功 1 uid错误 2 密码错误 3 未激活 4 邮箱冲突 5 验证码错误 6 邮箱状态错误 7 邮箱未找到 8 找回密码成功 9 激活成功 10 已激活不可再激活 11 邮箱更换成功
    private int errorCode;
    private T detail;
}
