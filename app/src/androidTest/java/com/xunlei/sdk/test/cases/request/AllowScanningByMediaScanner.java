package com.xunlei.sdk.test.cases.request;

import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.ReflectUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

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
		// 建立测试任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		int scan = cursor.getInt(cursor.getColumnIndex("scanned"));
		assertEquals("任务属性异常", 1, scan);
	}
}
