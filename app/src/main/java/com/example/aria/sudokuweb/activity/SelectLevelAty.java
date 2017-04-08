package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.SelectLevelAtyController.LevelClickController;
import com.example.aria.sudokuweb.controller.SelectLevelAtyController.SelectBackController;
import com.example.aria.sudokuweb.controller.SelectLevelAtyController.SelectPageController;
import com.example.aria.sudokuweb.model.ChessInfo;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.SelectLevelView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SelectLevelAty extends Activity {

    private SelectBackController selectBackController;
    private LevelClickController levelClickController;
    private SelectPageController selectPageController;
    private List<ChessInfo> chessList;
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

        setContentView(R.layout.activity_select_level_aty);

        //初始化数据
        myTask = new MyTask();
        myTask.execute();

        //初始化界面
        ((SelectLevelView)findViewById(R.id.activity_select_level_aty)).iniView();

        //初始化控制器
        selectBackController = new SelectBackController(this,(SelectLevelView)findViewById(R.id.activity_select_level_aty));
        levelClickController = new LevelClickController(this,(SelectLevelView)findViewById(R.id.activity_select_level_aty));
        selectPageController = new SelectPageController(this,(SelectLevelView)findViewById(R.id.activity_select_level_aty));

    }

    //从服务器端获取所有关卡信息
    public List<ChessInfo> getChessInfoFromTest(){

        List<ChessInfo> chessList = new ArrayList<ChessInfo>();
        for (int i=1;i<9;i++){
            ChessInfo info = new ChessInfo(i,"LEVEL - 0"+i,"1,2,4,5,0,0,0,0,0,0,0,7,0,0,8,0,0,0,0,3,0,0,9,0,6,7,0,0,0,9,7,8,4,3,1,0,0,1,0,0,5,2,0,4,0,7,0,8,6,0,3,0,0,2,5,8,2,4,0,9,1,6,0,0,9,3,1,6,0,2,8,5,6,7,0,0,0,5,4,3,0");
            chessList.add(info);
        }
        return chessList;
    }

    //创建连接并从服务器获取数据
    public void createConnect(String url_str){
        try{
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(1000);
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len=inputStream.read(data))!=-1){
                outputStream.write(data,0,len);
            }
            inputStream.close();
            String level_message = outputStream.toString();
            JSONArray jsonArray = new JSONArray(level_message);
            BuildChessInfo(jsonArray);
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //创建连接并从服务器获取数据
    public void testConnect(String url_str,boolean flag){
        try{
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(false);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            Log.d("MainActivity","testConnect");

//            JSONObject object = new JSONObject();
//            object.put("title","test111");
//            object.put("isweb",1);
//            object.put("username","testuser");
//            object.put("usetime",1111);
//
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//            writer.write(object.toString());
//            writer.flush();
//            writer.close();

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len = 0;
            byte[] data = new byte[1024];
            while ((len=inputStream.read(data))!=-1){
                outputStream.write(data,0,len);
            }
            inputStream.close();
            String level_message = outputStream.toString();
            JSONObject object1 = new JSONObject(level_message);
            Log.d("MainActivity",level_message);
//            BuildChessInfo(jsonArray);
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //组装获取到的数据为ChessInfo
    public List<ChessInfo> BuildChessInfo(JSONArray jsonArray) {
        chessList = new ArrayList<ChessInfo>();
        try {
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                chessList.add(new ChessInfo(object.getInt("id"),object.getString("title"),object.getString("content")));
                int title = object.getInt("title");
                chessList.get(i).setTitle("LEVEL - "+title/10 + title%10);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return chessList;
    }

    //跳转游戏界面
    public void JumpToGameAty(ChessInfo chessInfo){
        //获取选择的关卡信息
        Intent intent = new Intent(this, GameAty.class);
        intent.putExtra("selectChess",chessInfo);
        startActivity(intent);
    }


    private class MyTask extends AsyncTask<String,Integer,String>{
        //onPreExecute方法用于在执行后台任务前做一些UI操作 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getSelectAty(),"提示","正在载入中...");
        }

        @Override
        protected String doInBackground(String... strings) {
            createConnect(SingleValues.url+"queryLevel");

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (chessList== null){
                progressDialog.cancel();
                Toast.makeText(getSelectAty(),"载入关卡失败！使用本地数据",Toast.LENGTH_SHORT).show();
//                getSelectAty().finish();
//                return;
                chessList = getChessInfoFromTest();
            }
           if (chessList.size() >0){
                //初始化数据
                SingleValues.getInstance().setChessList(chessList);

                //初始化分页
                ((SelectLevelView)findViewById(R.id.activity_select_level_aty)).iniViewPager();

                //设置监听器
                ((SelectLevelView)findViewById(R.id.activity_select_level_aty)).setBackBtnListener(selectBackController);
                ((SelectLevelView)findViewById(R.id.activity_select_level_aty)).setLevelBtnListener(levelClickController);
                ((SelectLevelView)findViewById(R.id.activity_select_level_aty)).setLevelPageListener(levelClickController);


            }
            progressDialog.cancel();

        }
    }

    public SelectLevelAty getSelectAty(){
        return this;
    }
}
