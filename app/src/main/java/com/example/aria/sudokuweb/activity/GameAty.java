package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.controller.GameAtyController.GameBackController;
import com.example.aria.sudokuweb.controller.GameAtyController.GameCheckChessController;
import com.example.aria.sudokuweb.controller.GameAtyController.GameNumBtnController;
import com.example.aria.sudokuweb.controller.GameAtyController.GameTimerController;
import com.example.aria.sudokuweb.model.ChessInfo;
import com.example.aria.sudokuweb.model.RankInfo;
import com.example.aria.sudokuweb.model.SingleValues;
import com.example.aria.sudokuweb.view.GameView;

public class GameAty extends Activity {

    private ChessInfo selectChess;
    private GameBackController gameBackController;
    private GameNumBtnController gameNumBtnController;
    private GameTimerController gameTimerController;
    private GameCheckChessController gameCheckChessController;

    private int ini_ChessMap[];
    /*
        0   可填
     */
    private int current_ChessMap[];
    private int selectPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置布局
        setContentView(R.layout.activity_game_aty);

        //初始化数值
        selectChess = (ChessInfo) getIntent().getSerializableExtra("selectChess"); //获取选中的关卡信息
        SingleValues.getInstance().setSelectChess(selectChess);
        iniChessMap();
        SingleValues.getInstance().setIni_ChessMap(ini_ChessMap);

        //初始化布局
        ((GameView)findViewById(R.id.activity_game_aty)).iniView();

        //初始化控制器
        gameBackController = new GameBackController(this,(GameView)findViewById(R.id.activity_game_aty));
        gameNumBtnController = new GameNumBtnController(this,(GameView)findViewById(R.id.activity_game_aty));
        gameTimerController = new GameTimerController(this,(GameView)findViewById(R.id.activity_game_aty));
        gameCheckChessController = new GameCheckChessController(this,(GameView)findViewById(R.id.activity_game_aty));


        //设置监听器
        ((GameView)findViewById(R.id.activity_game_aty)).setBackBtnListener(gameBackController);
        ((GameView)findViewById(R.id.activity_game_aty)).setNumberBtnsListener(gameNumBtnController);
        ((GameView)findViewById(R.id.activity_game_aty)).setTimerControlListener(gameTimerController);
        ((GameView)findViewById(R.id.activity_game_aty)).setCheckBtnListener(gameCheckChessController);
    }

    //初始化数独棋谱
    public void iniChessMap(){

        String []chessMap_str = selectChess.getContent().split(",");
        ini_ChessMap = new int[81];
        for (int i=0;i<chessMap_str.length;i++){
            try {
                ini_ChessMap[i] = Integer.parseInt(chessMap_str[i]);
            }catch (NumberFormatException e){
                e.printStackTrace();
                return;
            }
        }
        current_ChessMap = new int[ini_ChessMap.length];
        for (int i=0;i<current_ChessMap.length;i++)
            current_ChessMap[i] = ini_ChessMap[i];
    }

    //跳转到选择数字界面
    public void JumpToSelectNumAty(){
        Intent intent = new Intent(this,SelectNumberAty.class);
        startActivityForResult(intent,1);
    }

    //设置被选中的按钮位置
    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    //获取被选中的按钮位置
    public int getSelectPosition() {
        return selectPosition;
    }

    //获取返回信息
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1){   //选择数字且返回
            int selectNumber = data.getIntExtra("selectNumber",0);
            gameNumBtnController.setBtnNumbers(getSelectPosition(),selectNumber);
            setCurrent_ChessMap(getSelectPosition(),selectNumber);
            gameNumBtnController.setNumBtnsTextColor(getSelectPosition());
        }
    }

    //输入数独数字，更新当前棋谱
    public void setCurrent_ChessMap(int position,int number){
        current_ChessMap[position] = number;
    }

    //核对数独数字
    public boolean CheckMap(int position){
            if (ini_ChessMap[position]==0 && current_ChessMap[position]!=0){  //该位置上的数字被修改过
                int row = position/9;
                int col = position%9;
                if (checkBlk(row,col)&&checkLine(row,col)){
                  return true;
                }else {
                    return false;
                }
            }
        return true;
    }

    //核对每一行的数字
    public boolean checkLine(int row,int col){
        for (int i=0;i<9;i++){
            if (col != i && current_ChessMap[row*9+i]!=0 && current_ChessMap[row*9+i] == current_ChessMap[row*9+col]){
                return false;
            }
        }
        for (int j=0;j<9;j++){
            if (row!=j && current_ChessMap[j*9+col]!=0 && current_ChessMap[j*9+col] == current_ChessMap[row*9+col]){
                return false;
            }
        }
        return true;
    }

    //核对一个小九宫格数字
    public boolean checkBlk(int row,int col){
        int Row = row/3*3;
        int Col = col/3*3;
        for (int i = 0;i<3;i++){
            for (int j =0;j<3;j++){
                if (!(row == (Row+i) && col == (Col+j))){//如果行和列不是目标点的话
                    if (current_ChessMap[(Row+i)*9+(Col+j)]!=0 && current_ChessMap[(Row+i)*9+(Col+j)]
                            == current_ChessMap[row*9+col]){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //检查是否完成填写
    public boolean isFinishMap(){
        for (int i=0;i<current_ChessMap.length;i++){
            if (current_ChessMap[i] == 0)
                return false;
        }
        return true;
    }

    //跳转到完成关卡界面
    public void JumpToInputMsgAty(int usetime){
        RankInfo rankInfo = new RankInfo();
        rankInfo.setTitle(selectChess.getTitle());
        rankInfo.setUsetime(usetime);
        Intent intent = new Intent(this,InputMsgAty.class);
        intent.putExtra("rankInfo",rankInfo);
        startActivity(intent);
    }
}
