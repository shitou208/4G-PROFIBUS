package cn.edu.jlu.a4g_profibus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    class HomeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()){
                case R.id.button_seeMachine:
                    Intent intent_type1=new Intent(MainActivity.this,cn.edu.jlu.a4g_profibus.SeeMachineActivity.class);
                    intent_type1.putExtra("type",1);
                    startActivity(intent_type1);
                    //Toast.makeText(MainActivity.this,"点击了查看设备",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_addMachine:
                    Intent intent_type2=new Intent(MainActivity.this,cn.edu.jlu.a4g_profibus.SeeMachineActivity.class);
                    intent_type2.putExtra("type",2);
                    startActivity(intent_type2);
                    // Toast.makeText(MainActivity.this,"点击了添加设备",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_deleteMachine:
                    Intent intent_type3=new Intent(MainActivity.this,cn.edu.jlu.a4g_profibus.SeeMachineActivity.class);
                    intent_type3.putExtra("type",3);
                    startActivity(intent_type3);
                    //Toast.makeText(MainActivity.this,"点击了删除设备",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_controlMachine:
                    Intent intent_type4=new Intent(MainActivity.this,cn.edu.jlu.a4g_profibus.SeeMachineActivity.class);
                    intent_type4.putExtra("type",4);
                    startActivity(intent_type4);
                    //Toast.makeText(MainActivity.this,"点击了控制设备",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_confirm_search:
                    if(TextUtils.isEmpty(edittextSearchName.getText().toString())){
                        Toast.makeText(MainActivity.this,"请填写设备名称！",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent_type5=new Intent(MainActivity.this,cn.edu.jlu.a4g_profibus.SeeMachineActivity.class);
                        intent_type5.putExtra("type",5);
                        intent_type5.putExtra("name",edittextSearchName.getText().toString());
                        startActivity(intent_type5);
                    }
                    break;
            }
        }
    }
    private ViewPager viewPager;
    private View view_home, view_search, view_center;
    private List<View> viewList;
    private Button buttonSeeMachine;
    private Button buttonControlMachine;
    private Button buttonAddMachine;
    private Button buttonDeleteMachine;
    private MaterialEditText edittextSearchName;
    private Button buttonConfirmSearch;
    private TextView textviewLoginInfo;
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
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home, "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.search, "快速查询").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.center, "个人中心").setActiveColorResource(R.color.colorPrimary))
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
        //初始化各个页面的控件
        InitPagerWidget();
    }
    public void InitPagerWidget(){
        buttonSeeMachine = (Button) view_home.findViewById(R.id.button_seeMachine);
        buttonControlMachine = (Button) view_home.findViewById(R.id.button_controlMachine);
        buttonAddMachine = (Button) view_home.findViewById(R.id.button_addMachine);
        buttonDeleteMachine = (Button) view_home.findViewById(R.id.button_deleteMachine);
        edittextSearchName = (MaterialEditText) view_search.findViewById(R.id.edittext_searchName);
        buttonConfirmSearch = (Button) view_search.findViewById(R.id.button_confirm_search);
        textviewLoginInfo = (TextView) view_center.findViewById(R.id.textview_loginInfo);
        buttonSeeMachine.setOnClickListener(new HomeClickListener());
        buttonControlMachine.setOnClickListener(new HomeClickListener());
        buttonAddMachine.setOnClickListener(new HomeClickListener());
        buttonDeleteMachine.setOnClickListener(new HomeClickListener());
        buttonConfirmSearch.setOnClickListener(new HomeClickListener());

        //检查登录状态，设置个人中心信息
        SharedPreferences login=getSharedPreferences("login",MODE_PRIVATE);
        String username=login.getString("username","未登录");
        textviewLoginInfo.setText("当前登录用户："+username);
    }
}



