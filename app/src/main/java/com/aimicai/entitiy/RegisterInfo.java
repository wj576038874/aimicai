package com.aimicai.entitiy;

import java.io.Serializable;

/**
 * ProjectName: aimicai
 * PackageName com.aimicai.entitiy
 * Author: wenjie
 * Date: 2019-05-09 18:15
 * Description:
 */
public class RegisterInfo implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
