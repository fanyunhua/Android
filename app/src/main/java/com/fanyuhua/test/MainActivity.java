package com.fanyuhua.test;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener{
    private FragmentTabHost fragmentTabHost;
    //这些数组必须一一对应
    private String[] tabTitles;

    private int[] tabImageView =
            {
                    R.drawable.home1,
                    R.drawable.more1,
                    R.drawable.add1,
                    R.drawable.user1
            };
    private int[] tabImageView2 =
            {
                    R.drawable.home2,
                    R.drawable.more2,
                    R.drawable.add2,
                    R.drawable.user2
            };
    Class[] fragments =
            {
                    Home.class,
                    More.class,
                    Add.class,
                    User.class
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabTitles= new String[]
        {
            this.getString(R.string.home),
                    this.getString(R.string.more),
                    this.getString(R.string.add),
                    this.getString(R.string.user)
        };
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.RealContentLL);
        initTab();
        fragmentTabHost.setOnTabChangedListener(this);
    }
    private void initTab() {
        for (int i = 0; i < tabTitles.length; i++) {
            TabHost.TabSpec spec = fragmentTabHost.newTabSpec(tabTitles[i]).setIndicator(getTabView(i));
            //注意注意注意
            //将spec加进来之后只是标签，相对应的内容还没有????
            // so 第二个参数是其对应的内容， 第三个参数填null就行
            fragmentTabHost.addTab(spec,fragments[i],null);

        }
    }

    View getTabView(int i) {
//        //将xml布局转换 xml->view
        View view = LayoutInflater.from(this).inflate(R.layout.item, null);
        //找到标签布局里的控件
        //注意是view布局下的   --view--
        TextView tabTitle = (TextView) view.findViewById(R.id.tabTitle);
        ImageView tabLv = (ImageView) view.findViewById(R.id.tabIv);
        /* 将图片和标题填进控件 */
        tabTitle.setText(tabTitles[i]);
        if (i == 0) {
            tabTitle.setTextColor(Color.BLUE);
            tabLv.setImageResource(tabImageView2[i]);
        } else {
            tabTitle.setTextColor(Color.GRAY);
            tabLv.setImageResource(tabImageView[i]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String s) {
        //找到当前点击的是哪个标签
        TabWidget tabWidget = fragmentTabHost.getTabWidget();
        for (int i = 0;i<tabTitles.length;i++)
        {
            View view = tabWidget.getChildAt(i);
            TextView tabTitle = (TextView) view.findViewById(R.id.tabTitle);
            ImageView tabLv = (ImageView) view.findViewById(R.id.tabIv);
            /* 将图片和标题填进控件 */
            tabTitle.setText(tabTitles[i]);
            //判断第几个是当前点击的tab
            if (i == fragmentTabHost.getCurrentTab()) {
                tabTitle.setTextColor(Color.BLUE);
                tabLv.setImageResource(tabImageView2[i]);
            } else {
                tabTitle.setTextColor(Color.GRAY);
                tabLv.setImageResource(tabImageView[i]);
            }
        }
    }
}
