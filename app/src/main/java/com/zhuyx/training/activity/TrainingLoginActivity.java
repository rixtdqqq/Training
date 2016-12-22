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
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhuyx.training.R;
import com.zhuyx.training.base.TrainingBaseActivity;
import com.zhuyx.training.util.TrainingConstants;
import com.zhuyx.training.util.TrainingUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Map;

public class TrainingLoginActivity extends TrainingBaseActivity {

    private TextInputEditText etUserName, etPassword, etEmail;
    private Button btnLogin;
    private ImageView qqLogin;
    private Tencent mTencent;
    private boolean isServerSideLogin;

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
            mTencent = Tencent.createInstance(TrainingConstants.QQ_LOGIN_APP_ID, getApplicationContext());
            if (TextUtils.isEmpty(TrainingConstants.QQ_LOGIN_APP_ID)) {
                Toast.makeText(this, "No qq login app id,apply one first.", Toast.LENGTH_SHORT).show();
                return;
            }
            onClickLogin();
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

    private class BaseApiListener implements IRequestListener {
        @Override
        public void onComplete(JSONObject jsonObject) {

        }

        @Override
        public void onIOException(IOException e) {

        }

        @Override
        public void onMalformedURLException(MalformedURLException e) {

        }

        @Override
        public void onJSONException(JSONException e) {

        }

        @Override
        public void onConnectTimeoutException(ConnectTimeoutException e) {

        }

        @Override
        public void onSocketTimeoutException(SocketTimeoutException e) {

        }

        @Override
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {

        }

        @Override
        public void onHttpStatusException(HttpUtils.HttpStatusException e) {

        }

        @Override
        public void onUnknowException(Exception e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode,resultCode,data,new BaseUiListener());
    }

    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                return;
            }
            mTencent.logout(this);
        }
    }

    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
//            initOpenidAndToken(values);
//            updateUserInfo();
//            updateLoginButton();
        }
    };

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (jsonResponse.length() == 0) {
//                Util.showResultDialog(MainActivity.this, "返回为空", "登录失败");
                return;
            }
//            Util.showResultDialog(MainActivity.this, response.toString(), "登录成功");
            // 有奖分享处理
//            handlePrizeShare();
            doComplete((JSONObject)response);
        }

        protected void doComplete(JSONObject values) {

        }

        @Override
        public void onError(UiError e) {
        }

        @Override
        public void onCancel() {
            if (isServerSideLogin) {
                isServerSideLogin = false;
            }
        }
    }
}
