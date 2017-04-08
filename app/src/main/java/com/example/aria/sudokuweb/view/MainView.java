package com.example.aria.sudokuweb.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.generic.GenericBtnTouchEvent;
import com.example.aria.sudokuweb.model.SingleValues;

/**
 * Created by Aria on 2016/12/2.
 */

public class MainView extends RelativeLayout{
    private Context context;
    private int Screen_Height;
    private int Screen_Width;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //初始化界面
    public void initView(){
        //设置屏幕宽高参数
        Screen_Height = SingleValues.getInstance().getScreen_Height();
        Screen_Width = SingleValues.getInstance().getScreen_Width();

        //描述标题
        TextView title_jiandan = (TextView) findViewById(R.id.select_title_jiandan);
        SingleValues.getInstance().setWidget_pos(title_jiandan,getResources().getInteger(R.integer.MAIN_TITLE_JIANDAN_TOP),getResources().getInteger(R.integer.MAIN_TITLE_JIANDAN_LEFT));
        title_jiandan.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(title_jiandan);

        TextView title_de = (TextView)findViewById(R.id.select_title_de);
        SingleValues.getInstance().setWidget_pos(title_de,getResources().getInteger(R.integer.MAIN_TITLE_DE_TOP),getResources().getInteger(R.integer.MAIN_TITLE_DE_LEFT));
        SingleValues.getInstance().setFont(title_de);
        title_de.setTextColor(Color.WHITE);

        TextView title_shudu = (TextView) findViewById(R.id.select_title_shudu);
        SingleValues.getInstance().setWidget_pos(title_shudu,getResources().getInteger(R.integer.MAIN_TITLE_SHUDU_TOP),getResources().getInteger(R.integer.MAIN_TITLE_SHUDU_LEFT));
        SingleValues.getInstance().setFont(title_shudu);
        title_shudu.setTextColor(Color.WHITE);

        //设置开始游戏按钮
        Button StartGameBtn = (Button) findViewById(R.id.StartGame);
        SingleValues.getInstance().setWidget_pos(StartGameBtn,getResources().getInteger(R.integer.MAIN_STARTGAME_TOP));
        SingleValues.getInstance().setFont(StartGameBtn);
        StartGameBtn.setTextColor(Color.WHITE);

        //设置查看排行按钮
        Button RankBtn = (Button) findViewById(R.id.CheckRank);
        SingleValues.getInstance().setWidget_pos(RankBtn,getResources().getInteger(R.integer.MAIN_RANK_TOP));
        SingleValues.getInstance().setFont(RankBtn);
        RankBtn.setTextColor(Color.WHITE);

        //设置退出游戏按钮
        Button ExitBtn = (Button) findViewById(R.id.ExitGame);
        SingleValues.getInstance().setWidget_pos(ExitBtn,getResources().getInteger(R.integer.MAIN_EXIT_TOP));
        SingleValues.getInstance().setFont(ExitBtn);
        ExitBtn.setTextColor(Color.WHITE);

        //设置帮助按钮
        Button HelpBtn = (Button) findViewById(R.id.HelpBtn);
        SingleValues.getInstance().setWidget_pos(HelpBtn,getResources().getInteger(R.integer.MAIN_HELP_TOP),
                getResources().getInteger(R.integer.MAIN_HELP_LEFT),
                getResources().getInteger(R.integer.MAIN_HELP_WIDTH),
                getResources().getInteger(R.integer.MAIN_HELP_WIDTH));

    }

    //设置监听器
    public void setStartGameListener(OnClickListener listener){
        findViewById(R.id.StartGame).setOnClickListener(listener);
        findViewById(R.id.StartGame).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setChechRankListener(OnClickListener listener){
        findViewById(R.id.CheckRank).setOnClickListener(listener);
        findViewById(R.id.CheckRank).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setExitListener(OnClickListener listener){
        findViewById(R.id.ExitGame).setOnClickListener(listener);
        findViewById(R.id.ExitGame).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setHelpListener(OnClickListener listener){
        findViewById(R.id.HelpBtn).setOnClickListener(listener);
    }
}
