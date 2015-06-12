package com.xunlei.sdk.test.cases.request;

import android.net.Uri;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * setMimeType 设置下载任务文件的类型
 */
public class SetMimeType extends BaseCase {
	private Request request;

	public void testSetMimeType() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		// 调用接口
		request.setMimeType("TestMimeType");
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		String mimeType = CaseUtils.selectMimeType(this.getContext(),
				downloadManager, id);
		DebugLog.d("Test_Debug", "文件类型 = " + mimeType);
		assertEquals("文件类型错误", "TestMimeType", mimeType);
	}
}
