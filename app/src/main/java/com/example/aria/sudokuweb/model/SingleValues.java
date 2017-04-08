package com.example.aria.sudokuweb.model;

import android.app.Application;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aria on 2016/12/5.
 */

public class SingleValues {

    private int Screen_Height;
    private int Screen_Width;
    private List<ChessInfo> chessList;
    private ChessInfo selectChess;
    private RankInfo rankInfo;
    private int[] ini_ChessMap;
    private Typeface typeface;
    private List<RankInfo> rankList_local;
    private List<RankInfo> rankList_global;

    public static final String url = "http://110.64.89.47:8080/user/";

    private SingleValues(){
        //初始化某些需要初始化的值
    }
    private static final SingleValues single = new SingleValues();

    public static SingleValues getInstance(){
        return single;
    }

    public  void setScreen_Height(int screen_Height) {
        Screen_Height = screen_Height;
    }

    public  void setScreen_Width(int screen_Width) {
        Screen_Width = screen_Width;
    }

    public  int getScreen_Width() {
        return Screen_Width;
    }

    public  int getScreen_Height() {
        return Screen_Height;
    }

    public  void setChessList(List<ChessInfo> chessList) {
        single.chessList = chessList;
    }

    public  List<ChessInfo> getChessList() {
        return chessList;
    }

    public  void setSelectChess(ChessInfo selectChess) {
        single.selectChess = selectChess;
    }

    public  ChessInfo getSelectChess() {
        return selectChess;
    }

    public  void setIni_ChessMap(int[] ini_ChessMap) {
        single.ini_ChessMap = ini_ChessMap;
    }

    public  int[] getIni_ChessMap() {
        return ini_ChessMap;
    }

    public  void iniTypeface(Application application){
        AssetManager manager = application.getAssets();
        typeface = Typeface.createFromAsset(manager,"fonts/ZBH.TTF");
    }

    public  void setRankList_local(List<RankInfo> rankList_local) {
        single.rankList_local = rankList_local;
    }

    public  List<RankInfo> getRankList_local() {
        return rankList_local;
    }

    public  void setRankList_global(List<RankInfo> rankList_global) {
        single.rankList_global = rankList_global;
    }

    public  List<RankInfo> getRankList_global() {
        return rankList_global;
    }

    public void setRankInfo(RankInfo rankInfo) {
        this.rankInfo = rankInfo;
    }

    public RankInfo getRankInfo() {
        return rankInfo;
    }

    //设置字体
    public  void setFont(TextView textView){
        textView.setTypeface(single.typeface);
    }

    //设置控件位置
    public  void setWidget_pos(View view, int top, int left) {
        RelativeLayout.LayoutParams relative_params =
                new RelativeLayout.LayoutParams(view.getLayoutParams());
        relative_params.topMargin = (int) (single.Screen_Height * (float)top/1000);
        relative_params.leftMargin = (int) (single.Screen_Width * (float)left/1000);
        view.setLayoutParams(relative_params);
    }

    //设置控件水平居中
    public  void setWidget_pos(View view,int top){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(view.getLayoutParams());
        params.topMargin = (int) (single.Screen_Height * (float)top/1000);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        view.setLayoutParams(params);
    }

    //设置控件位置和大小
    public  void setWidget_pos(View view, int top, int left,int width,int height) {
        RelativeLayout.LayoutParams relative_params =
                new RelativeLayout.LayoutParams(view.getLayoutParams());
        relative_params.topMargin = (int) (single.Screen_Height * (float)top/1000);
        relative_params.leftMargin = (int) (single.Screen_Width * (float)left/1000);
        relative_params.width = (int) (single.Screen_Width*(float)width/1000);
        relative_params.height = (int) (single.Screen_Width*(float)height/1000);
        view.setLayoutParams(relative_params);

    }
}
