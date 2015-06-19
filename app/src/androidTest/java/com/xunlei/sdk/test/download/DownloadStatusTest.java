package com.xunlei.sdk.test.download;

import android.content.Context;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

public class DownloadStatusTest extends BaseCase {

    // 异常状态为403的链接
    public void testStatus403() {
        printDivideLine();
        // 建立下载任务
        Request request = new XunLeiDownloadManager.Request(
                Uri.parse("http://video.study.163.com/edu-video/nos/mp4/2012/12/14/171189_sd.mp4"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test403.jpg");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(context, downloadManager, id);
        assertEquals("任务状态异常", 403, status);
    }

    // 异常状态为404的链接
    public void testStatus404() {
        printDivideLine();
        // 建立下载任务
        Request request = new XunLeiDownloadManager.Request(
                Uri.parse("http://simg3.gelbooru.com/images/b2/48/b24896de29cdc2a25cefa256e9b376ef.png"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test404.jpg");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(context, downloadManager, id);
        assertEquals("任务状态异常", 404, status);
    }
}
