package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;

import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * markRowDeleted 标记指定下载任务为已删除
 */
public class MarkRowDeleted extends BaseCase {

	// 标记下载中任务
	public void testMarkRunningDeleted() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口
		int result = downloadManager.markRowDeleted(id);
		assertEquals("标记删除失败", 1, result);
		sleep(3);
		// 查询本地数据库验证任务已被删除
		Cursor cursor = CaseUtils.selectTask(context, downloadManager, id);
		assertEquals("任务仍存在于数据库", 0, cursor.getCount());
	}

	// 标记未开始下载的任务
	public void testMarkPendingDeleted() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口
		int result = downloadManager.markRowDeleted(id);
		assertEquals("标记删除失败", 1, result);
		sleep(3);
		// 查询本地数据库验证任务已被删除
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		assertEquals("任务仍存在于数据库", 0, cursor.getCount());
	}

	// 标记暂停的任务
	public void testMarkPausedDeleted() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 暂停该任务
		int pauseResult = downloadManager.pauseDownload(id);
		assertEquals("暂停失败", 1, pauseResult);
		sleep(1);
		// 调用接口
		int result = downloadManager.markRowDeleted(id);
		assertEquals("标记删除失败", 1, result);
		sleep(3);
		// 查询本地数据库验证任务已被删除
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		assertEquals("任务仍存在于数据库", 0, cursor.getCount());
	}

	// 标记下载成功的任务
	public void testMarkSuccessfulDeleted() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			// 获取其中一条任务
			cursor.moveToLast();
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
			// 调用接口
			int result = downloadManager.markRowDeleted(id);
			assertEquals("标记删除失败", 1, result);
			sleep(3);
			// 查询本地数据库验证任务已被删除
			Cursor cursor2 = CaseUtils.selectTask(this.getContext(),
					downloadManager, id);
			assertEquals("任务仍存在于数据库", 0, cursor2.getCount());
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 标记下载失败的任务
	public void testMarkFailedDeleted() {
		printDivideLine();
		Context context = this.getContext();
		// 建立一个下载失败的任务
		long id = CaseUtils.insertFailedTask(context, downloadManager);
		// 调用接口
		int result = downloadManager.markRowDeleted(id);
		assertEquals("标记删除失败", 1, result);
		sleep(3);
		// 查询本地数据库验证任务已被删除
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		assertEquals("任务仍存在于数据库", 0, cursor.getCount());
	}

	// 标记多个任务
	public void testMarkMultiDeleted() {
		printDivideLine();
		// 建立2个下载任务
		long id1 = CaseUtils.insertDownloadTask(downloadManager);
		long id2 = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口
		int result = downloadManager.markRowDeleted(id1, id2);
		assertEquals("标记删除失败", 2, result);
		sleep(3);
		// 查询本地数据库验证任务已被删除
		Cursor cursor1 = CaseUtils.selectTask(this.getContext(),
				downloadManager, id1);
		assertEquals("任务仍存在于数据库", 0, cursor1.getCount());
		Cursor cursor2 = CaseUtils.selectTask(this.getContext(),
				downloadManager, id2);
		assertEquals("任务仍存在于数据库", 0, cursor2.getCount());
	}

}
