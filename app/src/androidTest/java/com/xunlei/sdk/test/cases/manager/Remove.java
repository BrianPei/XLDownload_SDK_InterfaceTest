package com.xunlei.sdk.test.cases.manager;

import android.content.Context;
import android.database.Cursor;

import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;

/*
 * remove 删除下载任务
 */
public class Remove extends BaseCase {

	// 删除正在下载的任务
	public void testRemoveRunning() {
		printDivideLine();
		// 建立下载任务
		long id = CaseUtils.insertDownloadTask(downloadManager);
		Context context = this.getContext();
		CaseUtils.startActivity(context);
		sleep(3);
		// 调用接口删除任务
		int result = downloadManager.remove(id);
		assertEquals("删除任务失败", 1, result);
		sleep(1);
		// 查询本地数据库验证任务已被删除
		Cursor cursor = CaseUtils.selectTask(context, downloadManager, id);
		assertEquals("任务仍存在于数据库", 0, cursor.getCount());
	}

}
