package com.example.aria.sudokuweb.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aria.sudokuweb.R;
import com.example.aria.sudokuweb.model.SingleValues;

public class RuleAty extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_aty);

        TextView RuleTitle = (TextView) findViewById(R.id.RuleTitle);
        SingleValues.getInstance().setFont(RuleTitle);
        RuleTitle.setTextColor(Color.WHITE);

        TextView RuleContent = (TextView) findViewById(R.id.RuleContent);
        SingleValues.getInstance().setFont(RuleContent);
        RuleContent.setTextColor(Color.WHITE);

    }


}
