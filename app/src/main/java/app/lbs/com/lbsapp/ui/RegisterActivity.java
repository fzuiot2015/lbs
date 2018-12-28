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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.HttpConstant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.BaseBean;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleTv;
    private TextView registerTv;
    private ImageView lookImg;
    private EditText usernameEt;
    private EditText pwdEt;
    private EditText nameEt;
    private EditText phoneEt;
    private EditText addressEt;
    private EditText driverLicenseEt;
    private Toolbar mToolbar;
    private boolean isLook = false;

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
        registerTv = findViewById(R.id.tv_to_register);
        registerTv.setOnClickListener(this);
        lookImg = findViewById(R.id.img_look);
        usernameEt = findViewById(R.id.et_register_username);
        pwdEt = findViewById(R.id.et_register_pwd);

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
            case R.id.tv_to_register:
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
                params.put("pass", pwd);
                params.put("name", name);
                params.put("phone", phone);
                params.put("driverLicense", driverLicense);
                NetUtils.getInstance().postDataAsynToNet(HttpConstant.REGISTER, params, new NetUtils.MyNetCall() {
                    @Override
                    public void success(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        BaseBean bean = new Gson().fromJson(result, BaseBean.class);
                        if (bean.getStatus() == 0) {
                            showMsg("注册成功");
                            //保存token
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "token", bean.getResult());
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "username", userName);
                            finish();
                        } else {
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "token", "");
                            SharedPreferencesUtil.saveStringValue(RegisterActivity.this, "username", "");
                            showMsg(bean.getMessage());
                        }
                    }

                    @Override
                    public void failed(Call call, IOException e) {
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
