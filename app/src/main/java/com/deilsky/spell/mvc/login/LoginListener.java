package com.deilsky.spell.mvc.login;


import com.deilsky.spell.mvc.model.User;
import com.deilsky.spell.network.Result;
/**
 * Created by 帷幕 on 2017/6/15.
 */

public interface LoginListener {
    void onSuccess(Result<User> result);
    void onError(String msg);
}
