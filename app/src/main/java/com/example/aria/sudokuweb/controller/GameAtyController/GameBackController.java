package com.example.aria.sudokuweb.controller.GameAtyController;

import android.view.View;

import com.example.aria.sudokuweb.activity.GameAty;
import com.example.aria.sudokuweb.view.GameView;

/**
 * Created by Aria on 2016/12/4.
 */

public class GameBackController implements View.OnClickListener{

    private GameView gameView;
    private GameAty gameAty;

    public GameBackController(GameAty gameAty,GameView gameView){
        this.gameAty = gameAty;
        this.gameView = gameView;
    }

    @Override
    public void onClick(View view) {
        gameAty.finish();
    }
}
