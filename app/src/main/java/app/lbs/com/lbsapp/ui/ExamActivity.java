package app.lbs.com.lbsapp.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import app.lbs.com.lbsapp.R;
import app.lbs.com.lbsapp.api.Constant;
import app.lbs.com.lbsapp.api.NetUtils;
import app.lbs.com.lbsapp.bean.Exam;
import app.lbs.com.lbsapp.bean.ResultDTO;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {
    private Exam exam;
    private ImageView imageView;
    private TextView question;

    private RadioGroup radioGroup;

    private Button confirmBtn;
    private Button nextBtn;

    private TextView answer;
    private TextView explains;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ctx = this;

        imageView = findViewById(R.id.exam_img);
        question = findViewById(R.id.exam_question);

        radioGroup = findViewById(R.id.exam_items);


        confirmBtn = findViewById(R.id.exam_confirm);
        nextBtn = findViewById(R.id.exam_next);

        answer = findViewById(R.id.exam_result);
        explains = findViewById(R.id.exam_explains);
        confirmBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        httpGetQuestion();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.exam_confirm:
                showAnswer();
                break;
            case R.id.exam_next:
                httpGetQuestion();
                break;
        }
    }

    private void showAnswer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String explains = "拓展： " + exam.getExplains();
                ExamActivity.this.explains.setText(Html.fromHtml(explains));
                int answerIndex = Integer.valueOf(exam.getAnswer()) - 1;
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                String answerText;
                if (checkedRadioButtonId == answerIndex) {
                    answerText = "正确，答案是： " + getIndexPrefix(answerIndex);
                    answer.setTextColor(getResources().getColor(R.color.green));
                } else {
                    answerText = "错误，答案是： " + getIndexPrefix(answerIndex);
                    answer.setTextColor(getResources().getColor(R.color.red));
                }
                answer.setText(answerText);
            }
        });
    }

    private void httpGetQuestion() {
        NetUtils.getInstance().getDataAsynFromNet(Constant.Exam + "?subject=1&type=c1", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<ResultDTO<Exam>>() {
                }.getType();
                ResultDTO<Exam> resultDTO = gson.fromJson(result, type);
                exam = resultDTO.getResult();

                setImg(exam.getUrl());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        question.setText(exam.getQuestion());
                        radioGroup.removeAllViews();
                        setRadio(exam.getItem1(), 0);
                        setRadio(exam.getItem2(), 1);
                        setRadio(exam.getItem3(), 2);
                        setRadio(exam.getItem4(), 3);
                        answer.setText("");
                        explains.setText("");
                    }
                });
            }
        });
    }

    private void setImg(String url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(null);
            }
        });

        if (url == null || "".equals(url)) {
            return;
        }

        NetUtils.getInstance().getDataAsynFromNet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = response.body().bytes();
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

    private void setRadio(String text, int index) {
        if (text == null || "".equals(text)) {
            return;
        }
        RadioButton radio = new RadioButton(ctx);
        radio.setId(index);
        text = getIndexPrefix(index) + ". " + text;
        radio.setText(text);
        radioGroup.addView(radio, index);
    }

    private char getIndexPrefix(int index) {
        switch (index) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            default:
                return '\0';
        }
    }
}
