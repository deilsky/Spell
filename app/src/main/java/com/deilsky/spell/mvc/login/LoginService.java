package com.deilsky.spell.mvc.login;

import com.deilsky.spell.mvc.model.LoginModel;
import com.deilsky.spell.mvc.model.User;
import com.deilsky.spell.network.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by 帷幕 on 2017/6/15.
 */

public interface LoginService {
    @POST("Account/Login")
    Call<Result<User>> login(@Body LoginModel loginModel);
}
