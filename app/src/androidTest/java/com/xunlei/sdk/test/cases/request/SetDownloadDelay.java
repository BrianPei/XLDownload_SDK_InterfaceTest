package com.xunlei.sdk.test.cases.request;

import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * setDownloadDelay 设置是否立即下载
 */
public class SetDownloadDelay extends BaseCase {
	private Request request;

	// 设置为不立即下载
	public void testSetDownloadDelay() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		// 调用接口
		request.setDownloadDelay(true);
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		int status = CaseUtils.selectDownloadStatus(this.getContext(),
				downloadManager, id);
		DebugLog.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("任务状态异常", 193, status);
	}

	// 设置为立即下载
	public void testSetDownloadDelay2() {
		printDivideLine();
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDownloadDelay(false);
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		int status = CaseUtils.selectDownloadStatus(this.getContext(),
				downloadManager, id);
		DebugLog.d("Test_Debug", "Status = " + String.valueOf(status));
		assertEquals("任务状态异常", 190, status);
	}
}
