package com.deilsky.spell.mvc.login;


import com.deilsky.spell.mvc.Contact;
import com.deilsky.spell.mvc.model.LoginModel;
import com.deilsky.spell.mvc.model.User;
import com.deilsky.spell.network.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 帷幕 on 2017/6/15.
 */

public class LoginContract extends Contact implements LoginInterface {
    private LoginService service = null;
    public static LoginContract instance;
    private LoginContract() {
        service = retrofit.create(LoginService.class);
    }
    public static LoginContract getInstance(){
        instance = new LoginContract();
        return instance;
    }

    @Override
    public void login(LoginModel loginModel, final LoginListener listener) {
        /*if (service != null) {*/
            Call<Result<User>> call = service.login(loginModel);
            call.enqueue(new Callback<Result<User>>() {
                @Override
                public void onResponse(Call<Result<User>> call, Response<Result<User>> response) {
                    if (response.isSuccessful() && response.code() == Contact.SUCCESS) {
                        listener.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Result<User>> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        /*} else {
            listener.onError("service 初始化失败！");
        }*/
    }
}
