package com.xunlei.sdk.test.cases.manager;

import android.database.Cursor;

import com.xunlei.sdk.test.utils.BaseCase;
import com.xunlei.sdk.test.utils.CaseUtils;

/*
 * query 查询所有已下载的任务
 */
public class Query extends BaseCase {

	public void testQueryResult() {
		printDivideLine();
		// 调用接口查询一条任务
		query.orderBy("_id", 1);
		Cursor cursor1 = downloadManager.query(query);
		cursor1.moveToFirst();
		// 从本地数据库查询同一条任务
		Cursor cursor2 = CaseUtils.selectTask(this.getContext(),
				downloadManager, 1L);
		// 对比验证结果
		assertEquals("ID错误", cursor2.getLong(cursor2.getColumnIndex("_id")),
				cursor1.getLong(cursor1.getColumnIndex("_id")));
		assertEquals("Local_filename错误",
				cursor2.getString(cursor2.getColumnIndex("_data")),
				cursor1.getString(cursor1.getColumnIndex("local_filename")));
		assertEquals("mediaprovider_uri错误",
				cursor2.getString(cursor2.getColumnIndex("mediaprovider_uri")),
				cursor1.getString(cursor1.getColumnIndex("mediaprovider_uri")));
		assertEquals("Destination错误",
				cursor2.getInt(cursor2.getColumnIndex("destination")),
				cursor1.getInt(cursor1.getColumnIndex("destination")));
		assertEquals("Title错误",
				cursor2.getString(cursor2.getColumnIndex("title")),
				cursor1.getString(cursor1.getColumnIndex("title")));
		assertEquals("Description错误",
				cursor2.getString(cursor2.getColumnIndex("description")),
				cursor1.getString(cursor1.getColumnIndex("description")));
		assertEquals("Uri错误", cursor2.getString(cursor2.getColumnIndex("uri")),
				cursor1.getString(cursor1.getColumnIndex("uri")));
		assertEquals("Hint错误",
				cursor2.getString(cursor2.getColumnIndex("hint")),
				cursor1.getString(cursor1.getColumnIndex("hint")));
		assertEquals("Media_Type错误",
				cursor2.getString(cursor2.getColumnIndex("mimetype")),
				cursor1.getString(cursor1.getColumnIndex("media_type")));
		assertEquals("Total_Size错误",
				cursor2.getLong(cursor2.getColumnIndex("total_bytes")),
				cursor1.getLong(cursor1.getColumnIndex("total_size")));
		assertEquals("Last_Modified_Timestamp错误", cursor2.getLong(cursor2
				.getColumnIndex("lastmod")), cursor1.getLong(cursor1
				.getColumnIndex("last_modified_timestamp")));
		assertEquals("Bytes_So_Far错误",
				cursor2.getLong(cursor2.getColumnIndex("current_bytes")),
				cursor1.getLong(cursor1.getColumnIndex("bytes_so_far")));
		assertEquals("Download_Speed错误",
				cursor2.getLong(cursor2.getColumnIndex("download_speed")),
				cursor1.getLong(cursor1.getColumnIndex("download_speed")));
		assertEquals("Origin_Speed错误",
				cursor2.getLong(cursor2.getColumnIndex("origin_speed")),
				cursor1.getLong(cursor1.getColumnIndex("origin_speed")));
		assertEquals("P2s_Speed错误",
				cursor2.getLong(cursor2.getColumnIndex("p2s_speed")),
				cursor1.getLong(cursor1.getColumnIndex("p2s_speed")));
		assertEquals("ErrorMsg错误",
				cursor2.getString(cursor2.getColumnIndex("errorMsg")),
				cursor1.getString(cursor1.getColumnIndex("errorMsg")));
		assertEquals("Allow_Write错误",
				cursor2.getLong(cursor2.getColumnIndex("allow_write")),
				cursor1.getLong(cursor1.getColumnIndex("allow_write")));
		assertEquals("Status_Original错误",
				cursor2.getInt(cursor2.getColumnIndex("status")),
				cursor1.getInt(cursor1.getColumnIndex("status_original")));
	}

}
