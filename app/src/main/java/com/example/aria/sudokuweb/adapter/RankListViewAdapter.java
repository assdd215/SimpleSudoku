package com.example.aria.sudokuweb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.activity.RankAty;
import com.example.aria.sudokuweb.model.RankInfo;
import com.example.aria.sudokuweb.model.SingleValues;

import java.util.List;

/**
 * Created by Aria on 2016/12/5.
 */

public class RankListViewAdapter extends BaseAdapter{

    private List<RankInfo> rankList;
    private Context context;
    public RankListViewAdapter(Context context,List<RankInfo> rankList){
        this.rankList = rankList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rankList.size();
    }

    @Override
    public Object getItem(int i) {
        return rankList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        RankInfo rankInfo = rankList.get(position);
        LayoutInflater inflater = ((RankAty)context).getLayoutInflater();
        view = inflater.inflate(R.layout.rank_item,null);
        TextView level_textview = (TextView) view.findViewById(R.id.rank_item_level);
        level_textview.setText(rankInfo.getTitle());
        SingleValues.getInstance().setFont(level_textview);

        TextView username_textview = (TextView) view.findViewById(R.id.rank_item_username);
        username_textview.setText(rankInfo.getUsername());
        SingleValues.getInstance().setFont(username_textview);

        TextView usetime_textview = (TextView) view.findViewById(R.id.rank_item_usetime);
        usetime_textview.setText(String.valueOf(rankInfo.getUsetime()));
        SingleValues.getInstance().setFont(usetime_textview);

        return view;
    }
}
