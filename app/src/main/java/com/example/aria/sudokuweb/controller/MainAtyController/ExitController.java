package com.example.aria.sudokuweb.controller.MainAtyController;

import android.view.View;

import com.example.aria.sudokuweb.activity.MainActivity;
import com.example.aria.sudokuweb.view.MainView;

/**
 * Created by Aria on 2016/12/3.
 */

public class ExitController implements View.OnClickListener{

    private MainView mainView;
    private MainActivity mainActivity;

    public ExitController(MainActivity mainActivity,MainView mainView){
        this.mainActivity = mainActivity;
        this.mainView = mainView;
    }

    @Override
    public void onClick(View view) {
        mainActivity.finish();
    }
}
