package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * getUriForDownloadedFile 获取下载任务的文件位置
 */
public class GetUriForDownloadedFile extends BaseCase {

	// 已下载完成的文件位置
	public void testGetUriForDownloadedFile() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			// 查询数据库获取文件位置
			cursor.moveToFirst();
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
			String localUri = cursor.getString(cursor.getColumnIndex("_data"));
			// 调用接口获取文件位置
			Uri fileUri = downloadManager.getUriForDownloadedFile(id);
			DebugLog.d("Test_Debug", "本地Uri = " + fileUri.toString());
			// 对比验证结果是否一致
			assertEquals("本地Uri错误", Uri.parse(localUri), fileUri);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 下载中的任务文件类型
	public void testGetUriForRunningFile() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 查询本地数据库获取该任务的文件类型
		Cursor cursor = CaseUtils.selectTask(context, downloadManager, id);
		cursor.moveToFirst();
		// 调用接口获取文件类型
		String localUri = cursor.getString(cursor.getColumnIndex("_data"));
		Uri fileUri = downloadManager.getUriForDownloadedFile(id);
		DebugLog.d("Test_Debug", "本地Uri = " + fileUri.toString());
		// 对比验证结果是否一致
		assertEquals("本地Uri错误", Uri.parse(localUri), fileUri);
	}

	// 未下载的任务文件类型应为null
	public void testGetUriForPendingFile() {
		printDivideLine();
		// 建立一个任务但不开始下载
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口获取文件类型
		Uri fileUri = downloadManager.getUriForDownloadedFile(id);
		DebugLog.d("Test_Debug", "本地Uri = " + fileUri.toString());
		// 验证结果应为null
		assertNull("本地Uri错误", fileUri);
	}

}
