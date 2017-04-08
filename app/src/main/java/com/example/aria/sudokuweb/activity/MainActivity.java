package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.MainAtyController.CheckRankController;
import com.example.aria.sudokuweb.controller.MainAtyController.ExitController;
import com.example.aria.sudokuweb.controller.MainAtyController.HelpController;
import com.example.aria.sudokuweb.controller.MainAtyController.StartGameController;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.MainView;

public class MainActivity extends Activity{

    private StartGameController startGameController;
    private CheckRankController checkRankController;
    private ExitController exitController;
    private HelpController helpController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置布局
        setContentView(R.layout.activity_main);

        //获取屏幕的宽高
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SingleValues.getInstance().setScreen_Height(dm.heightPixels);
        SingleValues.getInstance().setScreen_Width(dm.widthPixels);
        SingleValues.getInstance().iniTypeface(this.getApplication());  //初始化字体

        //初始化布局
        ((MainView)findViewById(R.id.activity_main)).initView();


        //初始化控制器
        startGameController = new StartGameController(this,(MainView)findViewById(R.id.activity_main));
        checkRankController = new CheckRankController(this,(MainView)findViewById(R.id.activity_main));
        exitController = new ExitController(this,(MainView)findViewById(R.id.activity_main));
        helpController = new HelpController(this,(MainView)findViewById(R.id.activity_main));

        //设置监听器
        ((MainView)findViewById(R.id.activity_main)).setStartGameListener(startGameController);
        ((MainView)findViewById(R.id.activity_main)).setChechRankListener(checkRankController);
        ((MainView)findViewById(R.id.activity_main)).setExitListener(exitController);
        ((MainView)findViewById(R.id.activity_main)).setHelpListener(helpController);
    }

    //跳转到选择关卡界面
    public void JumpToSelectAty(){
        Intent intent = new Intent(MainActivity.this, SelectLevelAty.class);
        startActivity(intent);
    }

    //跳转到规则说明界面
    public void JumpToHelpAty(){
        Intent intent = new Intent(this,RuleAty.class);
        startActivity(intent);
    }

    //跳转到查看排行界面
    public void JumpToRankAty(){
        Intent intent = new Intent(this,RankAty.class);
        startActivity(intent);
    }
}
