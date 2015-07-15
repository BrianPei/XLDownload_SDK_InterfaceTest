package com.xunlei.sdk.test.manager;

import android.content.Context;
import android.util.Log;

import com.xunlei.download.DownloadManager;
import com.xunlei.sdk.utils.BaseCase;

/*
 * getRecommendedMaxBytesOverMobile 获取建议的移动网络下载限制
 */
public class GetRecommendedMaxBytesOverMobile extends BaseCase {

    public void testGetRecommendedMaxBytesOverMobile() {
        printDivideLine();
        Context context = this.getContext();
        // 调用接口
        long bytes = DownloadManager
                .getRecommendedMaxBytesOverMobile(context);
        Log.d("Test_Debug",
                "Recommended MaxBytes OverMobile = " + bytes);
        // 验证结果
        assertEquals("移动网络下载限制异常", 524288L, bytes);
    }

}
