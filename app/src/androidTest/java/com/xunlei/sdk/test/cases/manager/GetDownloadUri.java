package com.xunlei.sdk.test.cases.manager;

import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * getDownloadUri 获取DownloadProvider的位置
 */
public class GetDownloadUri extends BaseCase {

	// 不传ID
	public void testGetDownloadUri() {
		printDivideLine();
		// 调用接口
		Uri downloadUri = XunLeiDownloadManager.getDownloadUri();
		DebugLog.d("Test_Debug", "DownloadProvider = " + downloadUri.toString());
		// 验证结果
		assertEquals(
				"DownloadProvider位置获取错误",
				Uri.parse("content://com.xunlei.sdk.test.downloadprovider/xl_downloads"),
				downloadUri);
	}

	// 传入ID为1
	public void testGetDownloadUriWithID() {
		printDivideLine();
		// 调用接口
		Uri downloadUri = XunLeiDownloadManager.getDownloadUri(1L);
		DebugLog.d("Test_Debug", "DownloadProvider = " + downloadUri.toString());
		// 验证结果
		assertEquals(
				"DownloadProvider位置获取错误",
				Uri.parse("content://com.xunlei.sdk.test.downloadprovider/xl_downloads/1"),
				downloadUri);
	}

}
