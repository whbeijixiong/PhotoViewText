package com.bwei.photoviewtext;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //图片资源
    private String[] imageUrls = new String[]{
            "http://eleteamdata.ygcr8.com/uploads/public/product/yin-pin/chao-ji-re-yin/han-guo-you-zi-cha/3.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151143.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151146.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151150.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151155.jpg",
            "http://mimg.xmeise.com/thumb/m/allimg/160203/1-160203151200.jpg"
    };
    private ViewPager viewpager;
    private LinearLayout line;
    private ArrayList<ImageView> list;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int currentItem = viewpager.getCurrentItem();
            currentItem++;
            viewpager.setCurrentItem(currentItem);
            handler.sendEmptyMessageDelayed(0,2000);

        }
    };
    private ArrayList<ImageView> clist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        line = (LinearLayout) findViewById(R.id.line);
        //获取图片
        getImage();
        //初始化小圆点
        initcircle();
        //适配数据
        Mypageadapter mypageadapter=new Mypageadapter(this,list,handler);
        viewpager.setAdapter(mypageadapter);
        //設置初始化的界面
        viewpager.setCurrentItem(list.size()*10000000);
        //發送延時操作
        //handler.sendEmptyMessageDelayed(0,2000);
        //监听换页变化
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //遍历图片集合
                for (int i=0;i<list.size();i++){
                    //索引相同即为亮点
                    if (i==position%list.size()){
                        clist.get(i).setImageResource(R.drawable.ago);
                    }else{
                        clist.get(i).setImageResource(R.drawable.later);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initcircle() {
        clist = new ArrayList<>();
        for (int i = 0; i <imageUrls.length; i++){
            ImageView imageCircle = new ImageView(this);
            if (i==0){
                imageCircle.setImageResource(R.drawable.ago);
            }else {
                imageCircle.setImageResource(R.drawable.later);
            }
            //默认圆点高度20
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            //设置间距
            params.setMargins(5,0,5,0);
            //放在容器中
            line.addView(imageCircle,params);
            clist.add(imageCircle);

        }
    }

    public void getImage() {
        list = new ArrayList<>();
        BitmapUtils bitmaputils = new BitmapUtils(this);
        for (int i = 0; i <imageUrls.length;i++){
            ImageView imageView = new ImageView(this);
            bitmaputils.display(imageView,imageUrls[i]);
            list.add(imageView);
        }
    }
}
