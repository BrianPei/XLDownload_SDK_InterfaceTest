package com.xunlei.sdk.test.cases.request;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * addRequestHeader 添加HTTP请求头信息
 */
public class AddRequestHeader extends BaseCase {

	public void testAddRequestHeader() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 调用接口
		request.addRequestHeader("TestKey", "TestValue");
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from request_headers where download_id = "
						+ String.valueOf(id), null);
		cursor.moveToNext();
		DebugLog.d("Test_Debug", "Key = " + cursor.getString(2));
		assertEquals("Key错误", "TestKey", cursor.getString(2));
		DebugLog.d("Test_Debug", "Value = " + cursor.getString(3));
		assertEquals("Value错误", "TestValue", cursor.getString(3));
	}

}
