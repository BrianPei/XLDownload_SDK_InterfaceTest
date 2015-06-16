package com.xunlei.sdk.test.request;

import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.ReflectUtils;
import com.xunlei.sdk.utils.log.DebugLog;

/*
 * allowScanningByMediaScanner 允许系统媒体库扫描
 */
public class AllowScanningByMediaScanner extends BaseCase {
	private Request request;

	public void testAllowScanningByMediaScanner() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 调用接口
		request.allowScanningByMediaScanner();
		// 通过反射验证设置成功
		Object mScannable = ReflectUtils.getPrivateAttr(request, "mScannable");
		assertEquals("设置属性失败", true, mScannable);
	}
}
