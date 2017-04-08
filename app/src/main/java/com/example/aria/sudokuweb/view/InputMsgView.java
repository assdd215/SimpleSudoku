package com.example.aria.sudokuweb.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.model.SingleValues;

/**
 * Created by Aria on 2016/12/6.
 */

public class InputMsgView extends RelativeLayout{

    private int Screen_Height;
    private int Screen_Width;
    public InputMsgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //初始化界面
    public void iniView(){
        //设置屏幕宽高参数
        Screen_Height = SingleValues.getInstance().getScreen_Height();
        Screen_Width = SingleValues.getInstance().getScreen_Width();

        //描述标题
        TextView title_jiandan = (TextView) findViewById(R.id.input_title_jiandan);
        SingleValues.getInstance().setWidget_pos(title_jiandan,getResources().getInteger(R.integer.INPUT_TITLE_JIANDAN_TOP),getResources().getInteger(R.integer.INPUT_TITLE_JIANDAN_LEFT));
        title_jiandan.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(title_jiandan);

        TextView title_de = (TextView)findViewById(R.id.input_title_de);
        SingleValues.getInstance().setWidget_pos(title_de,getResources().getInteger(R.integer.INPUT_TITLE_DE_TOP),getResources().getInteger(R.integer.INPUT_TITLE_DE_LEFT));
        SingleValues.getInstance().setFont(title_de);
        title_de.setTextColor(Color.WHITE);

        TextView title_shudu = (TextView) findViewById(R.id.input_title_shudu);
        SingleValues.getInstance().setWidget_pos(title_shudu,getResources().getInteger(R.integer.INPUT_TITLE_SHUDU_TOP),getResources().getInteger(R.integer.INPUT_TITLE_SHUDU_LEFT));
        SingleValues.getInstance().setFont(title_shudu);
        title_shudu.setTextColor(Color.WHITE);

        //描述排行标题
        TextView title_input = (TextView) findViewById(R.id.input_title);
        SingleValues.getInstance().setFont(title_input);
        SingleValues.getInstance().setWidget_pos(title_input,getResources().getInteger(R.integer.INPUT_SUCCESSTITLE_TOP));

        //描述用时布局
        RelativeLayout input_usetime_layout = (RelativeLayout) findViewById(R.id.input_usetime_layout);
        SingleValues.getInstance().setWidget_pos(input_usetime_layout,getResources().getInteger(R.integer.INPUT_USETIME_LAYOUT_TOP));

        TextView input_usetime_text = (TextView) findViewById(R.id.input_usetime_text);
        input_usetime_text.setText("用时： ");
        SingleValues.getInstance().setFont(input_usetime_text);
//
        TextView input_usetime_number = (TextView) findViewById(R.id.input_usetime_number);
        SingleValues.getInstance().setFont(input_usetime_number);
        input_usetime_number.setText(String.valueOf(SingleValues.getInstance().getRankInfo().getUsetime()));

        TextView input_usetime_second = (TextView) findViewById(R.id.input_usetime_second);
        SingleValues.getInstance().setFont(input_usetime_second);

        //描述输入玩家名文本
        TextView input_username_title = (TextView) findViewById(R.id.input_username_title);
        SingleValues.getInstance().setFont(input_username_title);

        //描述输入玩家名输入框
        EditText input_username_edit = (EditText) findViewById(R.id.input_username_edit);
//        SingleValues.getInstance().setFont(input_username_edit);

        //描述提交按钮
        Button input_username_submitbtn = (Button) findViewById(R.id.input_username_submitbtn);
        SingleValues.getInstance().setFont(input_username_submitbtn);

    }

    public void setSubmitBtnListener(OnClickListener listener){
        findViewById(R.id.input_username_submitbtn).setOnClickListener(listener);
    }

    public String getUsernameInEdit(){
        return ((EditText)findViewById(R.id.input_username_edit)).getText().toString();
    }
}
