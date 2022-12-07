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

    private String status;

    private String role;

    private String email;

    public String getId() {
        return u_id;
    }

    public void setId(String id) {
        this.u_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
