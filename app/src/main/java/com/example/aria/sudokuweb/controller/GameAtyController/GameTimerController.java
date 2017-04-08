package com.example.aria.sudokuweb.controller.GameAtyController;

import android.view.View;
import android.widget.Button;

import com.example.aria.sudokuweb.activity.GameAty;
import com.example.aria.sudokuweb.view.GameView;

/**
 * Created by Aria on 2016/12/4.
 */

public class GameTimerController implements View.OnClickListener{

    private GameView gameView;
    private GameAty gameAty;

    public GameTimerController(GameAty gameAty,GameView gameView){
        this.gameAty = gameAty;
        this.gameView = gameView;
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if ("暂停".equals(button.getText().toString())){
            gameView.setChronometer(true);
        }else {
            gameView.setChronometer(false);
        }
    }
}
