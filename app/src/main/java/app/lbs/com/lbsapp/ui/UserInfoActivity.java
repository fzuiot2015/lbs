package app.lbs.com.lbsapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.HttpConstant;
import app.lbs.com.lbsapp.bean.Car;
import app.lbs.com.lbsapp.bean.ResultDTO;
import app.lbs.com.lbsapp.bean.User;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserInfoActivity extends AppCompatActivity {

    TextView username;
    TextView name;
    TextView phone;

    TextView vin;
    TextView plate;
    TextView vehicleType;
    TextView engine;
    TextView model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        username = findViewById(R.id.info_username);
        name = findViewById(R.id.info_name);
        phone = findViewById(R.id.info_phone);

        vin = findViewById(R.id.info_vin);
        plate = findViewById(R.id.info_plate);
        vehicleType = findViewById(R.id.info_vehicle_type);
        engine = findViewById(R.id.info_engine);
        model = findViewById(R.id.info_model);

        getUserInfo();
        getCarInfo();
    }


    private void getUserInfo() {
        Long userId = SharedPreferencesUtil.getLongValue(UserInfoActivity.this, "userId");
        final Request request = new Request.Builder()
                .url(HttpConstant.USER_INFO + "?userId=" + userId).build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", "json: " + json);
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<User>>() {
                }.getType();
                ResultDTO<User> resultDTO = gson.fromJson(json, type);
                final User user = resultDTO.getResult();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        username.setText(user.getUsername());
                        name.setText(user.getName());
                        phone.setText(user.getPhone());
                    }
                });
            }
        });
    }

    private void getCarInfo() {
        Long userId = SharedPreferencesUtil.getLongValue(UserInfoActivity.this, "userId");
        final Request request = new Request.Builder()
                .url(HttpConstant.CAR_INFO + "?userId=" + userId).build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", "json: " + json);
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<List<Car>>>() {
                }.getType();
                ResultDTO<List<Car>> resultDTO = gson.fromJson(json, type);
                List<Car> carList = resultDTO.getResult();
                if (carList == null) {
                    return;
                }

                final Car car = carList.get(0);
                if (car == null) {
                    return;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vin.setText(car.getVin());
                        plate.setText(car.getPlate());
                        vehicleType.setText(car.getVehicleType());
                        engine.setText(car.getEngine());
                        model.setText(car.getModel());
                    }
                });
            }
        });
    }
}
