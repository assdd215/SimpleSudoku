package com.example.aria.sudokuweb.controller.RankAtyController;

import android.view.View;

import com.example.aria.sudokuweb.activity.RankAty;
import com.example.aria.sudokuweb.adapter.RankViewPagerAdapter;
import com.example.aria.sudokuweb.view.RankView;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankBackController implements View.OnClickListener{

    private RankAty rankAty;
    private RankView rankView;

    public RankBackController(RankAty rankAty,RankView rankView){
        this.rankAty = rankAty;
        this.rankView = rankView;
    }

    @Override
    public void onClick(View view) {
        rankAty.finish();
    }
}
