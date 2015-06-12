package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * resumeDownload 下载任务续传
 */
public class ResumeDownload extends BaseCase {

	// 续传一个延迟的下载任务
	public void testResumeDelayDownload() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		// 设置任务为延迟下载
		request.setDownloadDelay(true);
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id);
		assertEquals("续传任务失败", 1, result);
		sleep(3);
		// 查询本地数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 192, status);
		// 查询本地数据库验证下载速度
		int speed = CaseUtils.selectDownloadSpeed(context, downloadManager, id);
		assertTrue("下载速度异常", speed > 0);
	}

	// 续传一个暂停的任务
	public void testResumePausedDownload() {
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
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id);
		assertEquals("续传任务失败", 1, result);
		sleep(3);
		// 查询本地数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 192, status);
		// 查询本地数据库验证下载速度
		int speed = CaseUtils.selectDownloadSpeed(context, downloadManager, id);
		assertTrue("下载速度异常", speed > 0);
	}

	// 续传下载中任务
	public void testResumeRunningDownload() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id);
		assertEquals("续传任务失败", 0, result);
		sleep(1);
		// 查询数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 192, status);
	}

	// 续传未开始下载的任务
	public void testResumePendingDownload() {
		printDivideLine();
		// 建立一个下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id);
		assertEquals("续传任务失败", 0, result);
		sleep(1);
		// 查询数据库验证下载状态(此时会开始下载)
		int status = CaseUtils.selectDownloadStatus(this.getContext(),
				downloadManager, id);
		assertEquals("下载状态异常", 192, status);
	}

	// 续传已下载成功的任务
	public void testResumeSuccessfulDownload() {
		printDivideLine();
		// 查询本地数据库获取已下载完成的任务
		Cursor cursor = CaseUtils.selectTaskByStatus(this.getContext(),
				downloadManager, 200);
		if (cursor.getCount() > 0) {
			// 获取其中一条任务
			cursor.moveToLast();
			long id = cursor.getLong(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
			// 调用接口续传任务
			int result = downloadManager.resumeDownload(id);
			assertEquals("续传任务失败", 0, result);
			sleep(1);
			// 查询数据库验证下载状态(此时会开始下载)
			int status = CaseUtils.selectDownloadStatus(this.getContext(),
					downloadManager, id);
			assertEquals("下载状态异常", 200, status);
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

	// 续传下载失败的任务
	public void testResumeFailedDownload() {
		printDivideLine();
		Context context = this.getContext();
		// 建立一个下载失败的任务
		long id = CaseUtils.insertFailedTask(context, downloadManager);
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id);
		assertEquals("续传任务失败", 0, result);
		sleep(1);
		// 查询数据库验证下载状态
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		assertEquals("下载状态异常", 403, status);
	}

	// 续传多个任务
	public void testResumeMultiDownload() {
		printDivideLine();
		// 添加测试Request
		Request request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		// 设置任务为延迟下载
		request.setDownloadDelay(true);
		// 建立下载任务
		long id1 = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id1));
		assertTrue("下载任务建立失败", id1 > 0);
		long id2 = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id2));
		assertTrue("下载任务建立失败", id2 > 0);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口续传任务
		int result = downloadManager.resumeDownload(id1, id2);
		assertEquals("续传任务失败", 2, result);
		sleep(5);
		// 查询本地数据库验证下载状态
		int status1 = CaseUtils.selectDownloadStatus(context, downloadManager,
				id1);
		assertEquals("下载状态异常", 192, status1);
		int status2 = CaseUtils.selectDownloadStatus(context, downloadManager,
				id2);
		assertEquals("下载状态异常", 192, status2);
		// 查询本地数据库验证下载速度
		int speed1 = CaseUtils.selectDownloadSpeed(context, downloadManager,
				id1);
		assertTrue("下载速度异常", speed1 > 0);
		int speed2 = CaseUtils.selectDownloadSpeed(context, downloadManager,
				id2);
		assertTrue("下载速度异常", speed2 > 0);
	}
}
