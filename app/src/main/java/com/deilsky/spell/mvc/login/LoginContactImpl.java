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

public class LoginContactImpl extends Contact implements LoginContact {
    private LoginGitHubService service = null;
    public static LoginContactImpl instance;
    private LoginContactImpl() {
        service = retrofit.create(LoginGitHubService.class);
    }
    public static LoginContactImpl getInstance(){
        instance = new LoginContactImpl();
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
