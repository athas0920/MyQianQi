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
import android.database.Cursor;

import java.io.Serializable;

/**
 * 下载任务的模型，开始下载，准备下载，暂停下载和取消下载需提交一个DownloadRequest的实例
 * 可以通过下载地址和文件的保存路径构造一个下载任务模型，也可以通过数据库的实体对象来创建
 * 
 * @author Yan.Sairong
 */
public class DownloadRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 如果 mId == -1 表示该下载任务是一个新建的下载任务并且需要被插入到数据库中以作为下载记录
	private long mId = -1;
	private String mUuid = " ";
	private String mSrcUri;
	private String mDestUri;
	private boolean mSupportContinue = true;
	private String mTitle = "unknown";
	private long mTotalSize = 0;
	private long mDownloadSize = 0;
	private String mDownloadStatus = DownloadStatus.STATUS_IDLE;
	private String mTimeStamp = "00-00-00";
	private String mExtraValue = " ";
	
	private DownloadListener mDownloadListener;
	
	
	/**
	 * 通过下载地址和文件的保存路径构造一个下载任务模型
	 * 
	 * @param srcUri  下载地址
	 * @param destUri 存储路径
	 */
	public DownloadRequest(String srcUri, String destUri) {
		mSrcUri = srcUri;
		mDestUri = destUri;
	}
	
	public DownloadRequest(DownloadRequest request){
		mId = request.getId();
		mUuid = request.getUuid();
		mSrcUri = request.getSrcUri();
		mDestUri = request.getDestUri();
		mTitle = request.getTitle();
		mSupportContinue = request.getSupportContinue();
		mTotalSize = request.getTotalSize();
		mDownloadSize = request.getDownloadSize();
		mDownloadStatus = request.getDownloadStatus();
		mTimeStamp = request.getTimeStamp();
		mExtraValue = request.getExtraValue();
	}
	
	/**
	 * 通过数据库实体构造一个下载任务模型
	 * 
	 * @param cursor
	 */
	public DownloadRequest(Cursor cursor) {
		mId = cursor.getLong(cursor.getColumnIndex(DownloadColumns._ID));
		mUuid = cursor.getString(cursor.getColumnIndex(DownloadColumns.UUID));
		mSrcUri = cursor.getString(cursor.getColumnIndex(DownloadColumns.SRC_URI));
		mDestUri = cursor.getString(cursor.getColumnIndex(DownloadColumns.DEST_URI));
		mTitle = cursor.getString(cursor.getColumnIndex(DownloadColumns.TITLE));

		int intValue = cursor.getInt(cursor.getColumnIndex(DownloadColumns.SUPPORT_CONTINUE));
		mSupportContinue = (intValue == 0) ? false : true;
		
		mTotalSize = cursor.getLong(cursor.getColumnIndex(DownloadColumns.TOTAL_SIZE));
		mDownloadSize = cursor.getLong(cursor.getColumnIndex(DownloadColumns.DOWNLOAD_SIZE));
		mDownloadStatus = cursor.getString(cursor.getColumnIndex(DownloadColumns.DOWNLOAD_STATUS));
		mTimeStamp = cursor.getString(cursor.getColumnIndex(DownloadColumns.TIMESTAMP));
		mExtraValue = cursor.getString(cursor.getColumnIndex(DownloadColumns.EXTRA_VALUE));
	}
	
	/**
	 * 通过DownloadRequest创建一个数据ContentValues对象
	 * 用来增删改查操作
	 * 
	 * @return ContentValues
	 */
	synchronized public ContentValues toContentValues() {
		ContentValues value = new ContentValues();
		if (mId != -1) {
			value.put(DownloadColumns._ID, mId);
		}
		value.put(DownloadColumns.UUID, mUuid);
		value.put(DownloadColumns.SRC_URI, mSrcUri);
		value.put(DownloadColumns.DEST_URI, mDestUri);
		value.put(DownloadColumns.TITLE, mTitle);
		value.put(DownloadColumns.SUPPORT_CONTINUE, (mSupportContinue ? 1 : 0));
		value.put(DownloadColumns.TOTAL_SIZE, mTotalSize);
		value.put(DownloadColumns.DOWNLOAD_SIZE, mDownloadSize);
		value.put(DownloadColumns.DOWNLOAD_STATUS, mDownloadStatus);
		value.put(DownloadColumns.TIMESTAMP, mTimeStamp);
		value.put(DownloadColumns.EXTRA_VALUE, mExtraValue);
		
		return value;
	}
	
	/**
	 * 获取数据库主键
	 * 
	 * @return 数据库主键
	 */
	synchronized public long getId() {
		return mId;
	}
	
	/**
	 * 设置数据库主键值
	 * 
	 * @param id  主键
	 * @return 主键值
	 */
	synchronized long setId(long id) {
		return mId = id;
	}
	
	synchronized public String getUuid() {
		return mUuid;
	}
	
	synchronized public String setUuid(String uuid) {
		return mUuid = uuid;
	}
	
	synchronized public String getSrcUri() {
		return mSrcUri;
	}
	
	synchronized String setSrcUri(String srcUri) {
		return mSrcUri = srcUri;
	}
	
	synchronized public String getDestUri() {
		return mDestUri;
	}
	
	synchronized String setDestUri(String destUri) {
		return mDestUri = destUri;
	}
	
	synchronized public String getTitle() {
		return mTitle;
	}
	
	synchronized public String setTitle(String title) {
		return mTitle = title;
	}
	
	synchronized public boolean getSupportContinue() {
		return mSupportContinue;
	}

	synchronized public void setSupportContinue(boolean isContinue) {
		mSupportContinue = isContinue;
	}
	
	synchronized public long getTotalSize() {
		return mTotalSize;
	}
	
	synchronized public long setTotalSize(long size) {
		return mTotalSize = size;
	}
	
	synchronized public long getDownloadSize() {
		return mDownloadSize;
	}
	
	synchronized long setDownloadSize(long size) {
		return mDownloadSize = size;
	}

	synchronized public String getDownloadStatus() {
		return mDownloadStatus;
	}
	
	synchronized String setDownloadStatus(String status) {
		return mDownloadStatus = status;
	}
	
	synchronized public String getTimeStamp() {
		return mTimeStamp;
	}

	synchronized public void setTimeStamp(String timeStamp) {
		mTimeStamp = timeStamp;
	}
	
	synchronized public String getExtraValue() {
		return mExtraValue;
	}

	synchronized public void setExtraValue(String extraValue) {
		mExtraValue = extraValue;
	}
	
	synchronized public DownloadListener getDownloadListener() {
		return mDownloadListener;
	}
	
	synchronized public void setDownloadListener(DownloadListener listener) {
		mDownloadListener = listener;
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[mId=").append(mId)
				.append(", mUuid=").append(mUuid)
				.append(", mSrcUri=").append(mSrcUri)
				.append(", mDestUri=").append(mDestUri)
				.append(", mTitle=").append(mTitle)
				.append(", mSupportContinue=").append(mSupportContinue)
				.append(", mTotalSize=").append(mTotalSize)
				.append(", mDownloadSize=").append(mDownloadSize)
				.append(", mDownloadStatus=").append(mDownloadStatus)
				.append(", mTimeStamp=").append(mTimeStamp)
				.append(", mExtraValue=").append(mExtraValue)
				.append("]");
		return sb.toString();
	}
}
