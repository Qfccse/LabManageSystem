package cn.edu.tongji.backend.login.pojo;

import lombok.Data;

/**
 * 用户信息
 */
@Data
public class User {
    private String u_id;
    private String name;
    private String password;
    private int status;
    private int role;
    private String email;
    private String code;
}
