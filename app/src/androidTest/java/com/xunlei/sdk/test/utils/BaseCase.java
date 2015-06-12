package com.xunlei.sdk.test.utils;

import android.content.Context;
import android.test.AndroidTestCase;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.download.XunLeiDownloadManager.Query;
import com.xunlei.sdk.test.utils.log.DebugLog;

public class BaseCase extends AndroidTestCase {
	protected XunLeiDownloadManager downloadManager;
	protected Query query;
	protected DatabaseHelper dbHelper;

	public static String DOWNLOADPATH = "Download/sdk_test";

	public BaseCase() {
	}

	public void setUp() {
		Context context = this.getContext();
		downloadManager = new XunLeiDownloadManager(context);
		query = new Query();
		dbHelper = new DatabaseHelper(context);
	}

	public void tearDown() {
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void printDivideLine() {
		String caseName = Thread.currentThread().getStackTrace()[3]
				.getMethodName();
		DebugLog.d("Test_Debug", "*******" + caseName + " Start!*******");
	}
}
