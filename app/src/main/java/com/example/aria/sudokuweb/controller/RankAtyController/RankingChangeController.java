package com.example.aria.sudokuweb.controller.RankAtyController;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aria.sudokuweb.activity.RankAty;
import com.example.aria.sudokuweb.view.RankView;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankingChangeController implements View.OnClickListener{

    private RankAty rankAty;
    private RankView rankView;

    public RankingChangeController(RankAty rankAty,RankView rankView){
        this.rankAty = rankAty;
        this.rankView = rankView;
    }
    @Override
    public void onClick(View view) {
        TextView textView = (TextView) view;
        String btn_str = textView.getText().toString();
        if ("本区排行".equals(btn_str)){
            rankView.SwitchToPages(0);

        }else if ("全服排行".equals(btn_str)){
            rankView.SwitchToPages(1);
        }
    }
}
