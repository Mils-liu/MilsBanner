package com.mils.milsbannerlibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2019/1/5.
 */

public class BannerConfig {

    private Object[] images;

    private ViewPager viewPager;

    private RadioGroup radioGroup;

    private Button btn_select,btn_default;

    private BannerAdapter bannerAdapter;

    private Timer timer = new Timer();

    private PageTransform pageTransform;

    /*是否播放轮播图*/
    private Boolean isLoop = true;

    private final int LOOPSTART = 2000;

    private final int LOOPPERIOD = 2000;

    private final int PAGEMARGIN = 30;

    private final int SCROLLDURATION = 1000;

    private final int DOT_PADDING = 20;

    private final int DOT_SELECT_COLOR = 0XFFFF4081;

    private final int DOT_DEFAULT_COLOR = 0XFFFFFFFF;

    /*轮播图循环起始时刻，单位毫秒*/
    private int loopStart = LOOPSTART;

    /*轮播图循环间隔时间，单位毫秒*/
    private int loopPeriod = LOOPPERIOD;

    /*page之间的距离*/
    private int pageMargin = PAGEMARGIN;

    /*轮播图滑动的速度*/
    private int scrollDuration = SCROLLDURATION;

    /*RadioGroup里item的间距*/
    private int dot_padding = DOT_PADDING;

    /*item选中的颜色*/
    private int dot_select_color = DOT_SELECT_COLOR;

    /*item未选中的颜色*/
    private int dot_default_color = DOT_DEFAULT_COLOR;

    //当前索引位置以及上一个索引位置
    private int index;
    private int preIndex;

    private Context mContext;

    private Activity mActivity;

    private String TAG = "BannerConfig";

    public BannerConfig(Context mContext){
        this.mContext = mContext;
        mActivity = (Activity)mContext;
        pageTransform = new PageTransform();
    }

    public BannerConfig(Context mContext, ViewPager viewPager, RadioGroup radioGroup, int[] images){
        this.mContext = mContext;
        mActivity = (Activity)mContext;
        this.viewPager = viewPager;
        this.radioGroup = radioGroup;
        //this.images = images;
        if (images.length<=1){
            isLoop = false;
        }
    }

    public BannerAdapter getBannerAdapter(){
        return this.bannerAdapter;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public void setBtn_select(Button btn_select) {
        this.btn_select = btn_select;
    }

    public void setBtn_default(Button btn_default) {
        this.btn_default = btn_default;
    }


    public void setLoop(Boolean isLoop){
        this.isLoop = isLoop;
    }

    public void setLoopStart(int loopStart) {
        this.loopStart = loopStart;
    }

    public void setLoopPeriod(int loopPeriod) {
        this.loopPeriod = loopPeriod;
    }

    public void setImages(Object[] images){
        this.images = images;
    }

    public void setPageMargin(int pageMargin) {
        this.pageMargin = pageMargin;
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    private void setMaxRotation(float maxRotation){
        pageTransform.setMaxRotation(maxRotation);
    }

    private void setMinAlpha(float minAlpha){
        pageTransform.setMinAlpha(minAlpha);
    }

    public void setDotPadding(int dot_padding) {
        this.dot_padding = dot_padding;
    }

    public void setDotSelectColor(int dot_select_color) {
        Log.d(TAG,"selectColor:"+dot_select_color);
        this.dot_select_color = dot_select_color;
    }

    public void setDotDefaultColor(int dot_default_color) {
        this.dot_default_color = dot_default_color;
    }

    public void init(){
        bannerAdapter = new BannerAdapter(mContext,viewPager,images);
        //设置page之间间距
        viewPager.setPageMargin(pageMargin);
        //设置缓存的页面数量
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(bannerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setPageTransformer(true, pageTransform);
        viewPager.setCurrentItem(images.length * 100);

        /*设置轮播图滑动速度*/
        ViewPagerScroller pagerScroller = new ViewPagerScroller(mContext);
        pagerScroller.setScrollDuration(scrollDuration);
        pagerScroller.initViewPagerScroll(viewPager);

        initRadioButton(images.length);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isLoop) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            index++;
                            viewPager.setCurrentItem(index);
                        }
                    });
                }
            }
        }, loopStart, loopPeriod);
    }

    /*page改变时的监听*/
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            index = position;
            setCurrentDot(index % images.length);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initRadioButton(int length) {
        /*动态设置item颜色*/
        GradientDrawable item_select = (GradientDrawable)btn_select.getBackground();
        item_select.setColor(dot_select_color);
        Log.d(TAG,"selectColor2:"+dot_select_color);
        Log.d(TAG,"selectColor:"+DOT_SELECT_COLOR);
        GradientDrawable item_default = (GradientDrawable)btn_default.getBackground();
        item_default.setColor(dot_default_color);

        for (int i = 0; i < length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.ind_selector);
            imageView.setPadding(dot_padding, 0, 0, 0);
            radioGroup.addView(imageView, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            radioGroup.getChildAt(0).setEnabled(false);
        }
    }

    private void setCurrentDot(int i) {
        if (radioGroup.getChildAt(i) != null) {
            //当前按钮不可改变
            radioGroup.getChildAt(i).setEnabled(false);
        }
        if (radioGroup.getChildAt(preIndex) != null) {
            //上个按钮可以改变
            radioGroup.getChildAt(preIndex).setEnabled(true);
            //当前位置变为上一个，继续下次轮播
            preIndex = i;
        }
    }

}
