package com.example.aria.sudokuweb.controller.GameAtyController;

import android.view.View;

import com.example.aria.sudokuweb.activity.GameAty;
import com.example.aria.sudokuweb.view.GameView;

/**
 * Created by Aria on 2016/12/4.
 */

public class GameCheckChessController implements View.OnClickListener{

    private GameView gameView;
    private GameAty gameAty;

    public GameCheckChessController(GameAty gameAty,GameView gameView){
        this.gameAty = gameAty;
        this.gameView = gameView;
    }

    @Override
    public void onClick(View view) {
        boolean isRed = false;
        for (int position = 0;position<81;position++){
            if (!gameAty.CheckMap(position)){
                isRed = true;
                gameView.setErrorNumBtn(position);
            }
        }
//        int usetime = gameView.getRangeTime();
//        gameAty.JumpToInputMsgAty(usetime);
//        gameView.setChronometer(true);

        if (!isRed && gameAty.isFinishMap()){
            int usetime = gameView.getRangeTime();
            gameAty.JumpToInputMsgAty(usetime);
            gameView.setChronometer(true);
        }
    }
}
