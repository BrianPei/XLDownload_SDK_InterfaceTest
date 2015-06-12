package com.xunlei.sdk.test.cases.request;

import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;
import com.xunlei.sdk.test.utils.ReflectUtils;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * setAllowedNetworkTypes 设置允许下载的网络类型
 */
public class SetAllowedNetworkTypes extends BaseCase {
	private Request request;

	// 设置为允许移动网络下载
	public void testSetAllowedNetworkTypes() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 调用接口
		request.setAllowedNetworkTypes(Request.NETWORK_MOBILE);
		// 通过反射验证设置成功
		Object mAllowedNetworkTypes = ReflectUtils.getPrivateAttr(request,
				"mAllowedNetworkTypes");
		assertEquals("设置属性失败", 1, mAllowedNetworkTypes);
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		int networkType = cursor.getInt(cursor
				.getColumnIndex("allowed_network_types"));
		assertEquals("任务属性异常", 1, networkType);
	}

	// 设置为只允许wifi下载
	public void testSetAllowedWifiTypes() {
		printDivideLine();
		// 添加测试Request
		request = new Request(
				Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
		request.setDestinationInExternalPublicDir(DOWNLOADPATH,
				"201412181_uc.apk");
		// 调用接口
		request.setAllowedNetworkTypes(Request.NETWORK_WIFI);
		// 通过反射验证设置成功
		Object mAllowedNetworkTypes = ReflectUtils.getPrivateAttr(request,
				"mAllowedNetworkTypes");
		assertEquals("设置属性失败", 2, mAllowedNetworkTypes);
		// 建立下载任务
		long id = downloadManager.enqueue(request);
		DebugLog.d("Test_Debug", "Task ID = " + String.valueOf(id));
		assertTrue("下载任务建立失败", id > 0);
		// 查询本地数据库验证结果
		Cursor cursor = CaseUtils.selectTask(this.getContext(),
				downloadManager, id);
		int networkType = cursor.getInt(cursor
				.getColumnIndex("allowed_network_types"));
		assertEquals("任务属性异常", 2, networkType);
	}
}
