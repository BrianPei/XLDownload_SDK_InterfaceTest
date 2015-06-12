package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * getMaxBytesOverMobile 获取移动网络下载限制
 */
public class GetMaxBytesOverMobile extends BaseCase {

	public void testGetMaxBytesOverMobile() {
		printDivideLine();
		Context context = this.getContext();
		// 调用接口
		long bytes = XunLeiDownloadManager.getMaxBytesOverMobile(context);
		DebugLog.d("Test_Debug",
				"MaxBytes OverMobile = " + String.valueOf(bytes));
		// 验证结果
		assertEquals("移动网络下载限制异常", XunLeiDownloadManager.MAX_BYTES_OVER_MOBILE,
				bytes);
	}

}
