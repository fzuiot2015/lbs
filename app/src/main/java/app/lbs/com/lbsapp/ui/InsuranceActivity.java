package app.lbs.com.lbsapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.adapter.InsuranceAdapter;
import app.lbs.com.lbsapp.api.HttpConstant;
import app.lbs.com.lbsapp.bean.Insurance;
import app.lbs.com.lbsapp.bean.ResultDTO;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InsuranceActivity extends AppCompatActivity {
    private InsuranceAdapter insuranceAdapter;
    private List<Insurance> insuranceList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        insuranceAdapter = new InsuranceAdapter(this, R.layout.item_insurance, insuranceList);
        listView = findViewById(R.id.list_insurance);
        listView.setAdapter(insuranceAdapter);
        getData();
    }

    public void getData() {
        Long userId = SharedPreferencesUtil.getLongValue(InsuranceActivity.this, "userId");
        final Request request = new Request.Builder()
                .url(HttpConstant.INSURANCE_INFO + "?userId=" + userId).build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.e("json", "json: " + json);
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<List<Insurance>>>() {
                }.getType();
                ResultDTO<List<Insurance>> resultDTO = gson.fromJson(json, type);
                if (resultDTO.getStatus() == 0) {
                    insuranceList.clear();
                    insuranceList.addAll(resultDTO.getResult());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            insuranceAdapter.notifyDataSetChanged();
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(InsuranceActivity.this, "保险信息加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
