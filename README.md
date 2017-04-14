# yweb
为了将html页面能够显示在android手机上而做的框架

#realse 1.0 : 写一个最简单的android与html5交互的案例(2017年4月15日01:18:01)
----------------------------------------------------------------------------------------------------------------------------------
效果：
<p><img src="https://github.com/hytcyjb/yweb/blob/master/screenshot/jdfw.gif?raw=true" width="327" height="658" marge="20"></p>

代码摘取：
<pre><code>
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
    ...
</code></pre>

<p><img src="https://github.com/hytcyjb/yweb/blob/master/screenshot/app_pic1.png?raw=true" width="327" height="658" marge="20"></p>