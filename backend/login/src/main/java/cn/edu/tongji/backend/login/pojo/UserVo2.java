package cn.edu.tongji.backend.login.pojo;

import lombok.Data;

@Data
public class UserVo2 {
    private String u_id;

    private String password;
    //    新密码
    private String newPassword;

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

