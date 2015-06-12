package com.xunlei.sdk.test.cases.manager;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * getReason 获取下载状态对应的原因码
 */
public class GetReason extends BaseCase {

	/*
	 * 下载暂停的情况
	 */
	public void testGetReason193() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(193);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("暂停原因获取错误", XunLeiDownloadManager.PAUSED_BY_APP, reason);
	}

	public void testGetReason194() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(194);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("暂停原因获取错误", XunLeiDownloadManager.PAUSED_WAITING_TO_RETRY,
				reason);
	}

	public void testGetReason195() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(195);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("暂停原因获取错误",
				XunLeiDownloadManager.PAUSED_WAITING_FOR_NETWORK, reason);
	}

	public void testGetReason196() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(196);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("暂停原因获取错误", XunLeiDownloadManager.PAUSED_QUEUED_FOR_WIFI,
				reason);
	}

	/*
	 * 下载异常的情况
	 */
	public void testGetReason198() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(198);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误",
				XunLeiDownloadManager.ERROR_INSUFFICIENT_SPACE, reason);
	}

	public void testGetReason199() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(199);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_DEVICE_NOT_FOUND,
				reason);
	}

	public void testGetReason400() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(400);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_CLIENT_ERROR,
				reason);
	}

	public void testGetReason404() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(403);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_CLIENT_ERROR,
				reason);
	}

	public void testGetReason406() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(406);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_CLIENT_ERROR,
				reason);
	}

	public void testGetReason411() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(411);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_CLIENT_ERROR,
				reason);
	}

	public void testGetReason412() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(412);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_CLIENT_ERROR,
				reason);
	}

	public void testGetReason488() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(488);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误",
				XunLeiDownloadManager.ERROR_FILE_ALREADY_EXISTS, reason);
	}

	public void testGetReason489() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(489);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_CANNOT_RESUME,
				reason);
	}

	public void testGetReason490() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(490);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_UNKNOWN, reason);
	}

	public void testGetReason491() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(491);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_UNKNOWN, reason);
	}

	public void testGetReason492() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(492);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_FILE_ERROR, reason);
	}

	public void testGetReason493() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(493);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误",
				XunLeiDownloadManager.ERROR_UNHANDLED_HTTP_CODE, reason);
	}

	public void testGetReason494() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(494);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误",
				XunLeiDownloadManager.ERROR_UNHANDLED_HTTP_CODE, reason);
	}

	public void testGetReason495() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(495);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_DATA_ERROR,
				reason);
	}

	public void testGetReason496() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(496);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_UNKNOWN, reason);
	}

	public void testGetReason497() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(497);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误",
				XunLeiDownloadManager.ERROR_TOO_MANY_REDIRECTS, reason);
	}

	public void testGetReason499() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(499);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_UNKNOWN, reason);
	}

	public void testGetReason500() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(500);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_SERVER_ERROR,
				reason);
	}

	public void testGetReason503() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(503);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_HTTP_SERVER_ERROR,
				reason);
	}

	public void testGetReason600() {
		printDivideLine();
		long reason = XunLeiDownloadManager.getReason(600);
		DebugLog.d("Test_Debug", "Reason = " + String.valueOf(reason));
		assertEquals("失败原因获取错误", XunLeiDownloadManager.ERROR_UNKNOWN, reason);
	}
}
