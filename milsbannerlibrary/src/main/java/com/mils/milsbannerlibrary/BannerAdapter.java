package com.mils.milsbannerlibrary;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mils.ecornerimageviewlibrary.view.ECornerImageView;

import java.io.File;

/**
 * Created by Administrator on 2019/1/3.
 */

public class BannerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private Context mContext;

    private ItemClickListener itemClickListener;

    private int currentPosition = 0;

    private Object[] images;

    private String TAG = "BannerAdapter";

    public BannerAdapter(Context context, ViewPager viewPager, Object[] images) {
        mContext = context;

        if (images instanceof String[]){
            this.images = (String[])images;
        }else if (images instanceof Integer[]){
            this.images = (Integer[])images;
        }else if (images instanceof File[]){
            this.images = (File[])images;
        }else if (images instanceof Uri[]){
            this.images = (Uri[])images;
        }else if (images instanceof Byte[]){
            this.images = (Byte[])images;
        }

        viewPager.clearOnPageChangeListeners();
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public int getCount() {
        return images.length > 2 ? Integer.MAX_VALUE : 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /*图片初始化，将要显示的图片放到container中缓存，
      viewpager的setOffscreenPageLimit（）方法用于设置缓存图片的数量。*/
    @Override
    public Object instantiateItem(ViewGroup container, int p) {
        final int position = p % images.length;
        Log.d(TAG, "posotion:" + position);
        ECornerImageView imageView = new ECornerImageView(mContext);
        imageView.setRadius(10);
        Glide.with(mContext).load(images[position]).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if (null != itemClickListener) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(position);
                }
            });
        }

        container.addView(imageView);
        return imageView;
    }

    /*当滑动的图片超出缓存范围，就会销毁缓存的图片。*/
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int index);
    }
}
