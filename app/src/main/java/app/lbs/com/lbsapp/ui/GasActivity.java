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

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.Constant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.OilPrice;
import app.lbs.com.lbsapp.bean.ResultDTO;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * 油价查询界面
 */
public class GasActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private TextView InquireTv;
    private TextView priceDieselOil0;
    private TextView priceGasoline90;
    private TextView priceGasoline93;
    private TextView priceGasoline97;
    private String province;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);
        //初始化控件要放在请求数据前面
        initView();
        spinner = (Spinner) findViewById(R.id.spinner_province);
        //数据
        data_list = new ArrayList<String>();
        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                province = data_list.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //省份是默认加载的，不需要点击按钮
        httpGetProvince();

    }

    //请求省份接口
    private void httpGetProvince() {
        NetUtils.getInstance().getDataAsynFromNet(Constant.OIL_PROVINCE, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", json);//打印
                Type type = new TypeToken<ResultDTO<List<String>>>() {
                }.getType();
                ResultDTO<List<String>> resultDTO = new Gson().fromJson(json, type);
                data_list.clear();
                data_list.addAll(resultDTO.getResult());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arr_adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initView() {
        InquireTv = findViewById(R.id.tv_inquire);
        InquireTv.setOnClickListener(this);
        priceDieselOil0 = findViewById(R.id.price_dieselOil0);
        priceGasoline90 = findViewById(R.id.price_gasoline90);
        priceGasoline93 = findViewById(R.id.price_gasoline93);
        priceGasoline97 = findViewById(R.id.price_gasoline97);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_inquire://如果当前点击的是查询按钮，那么触发这里，因为你的按钮就叫这个名字
                //当点击查询的时候，去查询的油价
                httpGetOilPrice();
                break;
        }
    }

    private void httpGetOilPrice() {
        //get请求，请求参数，发送你请求的省份给服务器
        NetUtils.getInstance().getDataAsynFromNet(Constant.INQUIRE + "?province=" + province, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.e("json", "json:" + json);//打印
                Type type = new TypeToken<ResultDTO<OilPrice>>() {
                }.getType();
                ResultDTO<OilPrice> resultDTO = new Gson().fromJson(json, type);
                OilPrice oilPrice = resultDTO.getResult();
                final Float dieselOil0 = oilPrice.getDieselOil0();
                final Float gasoline90 = oilPrice.getGasoline90();
                final Float gasoline93 = oilPrice.getGasoline93();
                final Float gasoline97 = oilPrice.getGasoline97();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        priceDieselOil0.setText(String.valueOf(dieselOil0 + "(元/升)"));
                        priceGasoline90.setText(String.valueOf(gasoline90 + "(元/升)"));
                        priceGasoline93.setText(String.valueOf(gasoline93 + "(元/升)"));
                        priceGasoline97.setText(String.valueOf(gasoline97 + "(元/升)"));
                    }
                });
            }
        });
    }

}

