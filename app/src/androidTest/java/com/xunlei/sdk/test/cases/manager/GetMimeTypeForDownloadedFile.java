package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * getMimeTypeForDownloadFile 获取下载文件的mimeType
 */
public class GetMimeTypeForDownloadedFile extends BaseCase {

	// 已下载的文件类型
	public void testGetMimeTypeForDownloadedFile() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			// 查询数据库获取文件类型
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
			String mimeType = cursor.getString(cursor
					.getColumnIndex("media_type"));
			// 调用接口获取文件类型
			String fileType = downloadManager.getMimeTypeForDownloadedFile(id);
			DebugLog.d("Test_Debug", "文件MimeType = " + fileType);
			// 对比验证结果是否一致
			assertEquals("mimeType错误", mimeType, fileType);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 下载中的任务文件类型
	public void testGetMimeTypeForRunningFile() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 查询本地数据库获取该任务的文件类型
		String mimeType = CaseUtils
				.selectMimeType(context, downloadManager, id);
		// 调用接口获取文件类型
		String fileType = downloadManager.getMimeTypeForDownloadedFile(id);
		DebugLog.d("Test_Debug", "文件MimeType = " + fileType);
		// 对比验证结果是否一致
		assertEquals("mimeType错误", mimeType, fileType);
	}

	// 未下载的任务文件类型应为null
	public void testGetMimeTypeForPendingFile() {
		printDivideLine();
		// 建立一个任务但不开始下载
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口获取文件类型
		String fileType = downloadManager.getMimeTypeForDownloadedFile(id);
		DebugLog.d("Test_Debug", "文件MimeType = " + fileType);
		// 验证结果应为null
		assertNull("mimeType错误", fileType);
	}
}
