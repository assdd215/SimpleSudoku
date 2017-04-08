package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;

public class SelectNumberAty extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_aty);

        //设置标题
        TextView NumberTitle = (TextView) findViewById(R.id.number_title);
        setFont(NumberTitle);

        //设置数字按钮
        Button button1 = (Button) findViewById(R.id.number_btn1);
        button1.setOnClickListener(this);
//        setFont(button1);

        Button button2 = (Button) findViewById(R.id.number_btn2);
        button2.setOnClickListener(this);
//        setFont(button2);

        Button button3 = (Button) findViewById(R.id.number_btn3);
        button3.setOnClickListener(this);
//        setFont(button3);

        Button button4 = (Button) findViewById(R.id.number_btn4);
        button4.setOnClickListener(this);
//        setFont(button4);

        Button button5 = (Button) findViewById(R.id.number_btn5);
        button5.setOnClickListener(this);
//        setFont(button5);

        Button button6 = (Button) findViewById(R.id.number_btn6);
        button6.setOnClickListener(this);
//        setFont(button6);

        Button button7 = (Button) findViewById(R.id.number_btn7);
        button7.setOnClickListener(this);
//        setFont(button7);

        Button button8 = (Button) findViewById(R.id.number_btn8);
        button8.setOnClickListener(this);
//        setFont(button8);

        Button button9 = (Button) findViewById(R.id.number_btn9);
//        setFont(button9);
        button9.setOnClickListener(this);
    }

    //设置字体
    public void setFont(Button button){
        AssetManager manager = getAssets();
        Typeface typeface = Typeface.createFromAsset(manager,"fonts/ZBH.TTF");
        button.setTypeface(typeface);
    }

    //设置字体
    public void setFont(TextView button){
        AssetManager manager = getAssets();
        Typeface typeface = Typeface.createFromAsset(manager,"fonts/ZBH.TTF");
        button.setTypeface(typeface);
    }
    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        int selectNumber = Integer.parseInt(button.getText().toString());
        Log.d("MainActivity","click:"+selectNumber);
        Intent intent = new Intent();
        intent.putExtra("selectNumber",selectNumber);
        setResult(1,intent);
        finish();
    }
}
