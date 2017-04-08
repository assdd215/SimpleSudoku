package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.InputMsgController.InputMsgSubmitController;
import com.example.aria.sudokuweb.model.RankInfo;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.InputMsgView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InputMsgAty extends Activity {


    private InputMsgSubmitController inputMsgSubmitController;
    private ProgressDialog progressDialog;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_input_msg_aty);

        //初始化数据
        RankInfo rankInfo = (RankInfo) getIntent().getSerializableExtra("rankInfo");
        SingleValues.getInstance().setRankInfo(rankInfo);

        ((InputMsgView)findViewById(R.id.activity_input_msg_aty)).iniView();

        //初始化控制器
        inputMsgSubmitController = new InputMsgSubmitController(this,(InputMsgView)findViewById(R.id.activity_input_msg_aty));

        //配置监听器
        ((InputMsgView)findViewById(R.id.activity_input_msg_aty)).setSubmitBtnListener(inputMsgSubmitController);
    }

    public InputMsgAty getInputMsgAty(){
        return this;
    }


    //建立与服务器的连接
    public void CreateConnection(String url_str,RankInfo rankInfo){
        try {
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type","application/json");

            connection.connect();

            JSONObject object = new JSONObject();
            object.put("title",rankInfo.getTitle().toString());
            object.put("isweb",0);
            object.put("username",rankInfo.getUsername());
            object.put("usetime",rankInfo.getUsetime());

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            Log.d("MainActivity",object.toString());
            writer.write(object.toString());
            writer.flush();
            writer.close();

            connection.getInputStream();
            flag = true;

        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //处理被点击时的事件
    public void SubmitRankInfo(String username){
        RankInfo rankInfo = SingleValues.getInstance().getRankInfo();
        rankInfo.setUsername(username);
        MyTask myTask = new MyTask(rankInfo);
        myTask.execute();

    }

    private class MyTask extends AsyncTask<String,Integer,String>{

        private RankInfo rankInfo;

        public MyTask(RankInfo rankInfo){
            this.rankInfo = rankInfo;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getInputMsgAty(),"提示","提交数据中...");
        }

        @Override
        protected String doInBackground(String... strings) {

           CreateConnection(SingleValues.url+"createAccount",rankInfo);

            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (flag){
                Toast.makeText(getInputMsgAty(),"提交成功",Toast.LENGTH_SHORT).show();
                progressDialog.cancel();
                getInputMsgAty().finish();
            }
            else {
                Toast.makeText(getInputMsgAty(),"提交失败!",Toast.LENGTH_SHORT).show();
                progressDialog.cancel();

            }
        }
    }
}
