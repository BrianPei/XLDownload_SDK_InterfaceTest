package com.xunlei.sdk.test.suites;

import android.test.AndroidTestCase;

import com.xunlei.sdk.test.cases.query.OrderBy;
import com.xunlei.sdk.test.cases.query.SetFilterById;
import com.xunlei.sdk.test.cases.query.SetFilterByStatus;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Brian on 15/6/9.
 */
public class QueryTests extends AndroidTestCase {

    public static Test suite() {
        TestSuite querySuite = new TestSuite(QueryTests.class.getName());
        //$JUnit-BEGIN$
        querySuite.addTestSuite(OrderBy.class);
        querySuite.addTestSuite(SetFilterById.class);
        querySuite.addTestSuite(SetFilterByStatus.class);
        //$JUnit-END$
        return querySuite;
    }
}
