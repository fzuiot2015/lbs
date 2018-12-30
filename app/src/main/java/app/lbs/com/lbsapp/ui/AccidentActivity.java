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
import app.lbs.com.lbsapp.adapter.AccidentAdapter;
import app.lbs.com.lbsapp.api.Constant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.Accident;
import app.lbs.com.lbsapp.bean.ResultDTO;
import app.lbs.com.lbsapp.utils.SharedPreferencesUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AccidentActivity extends AppCompatActivity {
    private AccidentAdapter accidentAdapter;
    private List<Accident> accidentList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);
        accidentAdapter = new AccidentAdapter(this, R.layout.item_accident, accidentList);
        listView = findViewById(R.id.list_accident);
        listView.setAdapter(accidentAdapter);
        getData();
    }

    public void getData() {
        Long userId = SharedPreferencesUtil.getLongValue(AccidentActivity.this, "userId");

        NetUtils.getInstance().getDataAsynFromNet(Constant.ACCIDENT + "?userId=" + userId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.e("json", "json: " + json);
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<List<Accident>>>() {
                }.getType();
                ResultDTO<List<Accident>> resultDTO = gson.fromJson(json, type);
                if (resultDTO.getStatus() == 0) {
                    accidentList.clear();
                    accidentList.addAll(resultDTO.getResult());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            accidentAdapter.notifyDataSetChanged();
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(AccidentActivity.this, "事故信息加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
