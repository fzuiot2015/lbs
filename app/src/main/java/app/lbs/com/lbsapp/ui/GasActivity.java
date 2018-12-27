package app.lbs.com.lbsapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.HttpConstant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.OilPrice;
import app.lbs.com.lbsapp.bean.ProvinceBean;
import okhttp3.Call;
import okhttp3.Response;


public class GasActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private TextView InquireTv;
    private TextView price_dieselOil0;
    private TextView price_gasoline90;
    private TextView price_gasoline93;
    private TextView price_gasoline97;
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
    private void httpGetProvince(){
        NetUtils.getInstance().getDataAsynFromNet(HttpConstant.OIL_PROVINCE, new NetUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String result = response.body().string();
                ProvinceBean bean = new Gson().fromJson(result,ProvinceBean.class);
                data_list.clear();
                data_list.addAll(bean.getResult());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arr_adapter.notifyDataSetChanged();
                    }
                });

            }
            @Override
            public void failed(Call call, IOException e) {

            }

        });
    }

    private void initView() {
        InquireTv = findViewById(R.id.tv_inquire);
        InquireTv.setOnClickListener(this);
        price_dieselOil0 = findViewById(R.id.price_dieselOil0);
        price_gasoline90 = findViewById(R.id.price_gasoline90);
        price_gasoline93 = findViewById(R.id.price_gasoline93);
        price_gasoline97 = findViewById(R.id.price_gasoline97);
        price_dieselOil0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        price_gasoline90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        price_gasoline93.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        price_gasoline97.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_inquire://如果当前点击的是查询按钮，那么触发这里，因为你的按钮就叫这个名字
                //当点击查询的时候，去查询的油价
                httpGetOilPrice();
                break;
        }
    }

    private void httpGetOilPrice(){
        //get请求，请求参数，发送你请求的省份给服务器
        NetUtils.getInstance().getDataAsynFromNet(HttpConstant.INQUIRE+"?province="+province, new NetUtils.MyNetCall() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String result = response.body().string();
               //Log.e("result","result:"+result);//打印
                OilPrice bean = new Gson().fromJson(result,OilPrice.class);
                final Float dieselOil0 = bean.getResult().getDieselOil0();
                final Float gasoline90 = bean.getResult().getGasoline90();
                final Float gasoline93 = bean.getResult().getGasoline93();
                final Float gasoline97 = bean.getResult().getGasoline97();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        price_dieselOil0.setText(String.valueOf(dieselOil0+"(元/升)"));
                        price_gasoline90.setText(String.valueOf(gasoline90+"(元/升)"));
                        price_gasoline93.setText(String.valueOf(gasoline93+"(元/升)"));
                        price_gasoline97.setText(String.valueOf(gasoline97+"(元/升)"));
                    }
                });

            }
            @Override
            public void failed(Call call, IOException e) {

            }

        });
    }

}

