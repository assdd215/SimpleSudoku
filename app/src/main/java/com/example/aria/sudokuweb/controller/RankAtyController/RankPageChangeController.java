package com.example.aria.sudokuweb.controller.RankAtyController;

import android.support.v4.view.ViewPager;

import com.example.aria.sudokuweb.activity.RankAty;
import com.example.aria.sudokuweb.view.RankView;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankPageChangeController implements ViewPager.OnPageChangeListener{

    private RankAty rankAty;
    private RankView rankView;

    public RankPageChangeController(RankAty rankAty,RankView rankView){
        this.rankAty = rankAty;
        this.rankView = rankView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 1:
                rankView.SwitchLinePosition(true);
                break;
            case 0:
                rankView.SwitchLinePosition(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
