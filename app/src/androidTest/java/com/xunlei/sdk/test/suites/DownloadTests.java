package com.xunlei.sdk.test.suites;

import android.test.AndroidTestCase;

import com.xunlei.sdk.test.cases.download.FileTypeTest;
import com.xunlei.sdk.test.cases.download.MultiDownloadTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Brian on 15/6/9.
 */
public class DownloadTests extends AndroidTestCase {

    public static Test suite() {
        TestSuite downloadSuite = new TestSuite(DownloadTests.class.getName());
        //$JUnit-BEGIN$
        downloadSuite.addTestSuite(FileTypeTest.class);
        downloadSuite.addTestSuite(MultiDownloadTest.class);
        //$JUnit-END$
        return downloadSuite;
    }
}
