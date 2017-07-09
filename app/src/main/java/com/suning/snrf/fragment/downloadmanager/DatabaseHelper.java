/*******************************************************************************
 * Copyright 2011-2013
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.suning.snrf.fragment.downloadmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.suning.snrf.fragment.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>单列模式的数据库操作类
 * 
 * @version 1.0.0
 * @author Yan.Sairong
 *
 */
class DatabaseHelper implements DownloadListener {
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	
	private static final String DATABASE_NAME = "downloads.db";
	
	private static final String TABLE_NAME = "downloads";
	
	private static final int DATABASE_VERSION = 3;
	
	private static DatabaseHelper mDatabaseHelper;
	
	private List<ContentListener> mContentListeners;
	
	private MyOpenHelper mOpenHelper;
	
	
	private DatabaseHelper (Context context) {
		mOpenHelper = new MyOpenHelper(context);
		mContentListeners = new ArrayList<ContentListener>();
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
	}
	
	public static DatabaseHelper getInstence(Context context){
		if(mDatabaseHelper == null)
			return mDatabaseHelper = new DatabaseHelper(context);
		return mDatabaseHelper;
	}
	
	public long insert(ContentValues values) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long id = db.insert(TABLE_NAME, null, values);
		LogUtil.e(TAG, "DatabaseHelper insert() id=" + id);
		notifyContentChange();
		return id;
	}
	
	synchronized public long update(ContentValues values, final String where, final String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long id = db.update(TABLE_NAME, values, where, whereArgs);
		LogUtil.e(TAG, "DatabaseHelper update() id=" + id);
		notifyContentChange();
		return id;
	}
	
	synchronized public long delete(final String where, final String[] whereArgs) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long id = db.delete(TABLE_NAME, where, whereArgs);
		LogUtil.v(TAG, "DatabaseHelper delete() id=" + id);
		notifyContentChange();
		return id;
	}
	
	public Cursor query(String[] projection, String selection,
			String[] selectionArgs, String orderBy) {
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		LogUtil.e(TAG, "DatabaseHelper query()");
		return db.query(TABLE_NAME, projection, selection,
				selectionArgs, null, null, orderBy);
	}
	
	public void updatePatch(List<ContentValues> values, final String where, final String[] whereArgs){
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		try {
			db.beginTransaction();
            for (ContentValues v : values) {
                db.update(TABLE_NAME, v, where, whereArgs);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
	}
	
	public void close(){
		if(mOpenHelper != null)
			mOpenHelper.close();
	}
	
	private static final class MyOpenHelper extends SQLiteOpenHelper {

		public MyOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createTable(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			createTable(db);
		}

		private void createTable(SQLiteDatabase db) {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE ").append(TABLE_NAME).append("(")
					.append(DownloadColumns._ID).append(" INTEGER PRIMARY KEY autoincrement,")
					.append(DownloadColumns.UUID).append(" TEXT,")
					.append(DownloadColumns.SRC_URI).append(" TEXT,")
					.append(DownloadColumns.DEST_URI).append(" TEXT,")
					.append(DownloadColumns.TITLE).append(" TEXT,")
					.append(DownloadColumns.SUPPORT_CONTINUE).append(" TEXT,")
					.append(DownloadColumns.TOTAL_SIZE).append(" TEXT,")
					.append(DownloadColumns.DOWNLOAD_SIZE).append(" TEXT,")
					.append(DownloadColumns.DOWNLOAD_STATUS).append(" TEXT,")
					.append(DownloadColumns.TIMESTAMP).append(" TEXT,")
					.append(DownloadColumns.EXTRA_VALUE).append(" TEXT")
					.append(");");

			db.execSQL(sb.toString());
		}
	}
	
	
	public void addContentListener(ContentListener l) {
		if (!mContentListeners.contains(l)) {
			mContentListeners.add(l);
		}
	}

	public void removeContentListener(ContentListener l) {
		mContentListeners.remove(l);
	}
	
	public void notifyContentChange() {
		for (ContentListener l : mContentListeners) {
			l.onContentChange();
		}
	}

	@Override
	public void onStart(DownloadRequest request) {
		LogUtil.i(TAG, "DatabaseHelper onStart() request=" + request);
		String where = DownloadColumns._ID + "=" + request.getId();
		update(request.toContentValues(), where, null);
	}

	@Override
	public void onProgress(DownloadRequest request) {
		LogUtil.i(TAG, "DatabaseHelper onProgress() request=" + request.getDownloadSize());
		String where = DownloadColumns._ID + "=" + request.getId();
		update(request.toContentValues(), where, null);
	}

	@Override
	public void onError(DownloadRequest request) {
		LogUtil.i(TAG, "DatabaseHelper onError() request=" + request);
		String where = DownloadColumns._ID + "=" + request.getId();
		update(request.toContentValues(), where, null);
	}

	@Override
	public void onComplete(DownloadRequest request) {
		LogUtil.i(TAG, "DatabaseHelper onComplete() request=" + request);
		String where = DownloadColumns._ID + "=" + request.getId();
		//update(request.toContentValues(), where, null);
		delete(where, null);
	}
	
	@Override
	public void onPause(DownloadRequest request) {
		LogUtil.i(TAG, "DatabaseHelper onPause() request=" + request);
		String where = DownloadColumns._ID + "=" + request.getId();
		update(request.toContentValues(), where, null);
	}
	
	
	/**
	 * The listener interface for database content change.
	 * 
	 */
	public interface ContentListener {
		/**
		 * This method will be invoked, when database content change.
		 */
		void onContentChange();
	}
	
}
