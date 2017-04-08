package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.RankAtyController.RankBackController;
import com.example.aria.sudokuweb.controller.RankAtyController.RankPageChangeController;
import com.example.aria.sudokuweb.controller.RankAtyController.RankingChangeController;
import com.example.aria.sudokuweb.model.RankInfo;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.RankView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RankAty extends Activity {

    private RankBackController rankBackController;
    private RankingChangeController rankingChangeController;
    private RankPageChangeController rankPageChangeController;
    private List<RankInfo> rankList_global;
    private List<RankInfo> rankList_local;
    private MyTask myTask;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rank_aty);

        //获取排行信息
//        getRankList_global();
//        getRankList_local();

        myTask = new MyTask();
        myTask.execute();

        //初始化布局
        ((RankView)findViewById(R.id.activity_rank_aty)).iniView();
//        ((RankView)findViewById(R.id.activity_rank_aty)).iniRankPager();

        //初始化控制器
        rankBackController = new RankBackController(this,(RankView)findViewById(R.id.activity_rank_aty));
        rankingChangeController = new RankingChangeController(this,(RankView)findViewById(R.id.activity_rank_aty));
        rankPageChangeController = new RankPageChangeController(this,(RankView)findViewById(R.id.activity_rank_aty));

        //配置监听器
        ((RankView)findViewById(R.id.activity_rank_aty)).setBackBtnListener(rankBackController);
    }

    //从服务器获取本区排行信息
    public void getRankList_local(){
        rankList_local = new ArrayList<RankInfo>();

        for (int i=0;i<10;i++){
            RankInfo rankInfo = new RankInfo("LEVEL - 0"+i,"Aria",180+i);
            rankList_local.add(rankInfo);
        }

        SingleValues.getInstance().setRankList_local(rankList_local);
    }

    //从服务器获取全服排行信息
    public void getRankList_global(){
        rankList_global = new ArrayList<RankInfo>();

        for (int i=0;i<10;i++){
            RankInfo rankInfo = new RankInfo("LEVEL - 1"+i,"Aria",190+i);
            rankList_global.add(rankInfo);
        }

        SingleValues.getInstance().setRankList_global(rankList_global);
    }

    //创建与服务器的连接
    public List<RankInfo> createConnection(String url_str){
        try {
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setConnectTimeout(500);
            connection.setReadTimeout(500);

            connection.connect();

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len = inputStream.read(data))!=-1) {
                outputStream.write(data,0,len);
            }
            inputStream.close();
            return TransToList(new JSONArray(outputStream.toString()));

        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将JSON数组转换成List
    public List<RankInfo> TransToList(JSONArray jsonArray){
        List<RankInfo> rankList = new ArrayList<RankInfo>();
        try {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                rankList.add(new RankInfo(object.getString("title"),
                        object.getString("username"),object.getInt("usetime")));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return rankList;
    }

    //获取this
    public RankAty getRankAty(){
        return this;
    }

    private void getRankListFromTest(){
        rankList_local = new ArrayList<RankInfo>();
        rankList_local.add(new RankInfo("LEVEL - 01","local01",169));
        rankList_local.add(new RankInfo("LEVEL - 02","local02",177));
        rankList_local.add(new RankInfo("LEVEL - 03","local03",188));
        rankList_local.add(new RankInfo("LEVEL - 04","local04",199));
        rankList_local.add(new RankInfo("LEVEL - 05","local05",133));
        rankList_local.add(new RankInfo("LEVEL - 06","local06",187));
        rankList_local.add(new RankInfo("LEVEL - 07","local07",195));
        rankList_local.add(new RankInfo("LEVEL - 08","local08",222));

        rankList_global = new ArrayList<RankInfo>();
        rankList_global.add(new RankInfo("LEVEL - 01","global01",155));
        rankList_global.add(new RankInfo("LEVEL - 02","local02",177));
        rankList_global.add(new RankInfo("LEVEL - 03","global03",188));
        rankList_global.add(new RankInfo("LEVEL - 04","local04",199));
        rankList_global.add(new RankInfo("LEVEL - 05","global05",123));
        rankList_global.add(new RankInfo("LEVEL - 06","local06",187));
        rankList_global.add(new RankInfo("LEVEL - 07","global05",165));
        rankList_global.add(new RankInfo("LEVEL - 08","local08",222));
    }

    private class MyTask extends AsyncTask<String,Integer,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getRankAty(),"提示","正在载入中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            rankList_local = createConnection(SingleValues.url+"AllRanking_android");
            rankList_global = createConnection(SingleValues.url+"AllRanking_Global");


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (rankList_global==null && rankList_local==null){
                Toast.makeText(getRankAty(),"获取数据失败",Toast.LENGTH_SHORT).show();
                getRankListFromTest();

            }
            if (rankList_global==null && rankList_local.size()>0){  //正常情况

                Toast.makeText(getRankAty(),"获取全服数据失败",Toast.LENGTH_SHORT).show();
                rankList_global = new ArrayList<RankInfo>();

            }
            //初始化数据
            SingleValues.getInstance().setRankList_local(rankList_local);
            SingleValues.getInstance().setRankList_global(rankList_global);

            //初始化排行分页
            ((RankView)findViewById(R.id.activity_rank_aty)).iniRankPager();

            //配置监听器
            ((RankView)findViewById(R.id.activity_rank_aty)).setRankingChangeListener(rankingChangeController);
            ((RankView)findViewById(R.id.activity_rank_aty)).setRankingPageListener(rankPageChangeController);

            progressDialog.cancel();

        }
    }
}
