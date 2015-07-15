package com.xunlei.sdk.test.request;

import android.net.Uri;

import com.xunlei.download.DownloadManager.Request;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

/*
 * setDownloadDelay 设置是否立即下载
 */
public class SetDownloadDelay extends BaseCase {
    private Request request;

    // 设置为不立即下载
    public void testSetDownloadDelay() {
        printDivideLine();
        // 添加测试Request
        request = new Request(
                Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
        // 调用接口
        request.setDownloadDelay(true);
        // 建立下载任务
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        // 查询本地数据库验证结果
        int status = CaseUtils.selectDownloadStatus(this.getContext(),
                downloadManager, id);
        DebugLog.d("Test_Debug", "Status = " + status);
        assertEquals("任务状态异常", 193, status);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }

    // 设置为立即下载
    public void testSetDownloadDelay2() {
        printDivideLine();
        // 添加测试Request
        request = new Request(
                Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
        // 调用接口
        request.setDownloadDelay(false);
        // 建立下载任务
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        // 查询本地数据库验证结果
        int status = CaseUtils.selectDownloadStatus(this.getContext(),
                downloadManager, id);
        DebugLog.d("Test_Debug", "Status = " + status);
        assertEquals("任务状态异常", 190, status);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id);
    }
}
