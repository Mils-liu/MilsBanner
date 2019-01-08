package com.mils.milsbannerlibrary;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2019/1/6.
 */

public class MilsBanner extends RelativeLayout implements View.OnTouchListener{

    private String TAG = "MilsBanner";

    private BannerConfig bannerConfig;

    private ViewPager viewPager;

    private RadioGroup radioGroup;

    private Button btn_select, btn_default;

    /*是否隐藏指示器*/
    private Boolean isIndHide = false;

    public MilsBanner(Context mContext, AttributeSet attrs){
        super(mContext,attrs);
        LayoutInflater.from(mContext).inflate(R.layout.milsbanner,this,true);
        this.setClipChildren(false);
        this.setLongClickable(true);
        viewPager = (ViewPager)findViewById(R.id.milsbanner_view_Pager);
        radioGroup = (RadioGroup)findViewById(R.id.milsbanner_radio_group);
        btn_select = (Button)findViewById(R.id.btn_select);
        btn_default = (Button)findViewById(R.id.btn_default);
        viewPager.setOnTouchListener(this);
        bannerConfig = new BannerConfig(mContext);
    }

    public void setIndHide(Boolean isIndHide) {this.isIndHide = isIndHide;}

    /*设置指示器与顶部Banner的距离*/
    public void setIndicatorMarginTop(int dotMarginTop){
        LayoutParams lp = (LayoutParams)radioGroup.getLayoutParams();
        lp.topMargin = dotMarginTop;
        radioGroup.setLayoutParams(lp);
    }

    public BannerConfig Config(){
        return bannerConfig;
    }

    public BannerAdapter BannerClick(){
        return bannerConfig.getBannerAdapter();
    }

    public void init(Object[] images){
        bannerConfig.setImages(images);
        initBanner();
    }

    public void initBanner(){
        if (isIndHide){
            radioGroup.setVisibility(GONE);
        }
        bannerConfig.setViewPager(viewPager);
        bannerConfig.setRadioGroup(radioGroup);
        bannerConfig.setBtn_select(btn_select);
        bannerConfig.setBtn_default(btn_default);
        bannerConfig.init();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG,"actiondown");
                break;
            case MotionEvent.ACTION_MOVE:
                bannerConfig.setLoop(false);//MilsBanner停止循环播放
                Log.d(TAG,"actionmove");
                break;
            case MotionEvent.ACTION_UP:
                bannerConfig.setLoop(true);//MilsBanner开始循环播放
                Log.d(TAG,"actionup");
                break;
            default:
                break;
        }
        return false;//让事件向下传递，不然viewpage无法被拖动
    }
}
