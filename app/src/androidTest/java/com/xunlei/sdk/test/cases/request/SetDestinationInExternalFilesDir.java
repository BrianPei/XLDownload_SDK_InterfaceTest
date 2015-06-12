package com.xunlei.sdk.test.cases.request;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * setDestinationInExternalFilesDir 设置下载到内置存储的路径
 */
public class SetDestinationInExternalFilesDir extends BaseCase {
	private Request request;

	public void testSetDestinationInExternalFilesDir() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		// 调用接口
		Context context = this.getContext();
		request.setDestinationInExternalFilesDir(context, "DownloadTest",
				"201412181_uc.apk");
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		String hint = cursor.getString(cursor.getColumnIndex("hint"));
		DebugLog.d("Test_Debug", "存储路径 = " + hint);
		assertEquals(
				"存储路径错误",
				"file:///storage/emulated/0/Android/data/com.xunlei.sdk.test/files/DownloadTest/201412181_uc.apk",
				hint);
	}
}
