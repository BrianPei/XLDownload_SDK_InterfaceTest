package com.xunlei.sdk.test.cases.query;

import android.database.Cursor;
import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

/*
 * setFilterByStatus 按状态筛选下载任务
 */
public class SetFilterByStatus extends BaseCase {

	// 筛选成功任务
	public void testSetFilterByStatus() {
		printDivideLine();
		// 调用接口
		query.setFilterByStatus(XunLeiDownloadManager.STATUS_SUCCESSFUL);
		// 查询数据库验证结果
		Cursor cursor = downloadManager.query(query);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				int status = cursor.getInt(cursor
						.getColumnIndex("status_original"));
				DebugLog.d("Test_Debug", "Status = " + String.valueOf(status));
				assertEquals("Status筛选错误", 200, status);
				cursor.moveToNext();
			}
		} else {
			DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
		}
	}

}
