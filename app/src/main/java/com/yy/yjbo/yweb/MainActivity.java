package com.yy.yjbo.yweb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * webview与android最基本的交互
 *
 * @qq 1457521527
 * @author yjbo  @time 2017/4/15 0:09
 */
public class MainActivity extends AppCompatActivity {
    private final int handleMsg_12 = 12;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint({"AddJavascriptInterface", "JavascriptInterface"})
    private void initView() {
        initWidgetView();
        initData();
    }

    //初始化控件
    private void initWidgetView() {
        webView = (WebView) findViewById(R.id.swipe_target);
    }

    /**
     * 初始化webview
     */
    private void initData() {

        try {
            webView.loadUrl("file:///android_asset/testjs.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        settings.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
        settings.setUseWideViewPort(true);
        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
        //引用http://blog.csdn.net/sensky_yuan/article/details/17356843
        //关闭硬件加速;sdk19以下（不包含19）不设置这个就会出现不手动点击第一个页面不出现的情况；
        if (Build.VERSION.SDK_INT >= 19) {
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        //设置 缓存模式
        //settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 19) {//设置是否自动加载图片
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
    }
    /**
     * js调用
     * @author yjbo  @time 2017/4/15 0:19
     */
    class InJavaScriptLocalObj {

        @JavascriptInterface
        public void goback(String kind) {
            Message msg = new Message();
            msg.what = handleMsg_12;
            msg.obj = kind;
            mhandler.sendMessage(msg);
        }
    }
    WeakHandler mhandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String result = (String) msg.obj;
            switch (msg.what) {
                case handleMsg_12:
                    if ("1".equals(result)) {
                        showToast(result);
                    } else if ("2".equals(result)) {
                        showToast(result);
                    } else if ("3".equals(result)) {
                        showToast(result);
                    }
                    break;
            }
            return false;
        }
    });
    //吐司
    private void showToast(String result) {
        Toast.makeText(MainActivity.this,"交互"+result,Toast.LENGTH_SHORT).show();
    }
}
