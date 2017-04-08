package com.example.aria.sudokuweb.controller.SelectLevelAtyController;

import android.view.View;

import com.example.aria.sudokuweb.activity.SelectLevelAty;
import com.example.aria.sudokuweb.view.SelectLevelView;

/**
 * Created by Aria on 2016/12/3.
 */

public class SelectBackController implements View.OnClickListener{

    private SelectLevelAty selectLevelAty;
    private SelectLevelView selectLevelView;

    public SelectBackController(SelectLevelAty selectLevelAty,SelectLevelView selectLevelView){
        this.selectLevelAty = selectLevelAty;
        this.selectLevelView = selectLevelView;
    }

    @Override
    public void onClick(View view) {
        selectLevelAty.finish();
    }
}
