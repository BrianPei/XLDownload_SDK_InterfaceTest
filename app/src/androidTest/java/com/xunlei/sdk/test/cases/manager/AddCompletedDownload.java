package com.xunlei.sdk.test.cases.manager;

import android.database.Cursor;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * addCompletedDownload 添加本地文件至下载数据库
 */
public class AddCompletedDownload extends BaseCase {

	// 未设置allowWrite
	public void testAddCompletedDownload() {
		printDivideLine();
		String path = "/storage/sdcard0/Download/TEST.apk";
		String title = "test";
		String description = "Test Description";
		String mimeType = "application/vnd.android.package-archive";
		// 调用接口
		long id = downloadManager.addCompletedDownload(title, description,
				true, mimeType, path, 100000L, true);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		assertEquals("Title错误", title,
				cursor.getString(cursor.getColumnIndex("title")));
		assertEquals("Description错误", description,
				cursor.getString(cursor.getColumnIndex("description")));
		assertEquals("文件类型错误", mimeType,
				cursor.getString(cursor.getColumnIndex("mimetype")));
		assertEquals("文件路径错误", path,
				cursor.getString(cursor.getColumnIndex("_data")));
		assertEquals("文件大小错误", 100000L,
				cursor.getLong(cursor.getColumnIndex("total_bytes")));
		assertEquals("下载状态错误", 200,
				cursor.getInt(cursor.getColumnIndex("status")));
		assertEquals("allow_write错误", 0,
				cursor.getInt(cursor.getColumnIndex("allow_write")));
		assertEquals("destination错误", 6,
				cursor.getInt(cursor.getColumnIndex("destination")));
	}

	// 设置allowWrite
	public void testAddCompletedDownloadAllowWrite() {
		printDivideLine();
		String path = "/storage/sdcard0/Download/TEST.apk";
		String title = "test";
		String description = "Test Description";
		String mimeType = "application/vnd.android.package-archive";
		// 调用接口
		long id = downloadManager.addCompletedDownload(title, description,
				true, mimeType, path, 100000L, true, true);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		assertEquals("Title错误", title,
				cursor.getString(cursor.getColumnIndex("title")));
		assertEquals("Description错误", description,
				cursor.getString(cursor.getColumnIndex("description")));
		assertEquals("文件类型错误", mimeType,
				cursor.getString(cursor.getColumnIndex("mimetype")));
		assertEquals("文件路径错误", path,
				cursor.getString(cursor.getColumnIndex("_data")));
		assertEquals("文件大小错误", 100000L,
				cursor.getLong(cursor.getColumnIndex("total_bytes")));
		assertEquals("下载状态错误", 200,
				cursor.getInt(cursor.getColumnIndex("status")));
		assertEquals("allow_write错误", 1,
				cursor.getInt(cursor.getColumnIndex("allow_write")));
		assertEquals("destination错误", 6,
				cursor.getInt(cursor.getColumnIndex("destination")));
	}

}
