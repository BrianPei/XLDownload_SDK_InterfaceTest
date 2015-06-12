package com.xunlei.sdk.test.utils;

import android.content.Context;
import android.test.AndroidTestCase;

import com.xunlei.download.XunLeiDownloadManager;

public class VerifyUtils extends AndroidTestCase {

	public static void verifyDownloadResult(Context context,
			XunLeiDownloadManager downloadManager, long id) {
		int status = CaseUtils.selectDownloadStatus(context, downloadManager,
				id);
		if (status == 192) {
			int speed = CaseUtils.selectDownloadSpeed(context, downloadManager,
					id);
			assertTrue("下载速度异常", speed > 0);
		} else {
			assertEquals("下载状态异常", 200, status);
		}
	}

}
