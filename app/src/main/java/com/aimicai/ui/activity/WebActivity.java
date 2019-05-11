package com.aimicai.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aimicai.R;
import com.aimicai.base.BaseActivity;
import com.aimicai.utils.StatusBarUtil;
import com.aimicai.utils.ToastUtils;
import com.aimicai.widget.NestedScrollWebView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * ProjectName: aimicai
 * PackageName com.aimicai.ui.activity
 * Author: wenjie
 * Date: 2019-05-09 11:20
 * Description:
 */
public class WebActivity extends BaseActivity {

    private NestedScrollWebView nestedScrollWebView;
    private ProgressBar pvWeb;
    private String title;
    private TextView tv_detail_title;
    private ImageView iv_detail;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private String imgUrl;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_detail);
        initToolbar((Toolbar) findViewById(R.id.toolbar));
        nestedScrollWebView = findViewById(R.id.nswv_detail_content);
        pvWeb = findViewById(R.id.pb_web);
        tv_detail_title = findViewById(R.id.tv_detail_title);
        iv_detail = findViewById(R.id.iv_detail);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        initWebSetting(nestedScrollWebView.getSettings());
        initWebView();

        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        imgUrl = getIntent().getStringExtra("img");
        tv_detail_title.setText(title);
        nestedScrollWebView.loadUrl(url);
        Glide.with(this).load(imgUrl).into(iv_detail);

        //当collapsingToolbarLayout折叠后 toolbar的颜色
        Glide.with(this)
                .load(imgUrl)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(new RequestOptions().transform(new BlurTransformation(23, 4)))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        collapsingToolbarLayout.setContentScrim(resource);
                    }
                });
    }


    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, findViewById(R.id.toolbar));
    }

    /**
     * js接口
     */
    public class SupportJavascriptInterface {
        private Context context;

        public SupportJavascriptInterface(Context context) {
            this.context = context;
        }

        @JavascriptInterface
        public void openImage(final String img) {
            ToastUtils.showToast("asd");
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && nestedScrollWebView.canGoBack()) {
            // 返回上一页面
            nestedScrollWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void initWebView() {
        // 添加js交互接口类，并起别名 imagelistner
        nestedScrollWebView.addJavascriptInterface(new SupportJavascriptInterface(this),
                "imagelistner");
        nestedScrollWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                // html加载完成之后，添加监听图片的点击js函数
                addWebImageClickListner(view);
//                toolbar.setTitle(getToolbarTitle());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageStarted(view, url, favicon);
            }

            // 注入js函数监听
            protected void addWebImageClickListner(WebView webView) {
                // 这段js函数的功能就是，遍历所有的img节点，并添加onclick函数，
                // 函数的功能是在图片点击的时候调用本地java接口并传递url过去
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.imagelistner.openImage(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");
            }
        });

        nestedScrollWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    pvWeb.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pvWeb.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pvWeb.setProgress(newProgress);//设置进度值
                }
            }
        });

    }

    public static void displayBlurImg(Context context, final String imgUrl, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(imgUrl)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(new RequestOptions().transform(new BlurTransformation(23, 4)))
                .into(imageView);
    }

    protected void initWebSetting(WebSettings settings) {
        // 缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        // 保存表单数据
        settings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        settings.setSupportZoom(true);
        //        //是否支持手势缩放控制
        //        settings.setBuiltInZoomControls(true);
        //        是否隐藏原生的缩放控件
        //        settings.setDisplayZoomControls(false);
        // 启动应用缓存
        settings.setAppCacheEnabled(true);
        // 排版适应屏幕，只显示一列
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        //  页面加载好以后，再放开图片
//        settings.setBlockNetworkImage(false);
//        // 使用localStorage则必须打开
//        settings.setDomStorageEnabled(true);
//        settings.setDatabaseEnabled(true);
//        // WebView启用JavaScript执行。默认的是false。
        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
    }
}
