package com.example.aria.sudokuweb.controller.generic;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;

/**
 * Created by Aria on 2016/12/5.
 */

public class GenericBtnTouchEvent implements View.OnTouchListener{
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        TextView textView = (TextView) view;

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            textView.setBackgroundColor(Color.WHITE);
            textView.setTextColor(view.getResources().getColor(R.color.MainView_Back));
        }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
            textView.setBackgroundResource(R.drawable.btn_frame);
            textView.setTextColor(Color.WHITE);
        }

        return false;
    }
}
