package com.example.aria.sudokuweb.controller.InputMsgController;

import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.aria.sudokuweb.activity.InputMsgAty;
import com.example.aria.sudokuweb.view.InputMsgView;

/**
 * Created by Aria on 2016/12/7.
 */

public class InputMsgSubmitController implements View.OnClickListener{

    private InputMsgAty inputMsgAty;
    private InputMsgView inputMsgView;

    public InputMsgSubmitController(InputMsgAty inputMsgAty, InputMsgView inputMsgView){
        this.inputMsgAty = inputMsgAty;
        this.inputMsgView = inputMsgView;
    }

    @Override
    public void onClick(View view) {
        String username = inputMsgView.getUsernameInEdit();
        inputMsgAty.SubmitRankInfo(username);
    }
}
