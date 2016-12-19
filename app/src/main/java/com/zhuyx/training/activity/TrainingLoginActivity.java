package com.zhuyx.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;

import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.util.TrainingConstants;
import com.zhuyx.training.util.TrainingUtils;

import java.util.Map;

public class TrainingLoginActivity extends TrainingBaseActivity {

    private TextInputEditText etUserName, etPassword, etEmail;
    private Button btnLogin;
    private ImageView qqLogin;

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
        qqLogin = (ImageView) findViewById(R.id.training_qq_login);
    }

    @Override
    public void loadFragment() {

    }

    private void initListener() {
        btnLogin.setOnClickListener(v -> {
            if (checkNotNull()) {
                startActivity(new Intent(this, TrainingMainActivity.class));
                finish();
            }
        });
        etEmail.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                if (checkNotNull()) {
                    startActivity(new Intent(this, TrainingMainActivity.class));
                    finish();
                }
            }
            return false;
        });
        qqLogin.setOnClickListener(v -> {
            
        });
    }

    /**
     * 判空
     */

    private boolean checkNotNull() {
        String userName = etUserName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            etUserName.setError(getString(R.string.training_input_correct_username));
            return false;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError(getString(R.string.training_input_correct_password));
            return false;
        } else if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.training_input_correct_email));
            return false;
        } else {
            Map<String, String> loginUser = TrainingUtils.getUsername(this);
            if (TextUtils.equals(userName, loginUser.get(TrainingConstants.USERNAME_KEY))
                    && TextUtils.equals(password, loginUser.get(TrainingConstants.PASSWORD_KEY))) {
                return true;
            } else {
                Snackbar.make(btnLogin, R.string.training_user_name_pwd_not_correct, Snackbar.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
