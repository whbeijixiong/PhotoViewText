package com.bwei.photoviewtext;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作    者 ： 文欢
 * 时    间 ： 2017/2/14.
 * 描    述 ：
 * 修改时间 ：
 */
public class SecondActivity extends FragmentActivity{
    private ViewPager viewPager;
    //图片资源
    private String[] imageUrls = new String[] {
            "http://eleteamdata.ygcr8.com/uploads/public/product/yin-pin/chao-ji-re-yin/han-guo-you-zi-cha/3.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151143.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151146.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151150.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151155.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151200.jpg"};
    private LinearLayout line;
    private List<ImageView> imagelist;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int currentItem = viewPager.getCurrentItem();
            currentItem++;
            viewPager.setCurrentItem(currentItem);
            handler.sendEmptyMessageDelayed(0,2000);

        }
    };
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        line = (LinearLayout) findViewById(R.id.line2);
        //适配数据
        PictureSlidePagerAdapter mypageadapter=new PictureSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mypageadapter);
        //設置初始化的界面
        p = getIntent().getIntExtra("p", 0);
        //初始化小圆点
        initcircle();
        viewPager.setCurrentItem(p);
        //發送延時操作
        //handler.sendEmptyMessageDelayed(0,2000);
        //监听换页变化
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //遍历图片集合
                for (int i=0;i<imagelist.size();i++){
                    //索引相同即为亮点
                    if (i==position){
                        imagelist.get(i).setImageResource(R.drawable.ago);
                    }else{
                        imagelist.get(i).setImageResource(R.drawable.later);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initcircle() {
        //存放圆点
        imagelist = new ArrayList<>();
        for (int i=0;i<imageUrls.length;i++){
            ImageView imageView=new ImageView(this);
            //第一个默认为亮点
            if(i==p){
                imageView.setImageResource(R.drawable.ago);
            }else{
                imageView.setImageResource(R.drawable.later);
            }
            //默认圆点高宽20
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            //设置间距（左上右下）
            params.setMargins(5,0,5,0);
            //放在容器中
            line.addView(imageView,params);
            imagelist.add(imageView);
        }
    }

    private  class PictureSlidePagerAdapter extends FragmentPagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(imageUrls[position]);
        }

        @Override
        public int getCount() {
            return imageUrls.length;
        }

    }
}
