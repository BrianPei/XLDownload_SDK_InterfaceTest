package com.xunlei.sdk.test.download;

import android.content.Context;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

/*
 * 任务下载状态测试
 */
public class DownloadStatusTest extends BaseCase {

    // 未开始下载的任务状态为190
    public void testStatus190() {
        printDivideLine();
        // 建立下载任务
        Request request = new XunLeiDownloadManager.Request(
                Uri.parse("http://apkg.lenovomm.com/201506101350/a13b4d9379998558f8d323c89db5c8ad/dlserver/fileman/s3/apk/app/app-apkg-lestore/7371-2015-06-04051514-1433409314661.apk?v=5&clientid=59099-2-2-19-1-3-1_640_i864784020000638t19700111007115695_c18677d1p490"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test190.apk");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(this.getContext(), downloadManager, id);
        assertEquals("任务状态异常", 190, status);
    }

    // 下载中任务状态为192
    public void testStatus192() {
        printDivideLine();
        // 建立下载任务
        Request request = new XunLeiDownloadManager.Request(
                Uri.parse("http://apkg.lenovomm.com/201506101350/a13b4d9379998558f8d323c89db5c8ad/dlserver/fileman/s3/apk/app/app-apkg-lestore/7371-2015-06-04051514-1433409314661.apk?v=5&clientid=59099-2-2-19-1-3-1_640_i864784020000638t19700111007115695_c18677d1p490"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test192.apk");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(context, downloadManager, id);
        assertEquals("任务状态异常", 192, status);
    }

    // 暂停的任务状态为193
    public void testStatus193() {
        printDivideLine();
        // 建立下载任务
        Request request = new XunLeiDownloadManager.Request(
                Uri.parse("http://apkg.lenovomm.com/201506101350/a13b4d9379998558f8d323c89db5c8ad/dlserver/fileman/s3/apk/app/app-apkg-lestore/7371-2015-06-04051514-1433409314661.apk?v=5&clientid=59099-2-2-19-1-3-1_640_i864784020000638t19700111007115695_c18677d1p490"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "test193.apk");
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(3);
        //暂停任务
        int result = downloadManager.pauseDownload(id);
        assertEquals("暂停失败", 1, result);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(context, downloadManager, id);
        assertEquals("任务状态异常", 193, status);
    }

    // 下载完成的任务状态为200
    public void testStatus200() {
        printDivideLine();
        // 建立一个下载成功任务
        long id = CaseUtils.insertSuccessfulTask(this.getContext(),downloadManager);
        // 验证下载状态
        int status = CaseUtils.selectDownloadStatus(this.getContext(), downloadManager, id);
        assertEquals("任务状态异常", 200, status);
    }

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
