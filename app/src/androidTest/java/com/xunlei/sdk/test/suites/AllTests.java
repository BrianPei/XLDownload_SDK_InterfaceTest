package com.xunlei.sdk.test.suites;

import android.test.AndroidTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Brian on 15/6/9.
 */
public class AllTests extends AndroidTestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTest(QueryTests.suite());
        suite.addTest(RequestTests.suite());
        //$JUnit-END$
        return suite;
    }
}
