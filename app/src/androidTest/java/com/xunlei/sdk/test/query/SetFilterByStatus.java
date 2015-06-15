package com.xunlei.sdk.test.query;

import android.database.Cursor;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.log.DebugLog;

/*
 * setFilterByStatus 按状态筛选下载任务
 */
public class SetFilterByStatus extends BaseCase {

    // 筛选成功任务
    public void testSetFilterByStatus200() {
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
                DebugLog.d("Test_Debug", "Status = " + status);
                assertEquals("Status筛选错误", 200, status);
                cursor.moveToNext();
            }
        } else {
            DebugLog.d("Test_Debug", "暂无已完成任务，请重试");
        }
    }

    // 筛选下载中任务
    public void testSetFilterByStatus192() {
        printDivideLine();
        // 调用接口
        query.setFilterByStatus(XunLeiDownloadManager.STATUS_RUNNING);
        // 查询数据库验证结果
        Cursor cursor = downloadManager.query(query);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int status = cursor.getInt(cursor
                        .getColumnIndex("status_original"));
                DebugLog.d("Test_Debug", "Status = " + status);
                assertEquals("Status筛选错误", 192, status);
                cursor.moveToNext();
            }
        } else {
            DebugLog.d("Test_Debug", "暂无下载中任务，请重试");
        }
    }

    // 筛选未开始任务
    public void testSetFilterByStatus190() {
        printDivideLine();
        // 调用接口
        query.setFilterByStatus(XunLeiDownloadManager.STATUS_PENDING);
        // 查询数据库验证结果
        Cursor cursor = downloadManager.query(query);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int status = cursor.getInt(cursor
                        .getColumnIndex("status_original"));
                DebugLog.d("Test_Debug", "Status = " + status);
                assertEquals("Status筛选错误", 190, status);
                cursor.moveToNext();
            }
        } else {
            DebugLog.d("Test_Debug", "暂无未开始任务，请重试");
        }
    }

    // 筛选暂停任务
    public void testSetFilterByStatus193() {
        printDivideLine();
        // 调用接口
        query.setFilterByStatus(XunLeiDownloadManager.STATUS_PAUSED);
        // 查询数据库验证结果
        Cursor cursor = downloadManager.query(query);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int status = cursor.getInt(cursor
                        .getColumnIndex("status_original"));
                DebugLog.d("Test_Debug", "Status = " + status);
                assertEquals("Status筛选错误", 193, status);
                cursor.moveToNext();
            }
        } else {
            DebugLog.d("Test_Debug", "暂无暂停中任务，请重试");
        }
    }

    // 筛选失败任务
    public void testSetFilterByStatus400() {
        printDivideLine();
        // 调用接口
        query.setFilterByStatus(XunLeiDownloadManager.STATUS_FAILED);
        // 查询数据库验证结果
        Cursor cursor = downloadManager.query(query);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int status = cursor.getInt(cursor
                        .getColumnIndex("status_original"));
                DebugLog.d("Test_Debug", "Status = " + status);
                assertTrue("Status筛选错误", status > 400);
                cursor.moveToNext();
            }
        } else {
            DebugLog.d("Test_Debug", "暂无失败任务，请重试");
        }
    }
}
