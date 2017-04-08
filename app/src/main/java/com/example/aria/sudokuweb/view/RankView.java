package com.example.aria.sudokuweb.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.activity.RankAty;
import com.example.aria.sudokuweb.adapter.RankListViewAdapter;
import com.example.aria.sudokuweb.adapter.RankViewPagerAdapter;
import com.example.aria.sudokuweb.controller.generic.GenericBtnTouchEvent;
import com.example.aria.sudokuweb.model.RankInfo;
import com.example.aria.sudokuweb.model.SingleValues;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankView extends RelativeLayout{

    private int Screen_Height;
    private int Screen_Width;
    private List<RankInfo> rankList_local;
    private List<RankInfo> rankList_global;


    public RankView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //初始化界面
    public void iniView(){
        //设置屏幕宽高参数
        Screen_Height = SingleValues.getInstance().getScreen_Height();
        Screen_Width = SingleValues.getInstance().getScreen_Width();

        //描述标题
        TextView title_jiandan = (TextView) findViewById(R.id.rank_title_jiandan);
        SingleValues.getInstance().setWidget_pos(title_jiandan,getResources().getInteger(R.integer.RANK_TITLE_JIANDAN_TOP),getResources().getInteger(R.integer.RANK_TITLE_JIANDAN_LEFT));
        title_jiandan.setTextColor(Color.WHITE);
        SingleValues.getInstance().setFont(title_jiandan);

        TextView title_de = (TextView)findViewById(R.id.rank_title_de);
        SingleValues.getInstance().setWidget_pos(title_de,getResources().getInteger(R.integer.RANK_TITLE_DE_TOP),getResources().getInteger(R.integer.RANK_TITLE_DE_LEFT));
        SingleValues.getInstance().setFont(title_de);
        title_de.setTextColor(Color.WHITE);

        TextView title_shudu = (TextView) findViewById(R.id.rank_title_shudu);
        SingleValues.getInstance().setWidget_pos(title_shudu,getResources().getInteger(R.integer.RANK_TITLE_SHUDU_TOP),getResources().getInteger(R.integer.RANK_TITLE_SHUDU_LEFT));
        SingleValues.getInstance().setFont(title_shudu);
        title_shudu.setTextColor(Color.WHITE);

        //描述排行标题
        TextView title_rank = (TextView) findViewById(R.id.rank_title);
        SingleValues.getInstance().setFont(title_rank);
        SingleValues.getInstance().setWidget_pos(title_rank,getResources().getInteger(R.integer.RANK_RANKTITLE_TOP));

        //描述第一条下横线
//        TextView rank_line1 = (TextView) findViewById(R.id.rank_line1);
//        SingleValues.getInstance().setWidget_pos(rank_line1,getResources().getInteger(R.integer.RANK_LINE1_TOP));

        //描述菜单布局
        LinearLayout rank_menuLayout = (LinearLayout) findViewById(R.id.rank_menu_layout);
        SingleValues.getInstance().setWidget_pos(rank_menuLayout,getResources().getInteger(R.integer.RANK_MENULAYOUT_TOP));
        TextView rank_local = (TextView) findViewById(R.id.rank_local);
        SingleValues.getInstance().setFont(rank_local);
        TextView rank_global = (TextView) findViewById(R.id.rank_global);
        SingleValues.getInstance().setFont(rank_global);

        //描述分页标题布局
        LinearLayout rank_list_title_layout = (LinearLayout) findViewById(R.id.rank_list_title_layout);
        SingleValues.getInstance().setWidget_pos(rank_list_title_layout,getResources().getInteger(R.integer.RANK_PAGE_TITLE_LAYOUT_TOP));
        TextView rank_level_title = (TextView) findViewById(R.id.rank_level_title);
        SingleValues.getInstance().setFont(rank_level_title);
        SingleValues.getInstance().setFont((TextView)findViewById(R.id.rank_username_title));
        SingleValues.getInstance().setFont((TextView)findViewById(R.id.rank_usetime_title));

        //描述分页标题下的下划线
        TextView line2 = (TextView) findViewById(R.id.rank_line2);
        RelativeLayout.LayoutParams line_params = new RelativeLayout.LayoutParams(line2.getLayoutParams());
        line_params.width = Screen_Width/2 - 10;
        line_params.addRule(RelativeLayout.BELOW,R.id.rank_menu_layout);
//        line_params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        line_params.topMargin = 25;
        line2.setLayoutParams(line_params);

        //设置返回按钮
        Button GameBackBtn = (Button) findViewById(R.id.rank_btn_back);
        SingleValues.getInstance().setWidget_pos(GameBackBtn,getResources().getInteger(R.integer.RANK_BACKBTN_TOP),
                getResources().getInteger(R.integer.RANK_BACKBTN_LEFT));
        SingleValues.getInstance().setFont(GameBackBtn);
        GameBackBtn.setTextColor(Color.WHITE);
    }

    //描述排行分页
    public void iniRankPager(){

        //初始化参数
        rankList_global = SingleValues.getInstance().getRankList_global();
        rankList_local = SingleValues.getInstance().getRankList_local();

        List<View> pageList = new ArrayList<View>();
        LayoutInflater inflater =((RankAty)getContext()).getLayoutInflater();
        View page1 = inflater.inflate(R.layout.rank_single_page,null);
        ListView listView1 = (ListView) page1.findViewById(R.id.rank_listview);
        listView1.setAdapter(new RankListViewAdapter(getContext(),rankList_local));
        pageList.add(page1);

        View page2 = inflater.inflate(R.layout.rank_single_page,null);
        ListView listView2 = (ListView) page2.findViewById(R.id.rank_listview);
        listView2.setAdapter(new RankListViewAdapter(getContext(),rankList_global));
        pageList.add(page2);

        ViewPager viewPager = (ViewPager) findViewById(R.id.rank_viewPager);
        viewPager.setAdapter(new RankViewPagerAdapter(pageList));
//        SingleValues.getInstance().setWidget_pos(viewPager,getResources().getInteger(R.integer.RANK_VIEWPAGER_TOP));
    }

    //设置当前排行分页
    public void SwitchToPages(int position){
        if (position<2 && position>=0)
        ((ViewPager)findViewById(R.id.rank_viewPager)).setCurrentItem(position);
    }

    //设置排行下白线的位置
    public void SwitchLinePosition(boolean isLeft){
        TextView textView = (TextView) findViewById(R.id.rank_line2);
        if (isLeft){
            float current_X = textView.getTranslationX();
            ObjectAnimator animator = ObjectAnimator.ofFloat(textView,"translationX",current_X,(float) Screen_Width/2);
            animator.setDuration(200);
            animator.start();
        }else {

            float current_X = textView.getTranslationX();
            ObjectAnimator animator = ObjectAnimator.ofFloat(textView,"translationX",current_X,0);
            animator.setDuration(200);
            animator.start();
        }
    }

    //配置监听器
    public void setBackBtnListener(OnClickListener listener){
        findViewById(R.id.rank_btn_back).setOnClickListener(listener);
        findViewById(R.id.rank_btn_back).setOnTouchListener(new GenericBtnTouchEvent());
    }

    public void setRankingChangeListener(OnClickListener listener){
        findViewById(R.id.rank_local).setOnClickListener(listener);
        findViewById(R.id.rank_global).setOnClickListener(listener);
    }

    public void setRankingPageListener(ViewPager.OnPageChangeListener listener){
        ((ViewPager)findViewById(R.id.rank_viewPager)).setOnPageChangeListener(listener);
    }
}
