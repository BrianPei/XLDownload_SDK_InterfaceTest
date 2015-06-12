package com.xunlei.sdk;

import com.android.providers.downloads.DownloadProvider;
import com.xunlei.download.XLDownloadProvider;

import android.app.Application;

public class MyApplication extends Application{
	
	@Override
	public void onCreate() {
		super.onCreate();
		DownloadProvider.init(this, XLDownloadProvider.class);
	}
}
