package com.xunlei.sdk.test.cases.manager;

import android.util.Log;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.test.utils.BaseCase;

/*
 * translateStatus 将下载任务状态码转换为DownloadManager的状态标识
 */
public class TranslateStatus extends BaseCase {

	// PENDING状态
	public void testTranslateStatus190() {
		printDivideLine();
		int status = XunLeiDownloadManager.translateStatus(190);
		Log.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("状态码转换错误", XunLeiDownloadManager.STATUS_PENDING, status);
	}

	// RUNNING状态
	public void testTranslateStatus192() {
		printDivideLine();
		int status = XunLeiDownloadManager.translateStatus(192);
		Log.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("状态码转换错误", XunLeiDownloadManager.STATUS_RUNNING, status);
	}

	// PAUSED状态
	public void testTranslateStatus193() {
		printDivideLine();
		int status = XunLeiDownloadManager.translateStatus(193);
		Log.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("状态码转换错误", XunLeiDownloadManager.STATUS_PAUSED, status);
	}

	// SUCCESSFUL状态
	public void testTranslateStatus200() {
		printDivideLine();
		int status = XunLeiDownloadManager.translateStatus(200);
		Log.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("状态码转换错误", XunLeiDownloadManager.STATUS_SUCCESSFUL, status);
	}

	// FAILED状态
	public void testTranslateStatus489() {
		printDivideLine();
		int status = XunLeiDownloadManager.translateStatus(403);
		Log.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("状态码转换错误", XunLeiDownloadManager.STATUS_FAILED, status);
	}

}
