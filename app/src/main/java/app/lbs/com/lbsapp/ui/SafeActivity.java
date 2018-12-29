package app.lbs.com.lbsapp.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.HttpConstant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.ResultDTO;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Response;

public class SafeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        button = findViewById(R.id.safe_btn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.safe_btn:
                callForHelp();
                break;
        }
    }

    private void callForHelp() {
        sendSms();
        //        callPhone();
    }

    private void sendSms(Location location) {
        final String username = SharedPreferencesUtil.getStringValue(SafeActivity.this, "username");
        double longitude = location.getLongitude(); //经度
        double latitude = location.getLatitude();   //纬度
        final String locationStr = longitude + "," + latitude;
        NetUtils.getInstance().getDataAsynFromNet(HttpConstant.PLACE_NAME + "?location=" + locationStr, new NetUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", "json: " + json);
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<String>>() {
                }.getType();
                ResultDTO<String> resultDTO = gson.fromJson(json, type);
                String placeName = resultDTO.getResult();
                String msg = username + "在坐标（" + locationStr + ")处向你发出求助\n" +
                        "大致地点：" + placeName;
                SmsManager.getDefault().sendTextMessage(HttpConstant.HOSPITAL_PHONE, null, msg, null, null);
            }

            @Override
            public void failed(Call call, IOException e) {
                String msg = username + "在坐标（" + locationStr + ")处向你发出求助";
                SmsManager.getDefault().sendTextMessage(HttpConstant.HOSPITAL_PHONE, null, msg, null, null);
            }
        });
    }

    private void sendSms() {
        final String username = SharedPreferencesUtil.getStringValue(SafeActivity.this, "username");

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String msg = username + "向你发出求助";
            SmsManager.getDefault().sendTextMessage(HttpConstant.HOSPITAL_PHONE, null, msg, null, null);
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            sendSms(location);
            return;
        }

        locationManager.requestLocationUpdates(provider, 60000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                sendSms(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + HttpConstant.HOSPITAL_PHONE);
        intent.setData(uri);
        startActivity(intent);
    }

}
