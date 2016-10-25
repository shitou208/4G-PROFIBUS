package cn.edu.jlu.a4g_profibus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SeeMachineActivity extends AppCompatActivity {
    private WebView webview;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_machine);
        dialog=new ProgressDialog(this);
        dialog.setTitle("温馨提示");
        dialog.setMessage("正在处理，请稍候……");
        webview = (WebView) findViewById(R.id.webview);
        //对webview进行设置
        //webview.getSettings().setSupportZoom(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //webview.getSettings().setBuiltInZoomControls(true);
        //webview.getSettings().setDisplayZoomControls(false);//隐藏Zoom缩放按钮
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new MyWebView());
        //进行业务逻辑判断，确定加载哪个页面
        Intent intent=getIntent();
        int type=intent.getIntExtra("type",0);
        switch (type){
            case 1:
                webview.loadUrl("1");
                break;
            case 2:
                webview.loadUrl("2");
                break;
            case 3:
                webview.loadUrl("3");
                break;
            case 4:
                webview.loadUrl("4");
                break;
            case 5:
                String para=intent.getStringExtra("name");
                webview.loadUrl("https://www.baidu.com/s?wd="+para);
                break;
        }
    }
    public class MyWebView extends WebViewClient {
        @Override
        //页面开始加载
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            dialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        //页面加载完成
        public void onPageFinished(WebView view, String url) {
            dialog.dismiss();
            super.onPageFinished(view, url);
        }

        @Override
        //加载出错
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(SeeMachineActivity.this, "无法连接到服务器！", Toast.LENGTH_SHORT).show();
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        //避免点击URL跳转到外部浏览器
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
