package com.example.aria.sudokuweb.controller.SelectLevelAtyController;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.activity.SelectLevelAty;
import com.example.aria.sudokuweb.model.ChessInfo;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.SelectLevelView;

import java.util.List;

/**
 * Created by Aria on 2016/12/3.
 */

public class LevelClickController implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private SelectLevelAty selectLevelAty;
    private SelectLevelView selectLevelView;
    private LevelBtnTouchEvent levelBtnTouchEvent;
    private int btn_click_id = -1;

    public LevelClickController(SelectLevelAty selectLevelAty,SelectLevelView selectLevelView){
        this.selectLevelAty = selectLevelAty;
        this.selectLevelView = selectLevelView;
        levelBtnTouchEvent = new LevelBtnTouchEvent();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        btn_click_id = id;
        ChessInfo selectChess = null;
        List<ChessInfo> chessList = SingleValues.getInstance().getChessList();
        for (int i=0;i<chessList.size();i++){
            if (id == chessList.get(i).getId())
                selectChess = chessList.get(i);
        }
        selectLevelAty.JumpToGameAty(selectChess);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 1){
            selectLevelView.setLevelBtnsState(btn_click_id);
        }
    }

    public LevelBtnTouchEvent getLevelBtnTouchEvent() {
        return levelBtnTouchEvent;
    }

    class LevelBtnTouchEvent implements View.OnTouchListener{
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            TextView textView = (TextView) view;

            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                btn_click_id = textView.getId();
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(view.getResources().getColor(R.color.MainView_Back));
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                textView.setBackgroundResource(R.drawable.btn_frame);
                textView.setTextColor(Color.WHITE);
            }

            return false;
        }
    }

}
