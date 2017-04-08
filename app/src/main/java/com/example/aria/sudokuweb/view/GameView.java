package com.example.aria.sudokuweb.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.activity.GameAty;
import com.example.aria.sudokuweb.controller.generic.GenericBtnTouchEvent;
import com.example.aria.sudokuweb.model.ChessInfo;
import com.example.aria.sudokuweb.model.SingleValues;

/**
 * Created by Aria on 2016/12/3.
 */

public class GameView extends RelativeLayout{

    private int Screen_Height;
    private int Screen_Width;
    private long rangeTime;   //所有时间  单位：s
    private Context context;
    private Typeface typeface;


    private Button[] NumberBtns;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        NumberBtns = new Button[81];
    }

    public void iniView(){
        //设置屏幕宽高参数
        Screen_Height = SingleValues.getInstance().getScreen_Height();
        Screen_Width = SingleValues.getInstance().getScreen_Width();

        //设置字体样式
        AssetManager manager = context.getAssets();
        typeface = Typeface.createFromAsset(manager,"fonts/ZBH.TTF");

        //描述标题
        TextView title_jiandan = (TextView) findViewById(R.id.game_title_jiandan);
        SingleValues.getInstance().setWidget_pos(title_jiandan,getResources().getInteger(R.integer.GAME_TITLE_JIANDAN_TOP),getResources().getInteger(R.integer.GAME_TITLE_JIANDAN_LEFT));
        title_jiandan.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(title_jiandan);

        TextView title_de = (TextView)findViewById(R.id.game_title_de);
        SingleValues.getInstance().setWidget_pos(title_de,getResources().getInteger(R.integer.GAME_TITLE_DE_TOP),getResources().getInteger(R.integer.GAME_TITLE_DE_LEFT));
        SingleValues.getInstance().setFont(title_de);
        title_de.setTextColor(Color.WHITE);

        TextView title_shudu = (TextView) findViewById(R.id.game_title_shudu);
        SingleValues.getInstance().setWidget_pos(title_shudu,getResources().getInteger(R.integer.GAME_TITLE_SHUDU_TOP),getResources().getInteger(R.integer.GAME_TITLE_SHUDU_LEFT));
        SingleValues.getInstance().setFont(title_shudu);
        title_shudu.setTextColor(Color.WHITE);

        //描述关卡文本
        TextView title_level = (TextView) findViewById(R.id.game_level_title);
        SingleValues.getInstance().setWidget_pos(title_level,getResources().getInteger(R.integer.GAME_LEVEL_TITLE_TOP));
        ChessInfo selectChess = SingleValues.getInstance().getSelectChess();
        title_level.setText(selectChess.getTitle());
        SingleValues.getInstance().setFont(title_level);
        title_level.setTextColor(Color.WHITE);

        //描述菜单布局
        LinearLayout MenuLayout = (LinearLayout) findViewById(R.id.menuLayout);
        SingleValues.getInstance().setWidget_pos(MenuLayout,getResources().getInteger(R.integer.GAME_MENU_LAYOUT_TOP));

        //描述计时器
        Chronometer chronometer = (Chronometer) findViewById(R.id.game_chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        SingleValues.getInstance().setFont(chronometer);
        chronometer.start();

        //描述暂停按钮
        Button BtnControlTimer = (Button) findViewById(R.id.BtnControlTimer);
        SingleValues.getInstance().setFont(BtnControlTimer);
        BtnControlTimer.setTextColor(Color.WHITE);

        //描述检查按钮
        Button BtnCheckChess = (Button) findViewById(R.id.BtnCheckChess);
        SingleValues.getInstance().setFont(BtnCheckChess);
        BtnCheckChess.setTextColor(Color.WHITE);

        //描述九宫格布局
        GridLayout GameGridLayout = (GridLayout) findViewById(R.id.game_gridlayout);
        SingleValues.getInstance().setWidget_pos(GameGridLayout,getResources().getInteger(R.integer.GAME_GRIDLAYOUT_TOP));
        LayoutInflater inflater = ((GameAty)context).getLayoutInflater();
        int[] ini_chessMap = SingleValues.getInstance().getIni_ChessMap();
        for (int i=0;i<81;i++){
            NumberBtns[i] = (Button) inflater.inflate(R.layout.game_button_item,null);
            NumberBtns[i].setId(i);
            if (ini_chessMap[i] == 0){
                NumberBtns[i].setText(" ");
                NumberBtns[i].setTextColor(getResources().getColor(R.color.MainView_Back));
            }else {
                NumberBtns[i].setText(String.valueOf(ini_chessMap[i]));
                NumberBtns[i].setTextColor(getResources().getColor(R.color.ROCKBLUE));
                NumberBtns[i].setClickable(false);
                NumberBtns[i].setFocusable(false);
            }
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = Screen_Width/9;
            params.height = params.width;
            NumberBtns[i].setLayoutParams(params);
            setNumBtnColor(NumberBtns[i]);
            GameGridLayout.addView(NumberBtns[i]);
        }

        //设置返回按钮
        Button GameBackBtn = (Button) findViewById(R.id.game_btn_back);
        SingleValues.getInstance().setWidget_pos(GameBackBtn,getResources().getInteger(R.integer.GAME_BACKBTN_TOP),
                getResources().getInteger(R.integer.GAME_BACKBTN_LEFT));
        SingleValues.getInstance().setFont(GameBackBtn);
        GameBackBtn.setTextColor(Color.WHITE);

    }

    //设置计时器开始暂停
    public void setChronometer(boolean isPause){
        Chronometer chronometer = (Chronometer) findViewById(R.id.game_chronometer);
        Button button = (Button) findViewById(R.id.BtnControlTimer);
        if (isPause){
            button.setText("开始");
            rangeTime = (SystemClock.elapsedRealtime()-chronometer.getBase())/1000;
            chronometer.stop();
            lockNumberBtns();
            Log.d("MainActivity",rangeTime+"");

        }else {
            button.setText("暂停");
            chronometer.setBase(SystemClock.elapsedRealtime()-rangeTime*1000);
            chronometer.start();
            unlockNumberBtns();
        }
    }

    //获取所用时间
    public int getRangeTime(){
        Chronometer chronometer = (Chronometer) findViewById(R.id.game_chronometer);
        return (int) ((SystemClock.elapsedRealtime()-chronometer.getBase())/1000);
    }

    //锁住所有数字按钮
    public void lockNumberBtns(){
        for (int i=0;i<NumberBtns.length;i++){
            NumberBtns[i].setClickable(false);
        }
    }

    //解锁所有数字按钮
    public void unlockNumberBtns(){
        int[] ini_chessMap = SingleValues.getInstance().getIni_ChessMap();
        for (int i=0;i<ini_chessMap.length;i++){
            if (ini_chessMap[i]==0){
                NumberBtns[i].setClickable(true);
            }
        }
    }

    //设置数字按钮数字
    public void setNumberBtnNumber(int position,int selectNumber){
        NumberBtns[position].setText(String.valueOf(selectNumber));
    }

    //设置数字按钮颜色
    public void setNumBtnColor(Button button){
        int position = button.getId();
        int row = position/9;
        int column = position%9;
        int blk_row = row/3;
        int blk_col = column/3;
        if ((blk_col+blk_row)%2 == 0){
            button.setBackgroundResource(R.drawable.num_btn_frame_aliceblue_normal);
        }else{
            button.setBackgroundResource(R.drawable.num_btn_frame_white_normal);
        }
    }

    //设置错误数字按钮颜色
    public void setErrorNumBtn(int position){
        NumberBtns[position].setTextColor(getResources().getColor(R.color.NUMBER_ERROR));
    }

    //设置被选中的数字按钮颜色
    public void setNumBtnColor_focus(Button button){
        int position = button.getId();
        int row = position/9;
        int column = position%9;
        int blk_row = row/3;
        int blk_col = column/3;
        if ((blk_col+blk_row)%2 == 0){
            button.setBackgroundResource(R.drawable.num_btn_frame_aquablue_focus);
        }else{
            button.setBackgroundResource(R.drawable.num_btn_frame_moonblue_focus);
        }
    }

    //设置数字按钮字体颜色
    public void setNumBtnsTextColor(int position){
        NumberBtns[position].setTextColor(getResources().getColor(R.color.MainView_Back));
    }

    //设置监听器
    public void setBackBtnListener(OnClickListener listener){
        findViewById(R.id.game_btn_back).setOnClickListener(listener);
        findViewById(R.id.game_btn_back).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setNumberBtnsListener(OnFocusChangeListener listener){
        int[] ini_chessMap = SingleValues.getInstance().getIni_ChessMap();
        for (int i=0;i<NumberBtns.length;i++){
            if (ini_chessMap[i] == 0){
                NumberBtns[i].setFocusableInTouchMode(true);
                NumberBtns[i].setOnFocusChangeListener(listener);
                NumberBtns[i].setOnClickListener((OnClickListener)listener);
            }
        }
    }

    public void setTimerControlListener(OnClickListener listener){
        findViewById(R.id.BtnControlTimer).setOnClickListener(listener);
    }

    public void setCheckBtnListener(OnClickListener listener){
        findViewById(R.id.BtnCheckChess).setOnClickListener(listener);
    }

}
