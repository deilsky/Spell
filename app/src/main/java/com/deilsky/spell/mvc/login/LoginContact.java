package com.deilsky.spell.mvc.login;


import com.deilsky.spell.mvc.model.LoginModel;

/**
 * Created by 帷幕 on 2017/6/15.
 */

public interface LoginContact {
    void login(LoginModel loginModel, LoginListener listener);
}
