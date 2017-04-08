package com.example.aria.sudokuweb.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.activity.SelectLevelAty;
import com.example.aria.sudokuweb.adapter.SelectLevelAdapter;
import com.example.aria.sudokuweb.controller.SelectLevelAtyController.LevelClickController;
import com.example.aria.sudokuweb.controller.generic.GenericBtnTouchEvent;
import com.example.aria.sudokuweb.model.ChessInfo;
import com.example.aria.sudokuweb.model.SingleValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aria on 2016/12/3.
 */

public class SelectLevelView extends RelativeLayout{
    private Context context;
    private int Screen_Height;
    private int Screen_Width;
    private List<View> pageList;
    private Button[] LevelBtns;

    public SelectLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    //初始化界面
    public void iniView(){
        //设置屏幕宽高参数
        Screen_Height = SingleValues.getInstance().getScreen_Height();
        Screen_Width = SingleValues.getInstance().getScreen_Width();


        //设置文字Logo
        //描述标题
        TextView title_jiandan = (TextView) findViewById(R.id.select_title_jiandan);
        SingleValues.getInstance().setWidget_pos(title_jiandan,getResources().getInteger(R.integer.SELECT_TITLE_JIANDAN_TOP),getResources().getInteger(R.integer.SELECT_TITLE_JIANDAN_LEFT));
        title_jiandan.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(title_jiandan);

        TextView title_de = (TextView)findViewById(R.id.select_title_de);
        SingleValues.getInstance().setWidget_pos(title_de,getResources().getInteger(R.integer.SELECT_TITLE_DE_TOP),getResources().getInteger(R.integer.SELECT_TITLE_DE_LEFT));
        SingleValues.getInstance().setFont(title_de);
        title_de.setTextColor(Color.WHITE);

        TextView title_shudu = (TextView) findViewById(R.id.select_title_shudu);
        SingleValues.getInstance().setWidget_pos(title_shudu,getResources().getInteger(R.integer.SELECT_TITLE_SHUDU_TOP),getResources().getInteger(R.integer.SELECT_TITLE_SHUDU_LEFT));
        SingleValues.getInstance().setFont(title_shudu);
        title_shudu.setTextColor(Color.WHITE);


        //设置标题
        TextView SelectTitle = (TextView) findViewById(R.id.select_title);
        SingleValues.getInstance().setWidget_pos(SelectTitle,getResources().getInteger(R.integer.SELECT_TITLE_TOP));
        SelectTitle.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(SelectTitle);

        //设置返回按钮
        Button SelectBackBtn = (Button) findViewById(R.id.select_btn_back);
        SingleValues.getInstance().setWidget_pos(SelectBackBtn,getResources().getInteger(R.integer.SELECT_BACKBTN_TOP),
                getResources().getInteger(R.integer.SELECT_BACKBTN_LEFT));
        SingleValues.getInstance().setFont(SelectBackBtn);
        SelectBackBtn.setTextColor(Color.WHITE);
    }

    //初始化分页
    public void iniViewPager(){
        //设置关卡ViewPager
        ViewPager SelectLevelPager = (ViewPager) findViewById(R.id.select_viewpager);
        SingleValues.getInstance().setWidget_pos(SelectLevelPager,getResources().getInteger(R.integer.SELECT_VIEWPAGER_TOP));
        pageList = new ArrayList<View>();
        List<ChessInfo> chessList = SingleValues.getInstance().getChessList();
        LevelBtns = new Button[chessList.size()];
        LayoutInflater inflater = ((SelectLevelAty)context).getLayoutInflater();
        for (int i=0;i<(chessList.size()+2)/3;i++){
            View page = inflater.inflate(R.layout.level_single_page,null);
            page.setId(10001+i);

            for (int j=0;j<3;j++){
                Button button;
                switch (j){
                    case 0:
                        button = (Button) page.findViewById(R.id.Level_Btn1);
                        break;
                    case 1:
                        button = (Button) page.findViewById(R.id.Level_Btn2);
                        break;
                    case 2:
                        button = (Button) page.findViewById(R.id.Level_Btn3);
                        break;
                    default:
                        button = new Button(context);
                }

                if (i*3+j<chessList.size()){
                    ChessInfo info = chessList.get(i*3+j);
                    button.setText(info.getTitle());
                    button.setId(info.getId());
                    SingleValues.getInstance().setFont(button);
                    LevelBtns[i*3+j] = button;
                }else {
                    button.setVisibility(INVISIBLE);
                    button.setClickable(false);
                }
            }

            pageList.add(page);
        }
        SelectLevelAdapter adapter = new SelectLevelAdapter(pageList);
        SelectLevelPager.setAdapter(adapter);
    }

    //复原关卡按钮的状态
    public void setLevelBtnsState(int btn_click_id){
        if (btn_click_id < 0 )return;
        for (int i=0;i<LevelBtns.length;i++){
            if (LevelBtns[i].getId() == btn_click_id){
                LevelBtns[i].setBackgroundResource(R.drawable.btn_frame);
                LevelBtns[i].setTextColor(Color.WHITE);
                break;
            }


        }
    }

    //设置监听器
    public void setBackBtnListener(OnClickListener listener){
        findViewById(R.id.select_btn_back).setOnClickListener(listener);
        findViewById(R.id.select_btn_back).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setLevelBtnListener(OnClickListener listener){
        for (int i=0;i<LevelBtns.length;i++){
            LevelBtns[i].setOnClickListener(listener);
            LevelBtns[i].setOnTouchListener(((LevelClickController)listener).getLevelBtnTouchEvent());
        }

    }

    public void setLevelPageListener(ViewPager.OnPageChangeListener listener){
        ((ViewPager)findViewById(R.id.select_viewpager)).setOnPageChangeListener(listener);
    }
}
