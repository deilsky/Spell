package com.deilsky.spell.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.deilsky.spell.R;
import com.deilsky.spell.mvc.login.LoginContract;
import com.deilsky.spell.mvc.login.LoginListener;
import com.deilsky.spell.mvc.model.User;
import com.deilsky.spell.network.Result;
import com.sdsmdg.tastytoast.TastyToast;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private LoginContract contact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contact = LoginContract.getInstance();


    }

    @Override
    public void onSuccess(Result<User> result) {
    }

    @Override
    public void onError(String msg) {
        TastyToast.makeText(getApplicationContext(), msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
    }
}

