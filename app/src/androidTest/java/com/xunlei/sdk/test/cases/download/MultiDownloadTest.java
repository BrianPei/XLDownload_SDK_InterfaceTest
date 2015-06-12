package com.xunlei.sdk.test.cases.download;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

public class MultiDownloadTest extends BaseCase {

	/*
	 * 11个下载任务同时进行（设置最大下载数量为10）
	 */
	public void testOverMaxConcurrent() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 循环建立11个下载任务
		for (int i = 0; i < 11; i++) {
			long id = downloadManager.enqueue(request);
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
			assertTrue(id > 0);
		}
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 查询本地数据库验证最后一个任务应未下载
		assertEquals("同时下载数异常", 190,
				CaseUtils.selectNewTask(context, downloadManager));
	}

	/*
	 * 相同任务重复下载时名称区分
	 */
	public void testRepeatSameTask() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://bigota.miwifi.com/xiaoqiang/client/xqapp.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH, "xqapp.apk");
		// 建立2个相同下载任务
		long id1 = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id1));
		assertTrue(id1 > 0);
		long id2 = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id2));
		assertTrue(id2 > 0);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 查询本地数据库验证2个下载任务的Title不相同
		Cursor cursor1 = CaseUtils.selectTask(context, downloadManager, id1);
		String title1 = cursor1.getString(cursor1.getColumnIndex("title"));
		DebugLog.d("Test_Debug", "File Title = " + title1);
		Cursor cursor2 = CaseUtils.selectTask(context, downloadManager, id2);
		String title2 = cursor2.getString(cursor1.getColumnIndex("title"));
		DebugLog.d("Test_Debug", "File Title = " + title2);
		assertTrue("重复任务名称相同", title1 != title2);
	}
}
