package cn.edu.jlu.a4g_profibus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomActivity extends Activity {
    private ImageView imageview;
    private TextView textviewSlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        imageview = (ImageView) findViewById(R.id.imageview);
        textviewSlogin = (TextView) findViewById(R.id.textview_slogin);
        //设置透明度渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        //设置抖动效果
        TranslateAnimation translateAnimation = new TranslateAnimation(textviewSlogin.getWidth(),
                textviewSlogin.getWidth() + 10, textviewSlogin.getHeight(), textviewSlogin.getHeight());

        // 利用 CycleInterpolator 参数 为float 的数  表示 抖动的次数，而抖动的快慢是由 duration 和 CycleInterpolator 的参数的大小 联合确定的
        translateAnimation.setInterpolator(new CycleInterpolator(4f));
        translateAnimation.setDuration(1000);
        //发送延时消息
        Message message=new Message();
        message.what=1;
        handler.sendMessageDelayed(message,3000);
        imageview.startAnimation(alphaAnimation);
        textviewSlogin.startAnimation(translateAnimation);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //跳转
                    Intent intent = new Intent(WelcomActivity.this, cn.edu.jlu.a4g_profibus.MainActivity.class);
                    startActivity(intent);
                    WelcomActivity.this.finish();
            }
        }
    };
}
