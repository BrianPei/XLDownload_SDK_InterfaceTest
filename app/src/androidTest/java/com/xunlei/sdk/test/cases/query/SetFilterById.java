package com.xunlei.sdk.test.cases.query;

import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.log.DebugLog;

import android.database.Cursor;

/*
 * setFilterById 按ID筛选下载任务
 */
public class SetFilterById extends BaseCase {

	// 筛选单个ID
	public void testSetFilterById() {
		printDivideLine();
		// 调用接口
		query.setFilterById(1L);
		// 查询数据库验证结果
		query.orderBy("_id", 1);
		Cursor cursor = downloadManager.query(query);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			long id = cursor.getInt(cursor.getColumnIndex("_id"));
			DebugLog.d("Test_Debug", "ID = " + String.valueOf(id));
			assertEquals("ID筛选错误", 1L, id);
		}
	}

	// 筛选多个ID为1、2、3
	public void testSetFilterById2() {
		printDivideLine();
		// 调用接口
		query.setFilterById(1L, 2L, 3L);
		// 查询数据库验证结果
		query.orderBy("_id", 1);
		Cursor cursor = downloadManager.query(query);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 1; i <= cursor.getCount(); i++) {
				long id = cursor.getInt(cursor.getColumnIndex("_id"));
				DebugLog.d("Test_Debug", "ID = " + String.valueOf(id));
				assertEquals("ID筛选错误", (long) i, id);
				cursor.moveToNext();
			}
		}

	}
}
