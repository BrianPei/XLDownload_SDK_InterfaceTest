package com.xunlei.sdk.test.request;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

/**
 * Created by Brian on 15/6/15.
 */
public class SetDownloadSpdy extends BaseCase {
    private Request request;

    public void testSetDownloadSpdy() {
        printDivideLine();
        // 添加任务Request
        request = new Request(
                Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
        // 调用接口
        request.setDownloadSpdy(true);
        // 建立下载任务
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 查询本地数据库验证结果
        Cursor cursor = CaseUtils.selectTask(context,
                downloadManager, id);
        int status = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("Test_Debug", "Status = " + status);
        int p2sSpeed = cursor.getInt(cursor.getColumnIndex("p2s_speed"));
        DebugLog.d("Test_Debug", "P2S Speed = " + p2sSpeed);
        assertTrue("加速下载未开启", p2sSpeed > 0);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }

    public void testSetNotSpdy() {
        printDivideLine();
        // 添加任务Request
        request = new Request(
                Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
        // 调用接口
        request.setDownloadSpdy(false);
        // 建立下载任务
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 查询本地数据库验证结果
        Cursor cursor = CaseUtils.selectTask(context,
                downloadManager, id);
        int p2sSpeed = cursor.getInt(cursor.getColumnIndex("p2s_speed"));
        DebugLog.d("Test_Debug", "P2S Speed = " + p2sSpeed);
        assertEquals("加速下载未开启", 0, p2sSpeed);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }
}
