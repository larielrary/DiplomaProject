package com.example.diplomaproject.ui.web;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;


public class SiteFragment extends Fragment {

    TextView contentView;
    String contentText = null;
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_site, container, false);
        contentView = root.findViewById(R.id.content);
        webView = root.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Log.d("WEB_VIEW_TEST", "error code:" + errorCode + " - " + description);
            }

        });
        webView.loadUrl("https://www.gstu.by/");
        if (contentText != null) {
            webView.loadData(contentText, "text/html; charset=utf-8", "utf-8");
        }
        return root;
    }
}