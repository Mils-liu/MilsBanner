package com.mils.milsbanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mils.milsbannerlibrary.BannerAdapter;
import com.mils.milsbannerlibrary.MilsBanner;

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
        //milsBanner.Config().setPageMargin(0);
        milsBanner.setIndicatorMarginTop(-60);
        milsBanner.Config().setDotSelectColor(0xFFFF0400);
        milsBanner.Config().setDotDefaultColor(0xffffffff);
        milsBanner.init(images);
        milsBanner.BannerClick().setItemClickListener(new BannerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(MainActivity.this,"click:"+(++index),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
