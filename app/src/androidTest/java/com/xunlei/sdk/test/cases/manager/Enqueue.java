package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.VerifyUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * enqueue 添加下载任务
 */
public class Enqueue extends BaseCase {

	// 以默认设置添加一个下载任务
	public void testEnqueue() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 调用接口建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 验证下载速度
		VerifyUtils.verifyDownloadResult(context, downloadManager, id);
		// 验证任务字段默认值
		Cursor cursor = CaseUtils.selectTask(context, downloadManager, id);
		assertEquals("Uri错误", "http://cache.iruan.cn/201412/201412181_uc.apk",
				cursor.getString(cursor.getColumnIndex("uri")));
		assertEquals(
				"Hint错误",
				"file:///storage/emulated/0/Download/sdk_test/201412181_uc.apk",
				cursor.getString(cursor.getColumnIndex("hint")));
		assertEquals("Destination错误", 4,
				cursor.getInt(cursor.getColumnIndex("destination")));
		assertEquals("Visibility错误", 0,
				cursor.getInt(cursor.getColumnIndex("visibility")));
		assertEquals("PublicApi错误", 1,
				cursor.getInt(cursor.getColumnIndex("is_public_api")));
		assertEquals("AllowRoaming错误", 1,
				cursor.getInt(cursor.getColumnIndex("allow_roaming")));
		assertEquals("NetworkTypes错误", -1,
				cursor.getInt(cursor.getColumnIndex("allowed_network_types")));
		assertEquals("VisibleInUi错误", 1, cursor.getInt(cursor
				.getColumnIndex("is_visible_in_downloads_ui")));
		assertEquals("RecommandedSize错误", 0, cursor.getInt(cursor
				.getColumnIndex("bypass_recommended_size_limit")));
		assertEquals("Deleted错误", 0,
				cursor.getInt(cursor.getColumnIndex("deleted")));
		assertEquals("AllowMetered错误", 1,
				cursor.getInt(cursor.getColumnIndex("allow_metered")));
		assertEquals("AllowWrite错误", 0,
				cursor.getInt(cursor.getColumnIndex("allow_write")));
		assertEquals("XunleiSpdy错误", 1,
				cursor.getInt(cursor.getColumnIndex("xunlei_spdy")));
	}
}
