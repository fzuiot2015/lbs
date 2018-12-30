package app.lbs.com.lbsapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.Constant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.LoginResult;
import app.lbs.com.lbsapp.bean.ResultDTO;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 用户注册界面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleTv;
    private TextView registerTv;
    private ImageView lookImg;
    private ImageView lookImg2;
    private EditText usernameEt;
    private EditText pwdEt;
    private EditText pwdEt2;
    private EditText nameEt;
    private EditText phoneEt;
    private EditText driverLicenseEt;
    private Toolbar mToolbar;
    private boolean isLook = false;
    private boolean isLook2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        initToolBar();
        titleTv = findViewById(R.id.tv_action_title);
        registerTv = findViewById(R.id.tv_register);
        registerTv.setOnClickListener(this);
        lookImg = findViewById(R.id.register_img_look);
        usernameEt = findViewById(R.id.et_register_username);
        pwdEt = findViewById(R.id.et_register_pwd);
        lookImg2 = findViewById(R.id.register_img_look_again);
        pwdEt2 = findViewById(R.id.et_register_pwd_again);
        nameEt = findViewById(R.id.et_register_name);
        phoneEt = findViewById(R.id.et_register_phone);
        driverLicenseEt = findViewById(R.id.et_register_driver_license);

        titleTv.setText("注册");
        lookImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLook) {
                    isLook = false;
                    //隐藏密码
                    pwdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    lookImg.setImageDrawable(getResources().getDrawable(R.mipmap.look));
                } else {
                    isLook = true;
                    //显示密码
                    pwdEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    lookImg.setImageDrawable(getResources().getDrawable(R.mipmap.no_look));
                }
                pwdEt.setSelection(pwdEt.getText().toString().length());
            }
        });

        lookImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLook2) {
                    isLook2 = false;
                    //隐藏密码
                    pwdEt2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    lookImg2.setImageDrawable(getResources().getDrawable(R.mipmap.look));
                } else {
                    isLook2 = true;
                    //显示密码
                    pwdEt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    lookImg2.setImageDrawable(getResources().getDrawable(R.mipmap.no_look));
                }
                pwdEt2.setSelection(pwdEt2.getText().toString().length());
            }
        });
    }

    public void initToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                final String userName = usernameEt.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    showMsg("请输入用户名");
                    return;
                }
                String pwd = pwdEt.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    showMsg("请输入密码");
                    return;
                }

                String pwd2 = pwdEt2.getText().toString().trim();
                if (TextUtils.isEmpty(pwd2)) {
                    showMsg("请再次输入密码");
                    return;
                }

                if (!pwd.equals(pwd2)) {
                    showMsg("两次输入的密码不一致");
                    return;
                }

                String name = nameEt.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    showMsg("请输入姓名");
                    return;
                }
                String phone = phoneEt.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    showMsg("请输入手机号");
                    return;
                }
                String driverLicense = driverLicenseEt.getText().toString().trim();
                if (TextUtils.isEmpty(pwd)) {
                    showMsg("请输入驾驶证号");
                    return;
                }

                Map<String, String> params = new HashMap<>();
                params.put("username", userName);
                params.put("password", pwd);
                params.put("name", name);
                params.put("phone", phone);
                params.put("driverLicense", driverLicense);
                NetUtils.getInstance().postDataAsynToNet(Constant.REGISTER, params, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Type type = new TypeToken<ResultDTO<LoginResult>>() {
                        }.getType();
                        ResultDTO<LoginResult> resultDTO = new Gson().fromJson(result, type);
                        if (resultDTO.getStatus() == 0) {
                            LoginResult loginResult = resultDTO.getResult();
                            showMsg("注册成功");
                            //保存token
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "token", loginResult.getToken());
                            SharedPreferencesUtil.saveLongValue(RegisterActivity.this, "userId", loginResult.getUserId());
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "username", userName);
                            finish();
                        } else {
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "token", "");
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "username", "");
                            showMsg(resultDTO.getMessage());
                        }
                    }
                });
                break;
        }
    }

    private void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
