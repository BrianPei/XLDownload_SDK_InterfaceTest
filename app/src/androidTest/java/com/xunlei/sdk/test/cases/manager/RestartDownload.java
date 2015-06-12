package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * restartDownload 重启下载任务
 */
public class RestartDownload extends BaseCase {

	// 重启一个已下载成功的任务
	public void testRestartSuccessfulDownload() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			// 获取其中一条任务
			cursor.moveToLast();
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
			// 调用接口重启任务
			int result = downloadManager.restartDownload(id);
			assertEquals("重启任务失败", 1, result);
			Context context = this.getContext();
			CaseUtils.startActivity(context);
			sleep(3);
			// 查询数据库验证下载状态
			int status = CaseUtils.selectDownloadStatus(context,
					downloadManager, id);
			assertEquals("下载状态异常", 192, status);
			// 查询数据库验证下载速度
			int speed = CaseUtils.selectDownloadSpeed(context, downloadManager,
					id);
			assertTrue("下载速度异常", speed > 0);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 重启一个下载失败的任务
	public void testRestartFailedDownload() {
		printDivideLine();
		Context context = this.getContext();
		// 建立一个下载失败的任务
		long id = CaseUtils.insertFailedTask(context, downloadManager);
		// 调用接口重启任务
		int result = downloadManager.restartDownload(id);
		assertEquals("重启任务失败", 1, result);
		// 查询数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 190, status);
	}

	// 重启下载中任务
	public void testRestartRunningDownload() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口
		int result = downloadManager.restartDownload(id);
		assertEquals("重启任务失败", 0, result);
		sleep(1);
		// 查询数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 192, status);
	}

	// 重启未开始下载的任务
	public void testRestartPendingDownload() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口
		int result = downloadManager.restartDownload(id);
		assertEquals("重启任务失败", 0, result);
		sleep(1);
		// 查询数据库验证下载状态(此时会开始下载)
		int status = CaseUtils.selectDownloadStatus(this.getContext(),
				downloadManager, id);
		assertEquals("下载状态异常", 192, status);
	}

	// 重启暂停的任务
	public void testRestartPausedDownload() {
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
		int result = downloadManager.restartDownload(id);
		assertEquals("重启任务失败", 0, result);
		sleep(1);
		// 查询数据库验证暂停状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 193, status);
	}

	// 重启多个任务
	public void testRestartMultiDownload() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			// 获取其中2条任务
			cursor.moveToFirst();
			long id1 = cursor.getLong(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id1));
			cursor.moveToNext();
			long id2 = cursor.getLong(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id2));
			// 调用接口重启任务
			int result = downloadManager.restartDownload(id1, id2);
			assertEquals("重启任务失败", 2, result);
			Context context = this.getContext();
			CaseUtils.startActivity(context);
			sleep(3);
			// 查询数据库验证下载状态
			int status1 = CaseUtils.selectDownloadStatus(context,
					downloadManager, id1);
			assertEquals("下载状态异常", 192, status1);
			int status2 = CaseUtils.selectDownloadStatus(context,
					downloadManager, id2);
			assertEquals("下载状态异常", 192, status2);
			// 查询数据库验证下载速度
			int speed1 = CaseUtils.selectDownloadSpeed(context,
					downloadManager, id1);
			assertTrue("下载速度异常", speed1 > 0);
			int speed2 = CaseUtils.selectDownloadSpeed(context,
					downloadManager, id2);
			assertTrue("下载速度异常", speed2 > 0);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}
}
