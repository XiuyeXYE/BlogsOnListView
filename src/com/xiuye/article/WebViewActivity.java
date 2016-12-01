package com.xiuye.article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_activity);

		String articleUrl = this.getAricleUrl();
		this.webViewShowPage(articleUrl);
	}

	private String getAricleUrl() {
		Intent intent = this.getIntent();
		Bundle bundle = intent.getBundleExtra("urlBundle");
		String value = bundle.getString("url");
		return value;
	}

	private void webViewShowPage(String url) {
		WebView webView = (WebView) this.findViewById(R.id.html);

		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		webView.setBackgroundColor(this.getResources().getColor(R.color.black));
		webView.loadUrl("file:///android_asset/" + url);
	}
}
