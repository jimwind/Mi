package com.up360.mi.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class BookProvider extends ContentProvider {
    private static final String TAG = "BookProvider ";
    public static final String AUTHORITY = "com.up360.mi.provider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/user");

    public static final int BOOK_URI_CODE = 0;
    public static final int USER_URI_CODE = 1;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
    }

    private String getTableName(Uri uri){
        String tableName = null;
        switch (sUriMatcher.match(uri)){
            case BOOK_URI_CODE:{
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            }
            case USER_URI_CODE:{
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            }
        }
        return tableName;
    }

    @Override
    public boolean onCreate() {
        Log.e("jimwind", "BookProvider process id is "+android.os.Process.myPid());
        Log.e("jimwind", TAG + "onCreate, current thread: " + Thread.currentThread().getName());
        return false;
    }


    @Override
    public Cursor query(Uri uri,  String[] projection,  String selection,  String[] selectArgs,  String sortOrder) {
        Log.e("jimwind", TAG + "query, current thread: " + Thread.currentThread().getName());
        return null;
    }


    @Override
    public String getType(Uri uri) {
        Log.e("jimwind", TAG + "getType");
        return null;
    }


    @Override
    public Uri insert(Uri uri,  ContentValues contentValues) {
        Log.e("jimwind", TAG + "insert");
        return null;
    }

    @Override
    public int delete(Uri uri,  String selection,  String[] selectionArgs) {
        Log.e("jimwind", TAG + "delete");
        return 0;
    }

    @Override
    public int update(Uri uri,  ContentValues contentValues,  String selection,  String[] selectionArgs) {
        Log.e("jimwind", TAG + "update");
        return 0;
    }
}
