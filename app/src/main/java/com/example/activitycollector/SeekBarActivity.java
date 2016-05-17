package com.example.activitycollector;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar;
/**
 * Created by Administrator on 2016/5/10.
 */
public class SeekBarActivity extends Activity implements android.widget.SeekBar.OnSeekBarChangeListener {
    private  SeekBar seekBar;
    private TextView tv1;
    private TextView tv2;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.seekbar);
        this.seekBar = (android.widget.SeekBar)this.findViewById(R.id.seekBar);
        this.tv1 = (TextView)this.findViewById(R.id.tv1);
        this.tv2 = (TextView)this.findViewById(R.id.tv2);
        this.seekBar.setOnSeekBarChangeListener(this);
    }

    public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
        this.tv1.setText("正在拖动");
        this.tv2.setText("当前数值：" + progress);
    }

    public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
        this.tv1.setText("开始拖动");
    }

    public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
        this.tv1.setText("停止拖动");
    }
}
