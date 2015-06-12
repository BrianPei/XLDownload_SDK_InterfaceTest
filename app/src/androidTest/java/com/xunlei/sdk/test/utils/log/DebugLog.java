package com.xunlei.sdk.test.utils.log;

import android.os.Environment;
import android.util.Log;

/**
 * dump the log to sdcard file, should replace Config.LOG by this step by step
 */
public class DebugLog {

	private static String TAG = "sdktest";
	private static String LOG_DIR = Environment.getExternalStorageDirectory()
			.getPath() + "/sdk_test_log";
	private static String LOG_CURR_FILENAME = "debug_log.txt";

	public static final boolean DEBUG = true;
	public static final boolean LOGVV = true && DEBUG;
	public static final boolean LOGV = true && DEBUG;
	public static final boolean LOGD = true && DEBUG;
	public static final boolean LOGW = true && DEBUG;
	public static final boolean LOGE = true && DEBUG;
	public static final boolean LOGR = true && DEBUG;

	private static final boolean DUMP_LOG_TO_CONSOLE = DEBUG;

	private static final String VERBOSE_VERBOSE_TAG = "VV";
	private static final String VERBOSE_TAG = "VERBOSE";
	private static final String DEBUG_TAG = "DEBUG";
	private static final String WARNING_TAG = "WARNIG";
	private static final String ERORR_TAG = "ERROR";
	private static final String RELEASE_TAG = "RELEASE";

	private static DebugLog instance = null;
	private LogWriter logWriter = null;

	public synchronized static DebugLog getInstance() {
		if (instance == null) {
			instance = new DebugLog();
		}
		return instance;
	}

	private DebugLog() {
		logWriter = new LogWriter(LOG_DIR, LOG_CURR_FILENAME);
	}

	public void writeLog(String level, String tag, String msg, Throwable tr) {
		logWriter.writeLog(level, tag, msg, tr);
	}

	/**
	 * Send a verbose log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void vv(String tag, String msg) {
		vv(tag, msg, null);
	}

	/**
	 * Send a verbose log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void vv(String tag, String msg, Throwable tr) {
		if (!LOGVV) {
			return;
		}

		getInstance().writeLog(VERBOSE_VERBOSE_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			Log.d(TAG, "[[" + tag + "]]" + msg, tr);
		}
	}

	/**
	 * Send a verbose log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void v(String tag, String msg) {
		v(tag, msg, null);
	}

	/**
	 * Send a verbose log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void v(String tag, String msg, Throwable tr) {
		if (!LOGV) {
			return;
		}

		getInstance().writeLog(VERBOSE_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			Log.d(TAG, "[[" + tag + "]]" + msg, tr);
		}
	}

	/**
	 * Send a debug log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void d(String tag, String msg) {
		d(tag, msg, null);
	}

	/**
	 * Send a debug log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void d(String tag, String msg, Throwable tr) {
		if (!LOGD) {
			return;
		}

		getInstance().writeLog(DEBUG_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			// Log.d(TAG, "[[" + tag + "]]" + msg, tr);
			int index = tag.indexOf(".java");
			if (index > 0) {
				msg = "[[" + tag + "]]" + msg;
				tag = tag.substring(0, index);
			}
			tag = tag.replace("[[", "").replace("]]", "");
			Log.d(tag, msg, tr);
		}
	}

	/**
	 * Send a warning log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void w(String tag, String msg) {
		w(tag, msg, null);
	}

	/**
	 * Send a warning log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void w(String tag, String msg, Throwable tr) {
		if (!LOGW) {
			return;
		}

		getInstance().writeLog(WARNING_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			Log.d(TAG, "[[" + tag + "]]" + msg, tr);
		}
	}

	/**
	 * Send a error log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void e(String tag, String msg) {
		e(tag, msg, null);
	}

	/**
	 * Send a error log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void e(String tag, String msg, Throwable tr) {
		if (!LOGE) {
			return;
		}

		getInstance().writeLog(ERORR_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			Log.e(TAG, "[[" + tag + "]]" + msg, tr);
		}
	}

	/**
	 * Send a release log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 */
	public static void r(String tag, String msg) {
		r(tag, msg, null);
	}

	/**
	 * Send a release log message.
	 * 
	 * @param tag
	 *            Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg
	 *            The message you would like logged.
	 * @param tr
	 *            An exception to log
	 */
	public static void r(String tag, String msg, Throwable tr) {
		if (!LOGR) {
			return;
		}

		getInstance().writeLog(RELEASE_TAG, tag, msg, tr);
		if (DUMP_LOG_TO_CONSOLE) {
			Log.e(TAG, "[[" + tag + "]]" + msg, tr);
		}
	}

}
