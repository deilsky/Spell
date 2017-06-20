package com.deilsky.spell.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deilsky.spell.R;
import com.deilsky.spell.mvc.login.LoginContactImpl;
import com.deilsky.spell.mvc.login.LoginListener;
import com.deilsky.spell.mvc.model.LoginModel;
import com.deilsky.spell.mvc.model.User;
import com.deilsky.spell.network.Result;
import com.sdsmdg.tastytoast.TastyToast;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private EditText mUsernameView, mPasswordView;
    private View mLoginFormView;
    private LoginContactImpl contact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contact = LoginContactImpl.getInstance();
        // Set up the login form.

        mUsernameView = (EditText) findViewById(R.id.username);
        mUsernameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    mPasswordView.requestFocus();
                    return true;
                }
                return false;
            }
        });
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                TastyToast.makeText(getApplicationContext(), "失败", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }

    private void attemptLogin() {
        // Reset errors.
        mPasswordView.setError(null);
        mUsernameView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            LoginModel loginModel = new LoginModel();
            loginModel.setUsername(username);
            loginModel.setPassword(password);
            contact.login(loginModel,this);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onSuccess(Result<User> result) {
    }

    @Override
    public void onError(String msg) {
        TastyToast.makeText(getApplicationContext(), msg, TastyToast.LENGTH_LONG, TastyToast.ERROR);
    }
}

