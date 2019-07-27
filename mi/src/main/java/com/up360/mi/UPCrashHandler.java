package com.up360.mi;

import android.content.Context;
import android.content.Intent;

import com.up360.mi.activity.IndexActivity;
import com.up360.mi.config.SystemConstants;
import com.up360.mi.utils.DateShowUtils;
import com.up360.mi.utils.FileUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

/**
 * Created by mi.gao
 *
 */
public class UPCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final UPCrashHandler INSTANCE = new UPCrashHandler();
    private Context mContext;
    private String mLogDir;
    public static UPCrashHandler getInstance(){
        return INSTANCE;
    }

    public void init(Context ctx){
        mLogDir = SystemConstants.APP_SDCARD_PATH + SystemConstants.LOG_DIR;
		FileUtil.createDir(mLogDir);

        mContext = ctx;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String currentTimeStamp = DateShowUtils.df_filename.format(new Date());
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        String stacktrace = result.toString();
        printWriter.close();
        String filename = currentTimeStamp + ".log";
        writeToFile(stacktrace, filename);
        sendToServer(stacktrace);

        Intent intent = new Intent();
        intent.setClass(mContext, IndexActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }
    private void writeToFile(String stacktrace, String filename) {
        try {
            BufferedWriter bos = new BufferedWriter(new FileWriter(mLogDir + "/" + filename));
            bos.write(stacktrace);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendToServer(String stacktrace) {

    }
}
