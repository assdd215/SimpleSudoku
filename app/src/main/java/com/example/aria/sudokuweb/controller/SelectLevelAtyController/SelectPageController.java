package com.example.aria.sudokuweb.controller.SelectLevelAtyController;

import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.aria.sudokuweb.activity.SelectLevelAty;
import com.example.aria.sudokuweb.view.SelectLevelView;

/**
 * Created by Aria on 2016/12/5.
 */

public class SelectPageController implements ViewPager.OnPageChangeListener{

    private SelectLevelAty selectLevelAty;
    private SelectLevelView selectLevelView;

    public SelectPageController(SelectLevelAty selectLevelAty,SelectLevelView selectLevelView){
        this.selectLevelAty = selectLevelAty;
        this.selectLevelView = selectLevelView;
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
            Log.d("MainActivity","back to ");
//            selectLevelView.setLevelBtnsState();

        }
    }
}
