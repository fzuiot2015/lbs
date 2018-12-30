package app.lbs.com.lbsapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.Constant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.Limit;
import app.lbs.com.lbsapp.bean.ResultDTO;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LimitActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> cityList;
    private List<String> cityNameList;
    private ArrayAdapter<String> arrayAdapter;
    private Spinner spinner;
    private TextView button;
    private String city;
    private TextView date;
    private TextView area;
    private TextView numberRule;
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit);
        date = findViewById(R.id.limit_date);
        area = findViewById(R.id.limit_area);
        numberRule = findViewById(R.id.limit_numberRule);
        number = findViewById(R.id.limit_number);
        button = findViewById(R.id.limit_btn_quire);
        button.setOnClickListener(this);
        spinner = findViewById(R.id.limit_spinner_city);
        //数据
        cityList = new ArrayList<>();
        cityNameList = new ArrayList<>();
        //适配器
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityNameList);
        //设置样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = cityList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        httpGetCity();
    }

    private void httpGetCity() {
        NetUtils.getInstance().getDataAsynFromNet(Constant.LIMIT_CITY, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", json);//打印
                Type type = new TypeToken<ResultDTO<Map<String, String>>>() {
                }.getType();
                ResultDTO<Map<String, String>> resultDTO = new Gson().fromJson(json, type);
                Map<String, String> cityMap = resultDTO.getResult();
                cityList.clear();
                cityList.addAll(cityMap.keySet());
                cityNameList.clear();
                cityNameList.addAll(cityMap.values());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.limit_btn_quire://如果当前点击的是查询按钮，那么触发这里，因为你的按钮就叫这个名字
                httpGetLimit();
                break;
        }
    }

    private void httpGetLimit() {
        //get请求，请求参数，发送你请求的省份给服务器
        NetUtils.getInstance().getDataAsynFromNet(Constant.LIMIT + "?city=" + city, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", "json:" + json);//打印
                Type type = new TypeToken<ResultDTO<Limit>>() {
                }.getType();
                ResultDTO<Limit> resultDTO = new Gson().fromJson(json, type);
                final Limit limit = resultDTO.getResult();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        date.setText(limit.getDate());
                        area.setText(limit.getArea());
                        numberRule.setText(limit.getNumberRule());
                        number.setText(limit.getNumber());
                    }
                });

            }
        });
    }

}
