package com.mils.milsbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mils.milsbannerlibrary.MilsBanner;
import com.mils.milsbannerlibrary.BannerAdapter;


public class MainActivity extends AppCompatActivity {

    private Integer[] images = new Integer[]{R.drawable.img01,R.drawable.img02,R.drawable.img03};
    private String[] images2 = new String[]{
            "https://ss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/714fc51190ef76c6bee77a1d9416fdfaaf516765.jpg",
            "https://i0.hdslb.com/bfs/article/1cef0d1228283c1418f0554734a4c1ff8697eab2.png@1320w_742h.webp",
            "https://i0.hdslb.com/bfs/article/c6e4e3024bcf83edf46c62600dc246058d88f86f.png@1320w_742h.webp"};
    private MilsBanner milsBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        milsBanner = (MilsBanner)findViewById(R.id.milsbanner);

        milsBannerConfig(milsBanner);

        /*初始化Banner*/
        milsBanner.init(images);
        milsBanner.BannerClick().setItemClickListener(new BannerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(MainActivity.this,"click:"+(++index),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void milsBannerConfig(MilsBanner milsBanner){
        /*指示器的位置设置*/
        milsBanner.setIndicatorMarginTop(0);
        milsBanner.setIndicatorMarginRight(20);
        milsBanner.setIndicatorMarginLeft(0);
        milsBanner.setIndicatorMarginBottom(0);
        milsBanner.setIndicatorAlignt(MilsBanner.ALIGN_PARENT_RIGHT);

        /*设置是否隐藏指示器*/
        milsBanner.setIndicatorHide(false);


        /*设置Page的间距*/
        milsBanner.Config().setPageMargin(20);
        /*设置指示器选中的颜色*/
        milsBanner.Config().setDotSelectColor(0xFFFF0400);
        /*设置指示器默认的颜色*/
        milsBanner.Config().setDotDefaultColor(0xffffffff);
        /*设置指示器点的间距*/
        milsBanner.Config().setDotPadding(20);
        /*设置指示器点的大小*/
        milsBanner.Config().setDotSize(10);
        /*设置Banner开始循环播放的时刻（单位ms）*/
        milsBanner.Config().setLoopStart(2000);
        /*设置Banner循环播放的间隔（单位ms）*/
        milsBanner.Config().setLoopPeriod(2000);
        /*设置两边page的透明度（0f~1.0f）*/
        milsBanner.Config().setMinAlpha(1.0f);
        /*设置两边page的旋转的角度（0~360 你觉得好看就行。。。）*/
        milsBanner.Config().setMaxRotation(0);
        /*设置Banner播放的速度（单位ms）*/
        milsBanner.Config().setScrollDuration(2000);
    }
}
