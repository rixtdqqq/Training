package com.zhuyx.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.util.TrainingUtils;

public class TrainingLoginActivity extends TrainingBaseActivity {

    private TextInputEditText etUserName, etPassword, etEmail;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.training_a_login;
    }

    @Override
    public void initView() {
        etUserName = (TextInputEditText) findViewById(R.id.training_user_name);
        etPassword = (TextInputEditText) findViewById(R.id.training_user_pwd);
        etEmail = (TextInputEditText) findViewById(R.id.training_user_email);
        btnLogin = (Button) findViewById(R.id.training_user_login);
    }

    @Override
    public void loadFragment() {

    }

    private void initListener() {
        btnLogin.setOnClickListener(v -> {
            if (checkNotNull()) {
                startActivity(new Intent(this,TrainingMainActivity.class));
                finish();
            }
        });
        etEmail.setOnEditorActionListener((view,actionId,event)->{
            if (actionId == EditorInfo.IME_ACTION_GO) {
                if (checkNotNull()) {
                    startActivity(new Intent(this,TrainingMainActivity.class));
                    finish();
                }
            }
            return false;
        });
    }

    private boolean checkNotNull() {
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            etUserName.setError("请输入用户名");
            return false;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("请输入密码");
            return false;
        } else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("请输入正确的邮箱");
            return false;
        } else {
            TrainingUtils.saveUsername(TrainingLoginActivity.this,userName,password);
            return true;
        }
    }
}
