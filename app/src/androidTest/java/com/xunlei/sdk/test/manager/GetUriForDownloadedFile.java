package com.xunlei.sdk.test.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

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
			String localUri = cursor.getString(cursor.getColumnIndex("hint"));
			DebugLog.d("Test_Debug", "本地Uri = " + localUri);
			// 调用接口获取文件位置
			Uri fileUri = downloadManager.getUriForDownloadedFile(id);
			DebugLog.d("Test_Debug", "接口返回Uri = " + fileUri);
			// 对比验证结果是否一致
			String localString = localUri.split("[.]")[0];
			String fileString = (fileUri.toString()).split("[-]")[0];
			assertEquals("本地Uri错误", localString, fileString);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 下载中的任务文件位置
	public void testGetUriForRunningFile() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(5);
		// 调用接口获取文件位置
		Uri fileUri = downloadManager.getUriForDownloadedFile(id);
		DebugLog.d("Test_Debug", "接口返回Uri = " + fileUri);
		// 验证结果应为null
		assertNull("本地Uri错误", fileUri);
		//删除下载任务，清理测试环境
		CaseUtils.deleteTasks(downloadManager, id);
	}

	// 未下载的任务文件位置应为null
	public void testGetUriForPendingFile() {
		printDivideLine();
		// 建立一个任务但不开始下载
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口获取文件位置
		Uri fileUri = downloadManager.getUriForDownloadedFile(id);
		DebugLog.d("Test_Debug", "接口返回Uri = " + fileUri);
		// 验证结果应为null
		assertNull("本地Uri错误", fileUri);
		//删除下载任务，清理测试环境
		CaseUtils.deleteTasks(downloadManager, id);
	}

}
