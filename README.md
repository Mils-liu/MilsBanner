# MilsBanner

# 效果
gif里是自动轮播，被我拖动和点击的效果
##### 1.默认配置
![默认配置](https://upload-images.jianshu.io/upload_images/7019098-b09e1cd1a3e7b531.gif)
##### 2.自定义配置
![自定义配置](https://upload-images.jianshu.io/upload_images/7019098-31c72842ab7f5d58.gif)
# 使用
##### 1. 依赖
````java
allprojects {
    repositories {
       ...
        maven { url 'https://www.jitpack.io' }
    }
}
````
````java
dependencies {
    ...
    compile 'com.github.Mils-liu:MilsBanner:v1.0.1'
}
````
##### 2. 加载的图片
我是用Glide来加载图片，理论上是可以加载Integer、String、Byte、File、Uri类型的图片。但我只试过Integer和String（太懒了。。。）。
````java
private Integer[] images = new Integer[]{R.drawable.img01,
                                          R.drawable.img02,R.drawable.img03};
````
##### 3. 配置
a.默认配置
````java
MilsBanner milsBanner = (MilsBanner)findViewById(R.id.milsbanner);
milsBanner.init(images);
//点击事件
milsBanner.BannerClick().setItemClickListener(new BannerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(MainActivity.this,"click:"+(++index),Toast.LENGTH_SHORT).show();
            }
        });
````
b.自定义配置
自定义配置得写在milsBanner.init(images);之前
````java
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
````
# END
github地址：https://github.com/Mils-liu/MilsBanner  
简书地址：https://www.jianshu.com/p/8769373c01e3
