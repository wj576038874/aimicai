package com.aimicai.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.aimicai.R;
import com.just.agentweb.IWebLayout;

public class WebLayout implements IWebLayout {

    private NestedScrollWebView nestedScrollWebView;
    private FrameLayout frameLayout;

    public WebLayout(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.weblayout ,null);
        frameLayout = view.findViewById(R.id.fl_container);
        nestedScrollWebView = view.findViewById(R.id.nsw);
    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return frameLayout;
    }

    @Nullable
    @Override
    public WebView getWebView() {
        return nestedScrollWebView;
    }
}
