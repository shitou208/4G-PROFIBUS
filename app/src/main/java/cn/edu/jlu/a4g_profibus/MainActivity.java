package cn.edu.jlu.a4g_profibus;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private View view_home, view_search, view_center;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.myviewpager);
        //bottom_navigationbar初始化设置
        final BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页").setActiveColorResource(R.color.color1))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "快速查询").setActiveColorResource(R.color.color2))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "个人中心").setActiveColorResource(R.color.color3))
                .setFirstSelectedPosition(0)
                .initialise();
        //viewpager的初始化设置
        LayoutInflater inflater = getLayoutInflater();
        view_home = inflater.inflate(R.layout.layout_home, null);
        view_search = inflater.inflate(R.layout.layout_search, null);
        view_center = inflater.inflate(R.layout.layout_center, null);
        viewList = new ArrayList<View>();
        viewList.add(view_home);
        viewList.add(view_search);
        viewList.add(view_center);
        CustomAdapter pageAdapter = new CustomAdapter(viewList);
        viewPager.setAdapter(pageAdapter);
        //设置viewpager的滑动监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置bottom_navigation_bar点击事件
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position, false);//设置为false，切换时不带过渡效果，直接跳到指定页面
                //viewPager.setCurrentItem(position, true);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}
