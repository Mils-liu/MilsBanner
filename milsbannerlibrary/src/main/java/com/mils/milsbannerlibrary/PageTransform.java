package com.mils.milsbannerlibrary;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2019/1/5.
 */

public class PageTransform implements ViewPager.PageTransformer{

    private float maxRotation = 0;

    private float minAlpha = 1.0f;

    public void setMaxRotation(float maxRotation) {
        this.maxRotation = maxRotation;
    }

    public void setMinAlpha(float minAlpha) {
        this.minAlpha = minAlpha;
    }

    @Override
    public void transformPage(View view, float position) {
        if (position < -1) {
            //透明度
            view.setAlpha(minAlpha);
            //旋转
            view.setRotation(maxRotation * -1);
            //位置偏移
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());
        } else if (position <= 1) {
            if (position < 0) {
                //position是0到-1的变化,1+position就是从1到0的变化
                //position为width/2到width的变化
                float factor = minAlpha + (1 - minAlpha) * (1 + position);
                view.setAlpha(factor);

                view.setRotation(maxRotation * position);

                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
            } else {
                float factor = minAlpha + (1 - minAlpha) * (1 - position);
                view.setAlpha(factor);

                view.setRotation(maxRotation * position);

                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight());
            }
        } else {
            view.setAlpha(minAlpha);

            view.setRotation(maxRotation);

            view.setPivotX(0);
            view.setPivotY(view.getHeight());
        }
    }

}
