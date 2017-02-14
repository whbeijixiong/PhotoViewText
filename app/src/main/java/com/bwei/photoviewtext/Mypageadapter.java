package com.bwei.photoviewtext;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 作    者 ： 文欢
 * 时    间 ： 2017/2/14.
 * 描    述 ：
 * 修改时间 ：
 */
public class Mypageadapter extends PagerAdapter{
    private Context context;
    private List<ImageView> list;
    private Handler handler;
    public Mypageadapter(Context context, List<ImageView> list,Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final int i = position % list.size();
        ImageView imageView = list.get(i);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SecondActivity.class);
                intent.putExtra("p",i);
                context.startActivity(intent);
            }
        });
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position % list.size()));
    }
}
