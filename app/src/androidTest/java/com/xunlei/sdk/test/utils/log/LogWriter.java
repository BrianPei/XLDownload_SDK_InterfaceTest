package com.xunlei.sdk.test.utils.log;

import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * dump log to sdcard
 */
public class LogWriter {

	private String mLogDir = null;
	private String mLogFileName = null;

	private static final long MAX_LOGFILE_SIZE = 2 * 1024 * 1024;

	private BufferedWriter mOutWriter = null;
	private long mCurrFileSize = 0;

	private static final String DATE_FORMAT = "MM-dd HH:mm:ss:SSS";
	private SimpleDateFormat mDateFormat = new SimpleDateFormat(DATE_FORMAT);
	private Calendar mCalendar = Calendar.getInstance();

	public LogWriter(String logDir, String logFileName) {
		mLogDir = logDir;
		mLogFileName = logFileName;
	}

	public void close() {
		if (null != mOutWriter) {
			try {
				mOutWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mOutWriter = null;
	}

	public synchronized void writeLog(String level, String tag, String msg,
			Throwable tr) {
		if (null == mOutWriter) {
			boolean success = openFile();
			if (!success) {
				return;
			}
		}

		StringBuilder log = composeLog(level, tag, msg, tr);
		mCurrFileSize += log.length();

		try {
			mOutWriter.write(log.toString());
			mOutWriter.flush();
			mOutWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
		}

		if (mCurrFileSize > MAX_LOGFILE_SIZE) {
			// TODO change log file.
		}
	}

	private boolean openFile() {
		if (TextUtils.isEmpty(mLogDir)) {
			throw new IllegalArgumentException("LOG_DIR can't be empty!");
		}
		if (TextUtils.isEmpty(mLogFileName)) {
			throw new IllegalArgumentException("LOG_FILE_NAME can't be empty!");
		}

		File f = new File(mLogDir);

		if (f.exists() && !f.isDirectory()) {
			f.delete();
		}
		boolean logDirExists = false;
		if (!f.exists()) {
			logDirExists = f.mkdirs();
		} else {
			logDirExists = true;
		}
		if (!logDirExists) {
			return false;
		}

		f = new File(mLogDir, mLogFileName);
		mOutWriter = null;
		try {
			mOutWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, true)));
			mOutWriter.write("\n");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private StringBuilder composeLog(String level, String tag, String msg,
			Throwable tr) {
		StringBuilder log = new StringBuilder(512);
		
		log.append(composeTime());
		log.append(" ");
		log.append(level + "/" + tag);
		log.append("\t");
		if (!TextUtils.isEmpty(msg)) {
			log.append(msg);
			if (null != tr) {
				log.append("\n\t");
			}
		}
		log.append(Log.getStackTraceString(tr));

		return log;
	}

	private String composeTime() {
		mCalendar.setTimeInMillis(System.currentTimeMillis());
		return mDateFormat.format(mCalendar.getTime());
	}

}
