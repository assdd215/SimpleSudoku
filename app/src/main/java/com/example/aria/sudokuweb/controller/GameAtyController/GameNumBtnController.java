package com.example.aria.sudokuweb.controller.GameAtyController;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.aria.sudokuweb.activity.GameAty;
import com.example.aria.sudokuweb.view.GameView;

/**
 * Created by Aria on 2016/12/4.
 */

public class GameNumBtnController implements View.OnFocusChangeListener,View.OnClickListener{

    private GameView gameView;
    private GameAty gameAty;
    private boolean flag = false;

    public GameNumBtnController(GameAty gameAty,GameView gameView){
        this.gameAty = gameAty;
        this.gameView = gameView;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        Button button = (Button) view;
        if (hasFocus){
            gameView.setNumBtnColor_focus(button);
            if (flag == true){
                gameAty.setSelectPosition(view.getId());
                gameAty.JumpToSelectNumAty();
            }else {
                flag = true;
            }
        }else {
            gameView.setNumBtnColor(button);
        }
    }

    public void setBtnNumbers(int position, int number){
        gameView.setNumberBtnNumber(position,number);
    }

    public void setNumBtnsTextColor(int position){
        gameView.setNumBtnsTextColor(position);
    }

    @Override
    public void onClick(View view) {
        onFocusChange(view,true);
    }
}
