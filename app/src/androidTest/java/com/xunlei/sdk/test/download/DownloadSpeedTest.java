package com.xunlei.sdk.test.download;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

/*
 * 任务下载速度与完成度测试
 */
public class DownloadSpeedTest extends BaseCase {
    private Request request;

    //下载速度
    public void testDownloadSpeed() {
        printDivideLine();
        // 建立下载任务
        request = new Request(
                Uri.parse("http://apkg.lenovomm.com/201506101350/a13b4d9379998558f8d323c89db5c8ad/dlserver/fileman/s3/apk/app/app-apkg-lestore/7371-2015-06-04051514-1433409314661.apk?v=5&clientid=59099-2-2-19-1-3-1_640_i864784020000638t19700111007115695_c18677d1p490"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test.apk");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 验证下载速度
        Cursor cursor = CaseUtils.selectTask(context,
                downloadManager, id);
        int status = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("Test_Debug", "Status = " + status);
        assertEquals("下载状态异常", 192, status);
        int originSpeed = cursor.getInt(cursor.getColumnIndex("origin_speed"));
        DebugLog.d("Test_Debug", "Origin Speed = " + originSpeed);
        assertTrue("原生下载速度异常", originSpeed > 0);
        int p2sSpeed = cursor.getInt(cursor.getColumnIndex("p2s_speed"));
        DebugLog.d("Test_Debug", "P2S Speed = " + p2sSpeed);
        assertTrue("迅雷加速度异常", p2sSpeed > 0);
        int downloadSpeed = cursor.getInt(cursor.getColumnIndex("download_speed"));
        DebugLog.d("Test_Debug", "Download Speed = " + downloadSpeed);
        assertEquals("总下载速度异常", originSpeed + p2sSpeed, downloadSpeed);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }

    //下载完成度
    public void testDownloadCompleted() {
        printDivideLine();
        //建立一个下载成功的任务
        Context context = this.getContext();
        long id = CaseUtils.insertSuccessfulTask(context, downloadManager);
        //验证下载完成度
        Cursor cursor = CaseUtils.selectTask(context,
                downloadManager, id);
        long totalBytes = cursor.getLong(cursor.getColumnIndex("total_bytes"));
        DebugLog.d("Test_Debug", "Total Bytes = " + totalBytes);
        long currentBytes = cursor.getLong(cursor.getColumnIndex("current_bytes"));
        DebugLog.d("Test_Debug", "Current Bytes = " + currentBytes);
        assertEquals("已下载文件大小与预计大小不符", totalBytes, currentBytes);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }
}
