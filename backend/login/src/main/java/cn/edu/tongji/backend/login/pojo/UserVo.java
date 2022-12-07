package cn.edu.tongji.backend.login.pojo;

import lombok.Data;

@Data
public class UserVo {
    private String u_id;
    private String password;
    private String email;
    //    验证码
    private String code;

    //省略了get和set方法，自己生成一下
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
