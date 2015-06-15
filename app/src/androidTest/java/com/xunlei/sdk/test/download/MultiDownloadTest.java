package com.xunlei.sdk.test.download;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.XunLeiDownloadManager;
import com.xunlei.download.XunLeiDownloadManager.Request;
import com.xunlei.download.XunLeiDownloadManager.Query;
import com.xunlei.sdk.utils.BaseCase;
import com.xunlei.sdk.utils.CaseUtils;
import com.xunlei.sdk.utils.log.DebugLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MultiDownloadTest extends BaseCase {

    /*
     * 超过10个下载任务同时进行（设置最大下载数量为10）
     */
    public void testOverMaxConcurrent() {
        printDivideLine();
        // 添加测试Request
        Request request = new Request(
                Uri.parse("http://cache.iruan.cn/201412/201412181_uc.apk"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH,
                "201412181_uc.apk");
        // 循环建立超过10个下载任务
        List<Long> idList = new ArrayList<Long>();
        for (int i = 0; i < 11; i++) {
            long id = downloadManager.enqueue(request);
            DebugLog.d("Test_Debug", "Task ID = " + id);
            assertTrue(id > 0);
            idList.add(id);
        }
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 查询本地数据库验证所有下载中任务应最多10个
        Cursor cursor = CaseUtils.selectTaskByStatus(context,downloadManager,192);
        int downloadingCount = cursor.getCount();
        DebugLog.d("Test_Debug", "同时下载任务数 = " + downloadingCount);
        assertTrue("同时下载数异常", downloadingCount <= 10);
        //删除下载任务，清理测试环境
        Iterator<Long> it = idList.iterator();
        while (it.hasNext()) {
            long id = it.next();
            CaseUtils.deleteTasks(downloadManager, id);
        }
    }

    /*
     * 相同任务重复下载时名称区分
     */
    public void testRepeatSameTask() {
        printDivideLine();
        // 添加测试Request
        Request request = new Request(
                Uri.parse("http://bigota.miwifi.com/xiaoqiang/client/xqapp.apk"));
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, "xqapp.apk");
        // 建立2个相同下载任务
        long id1 = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id1);
        assertTrue(id1 > 0);
        long id2 = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id2);
        assertTrue(id2 > 0);
        Context context = this.getContext();
        CaseUtils.startActivity(context);
        sleep(5);
        // 查询本地数据库验证2个下载任务的Title不相同
        Cursor cursor1 = CaseUtils.selectTask(context, downloadManager, id1);
        String title1 = cursor1.getString(cursor1.getColumnIndex("title"));
        DebugLog.d("Test_Debug", "File Title = " + title1);
        Cursor cursor2 = CaseUtils.selectTask(context, downloadManager, id2);
        String title2 = cursor2.getString(cursor1.getColumnIndex("title"));
        DebugLog.d("Test_Debug", "File Title = " + title2);
        assertTrue("重复任务名称相同", title1 != title2);
        //删除下载任务，清理测试环境
        CaseUtils.deleteTasks(downloadManager, id1, id2);
    }
}
