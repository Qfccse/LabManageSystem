package cn.edu.tongji.backend.admin.pojo;

import lombok.Data;

@Data
public class User {
    private String u_id;
    private String name;
    private String password;
    private int status;
    private int role;
    private String email;
}
