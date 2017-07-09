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

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.suning.snrf.fragment.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式的下载管理器类
 * 
 * <p><strong>注意：只支持HTTP/HTTPS协议的下载</strong>
 * 
 * <p>所有的下载任务在一个线程池中. 当第一次创建该类对象是需要设置线程池核心线程数,
 * 默认的线程数为2
 * 
 * <p>下载历史将会被记录作为将来查询使用
 * 
 * @author Yan.Sairong
 * @version 1.0.0
 */
public class DownloadManager {
	private static final String TAG = DownloadManager.class.getSimpleName();
	
	private DownloadThreadPool mDownloadThreadPool;
	private static DatabaseHelper mDBHelper;
	private static DownloadManager mInstance;
	private static Context context;
	
	
	/**
	 * 下载建造器类
	 * <p><strong>注意：必须在调用 {@link Builder#build()} 
	 * 之前，调用{@link Builder#setContext(Context)}</strong>
	 * 
	 * @author Yan.Sairong
	 */
	public static class Builder {
		
		private Context mContext;
		/**
		 * 设置上下文对象 
		 *  
		 * @param context 上下文对象
		 * @return Builder对象
		 */
		public Builder setContext(Context context) {
			mContext = context;
			DownloadManager.context = mContext;
			return this;
		}
		
		/**
		 * 设置多线程下载的线程数
		 * <p>当在移动设备上使用时，建议最大线程数不要超过3.
		 * 
		 * @param threadNum  多线程下载时的线程数
		 * @return  Builder对象
		 */
		public Builder setDownloadThreadNum(int threadNum) {
			Config.setDownloadThreads(threadNum);
			return this;
		}
		
		/**
		 * 设置线程池的核心线程数
		 * <p>当在移动设备上使用时，建议最大线程数不要超过5.
		 * 
		 * @param threadNum 线程池核心线程数
		 * @return  Builder对象
		 */
		public Builder setPoolSize(int poolSize){
			Config.setPoolSize(poolSize);
			return this;
		}
		
		/**
		 * 通过Builder参数 构造一个DownloadManager对象
		 * @return  DownloadManager
		 */
		public DownloadManager build() {
			if (mContext == null) {
				throw new IllegalArgumentException();
			}
			return new DownloadManager(this);
		}
	}
	
	/**
	 * 获取或创建一个DownloadManager单例对象
	 * 
	 * @param context
	 * @param maxThread 
	 * @return  DownloadManager
	 * @deprecated  使用 {@link Builder} 作为替代，来创建DownloadManager
	 */
	public static DownloadManager getInstance(Context context, int maxThread) {
		synchronized (DownloadManager.class) {
			if (mInstance == null) {
				mInstance = new DownloadManager(
						context.getApplicationContext(), maxThread);
			}
		}
		return mInstance;
	}
	
	private DownloadManager(Context context, int maxThread) {
		if (mInstance != null) {
			throw new IllegalStateException("Can not create singlton object Duplicate");
		}
		mDownloadThreadPool = new DownloadThreadPool(maxThread, context);
		mDBHelper = DatabaseHelper.getInstence(context);
		//mDBHelper = new DatabaseHelper(context);
		//addDownloadListener(mDBHelper);
		addDownloadListener(mDownloadThreadPool);
	}
	
	private DownloadManager(Builder builder) {
		mDownloadThreadPool = new DownloadThreadPool(Config.POOL_SIZE, context);
		mDBHelper = DatabaseHelper.getInstence(builder.mContext);
		//mDBHelper = new DatabaseHelper(builder.mContext);
		//addDownloadListener(mDBHelper);
		addDownloadListener(mDownloadThreadPool);
	}
	
	/**
	 * 增加一个监听器
	 * 
	 * @param listener DownloadListener对象
	 */
	public void addDownloadListener(DownloadListener listener) {
		mDownloadThreadPool.addDownloadListener(listener);
	}
	
	/**
	 * 移除一个监听器
	 * 
	 * @param listener DownloadListener对象
	 */
	public void removeDownloadListener(DownloadListener listener) {
		mDownloadThreadPool.removeDownloadListener(listener);
	}
	
	/**
	 * 获取所有的下载监听器
	 * 
	 * @return
	 */
	public List<DownloadListener> getDownloadListeners(){
		return mDownloadThreadPool.getDownloadListeners();
	}
	
	/**
	 * 提交一个任务到线程池的任务队列
	 * 如果线程池中有空闲线程它将会立即开始任务
	 * 
	 * @param request DownloadRequest对象 
	 * 			<p><strong>注意： 该DownloadRequest对象 可能被修改. 例如当DownloadRequest开始时,
	 * 			{@link DownloadRequest#mDownloadStatus} 将被修改 </strong>
	 */
	public void enqueue(DownloadRequest request) {
		LogUtil.v(TAG, "enqueue()  " + request.toString());
		if (request.getId() == -1) {
			ConcurrentHashMap<Integer, Long> threadSet = new ConcurrentHashMap<Integer, Long>();
			// 当新任务被提交时初始化子线程信息，即设置各下载线程的已下载长度为 0
			for(int i = 0; i< Config.DOWNLOAD_THREADS; i++){
				threadSet.put(i, 0L);
			}
			
			ThreadModel extraValue = new ThreadModel(threadSet);
			request.setExtraValue(extraValue.toString());
			request.setDownloadSize(0);
			
			long id = mDBHelper.insert(request.toContentValues());
			request.setId(id);
		}
		request.setDownloadStatus(DownloadStatus.STATUS_IDLE);
		mDownloadThreadPool.enqueue(request);
	}
	
	/**
	 * 提交一个任务列表到线程池中
	 * see {@link #enqueue(DownloadRequest)}
	 * <p><strong>注意：requests.mDownloadStatus 必须和 DownloadStatus.STATUS_IDLE
	 * 作比较，以判断提交的任务是否是处于挂起或闲置状态</strong>
	 * 
	 * @param requests The request object
	 */
	public void enqueue(List<DownloadRequest> requests) {
		for (DownloadRequest r : requests) {
			enqueue(r);
		}
	}
	
	/**
	 * 通过下载地址获取DownloadRequest
	 * <b>如果该url对应的任务在线程池中没有找到则返回一个数据库中记录的DownloadRequest
	 * ，如果数据库也没有该url对应的记录则返回null</b>
	 * 
	 * @param src_url 下载地址
	 * @return DownloadRequest对象
	 */
	public DownloadRequest getRequestByUrl(String src_url){
		List<DownloadRequest> requests = query("src_url=?", new String[]{src_url}, null);
		if(requests != null && requests.size() != 0){
			DownloadRequest r = requests.get(0);
			/*在线程池中的任务*/
			DownloadRequest rInPool = mDownloadThreadPool.getDownloadRequest(r.getId());
			return rInPool == null ? r : rInPool;
		}
			
		return null;
	}
	
	/**
	 * 获取所有在线程池中的任务，不包括已经结束的任务
	 * @return
	 */
	public List<DownloadRequest> getDownloadRequestInPool(){
		return mDownloadThreadPool.getDownloadRequestInPool();
	}
	
	/**
	 * 暂停一个正在下载或者准备下载的任务
	 * 
	 * @param request DownloadRequest对象
	 */
	public void pause(DownloadRequest request) {
		String status = request.getDownloadStatus();
		if (status.equals(DownloadStatus.STATUS_IDLE)
				|| status.equals(DownloadStatus.STATUS_START)) {
			request.setDownloadStatus(DownloadStatus.STATUS_PAUSE);
		}
	}
	
	/**
	 * 取消一个任务，并且删除数据库中的记录
	 * 
	 * @param request DownloadRequest对象
	 */
	public void delete(DownloadRequest request) {
		pause(request);
		request.setDownloadStatus(DownloadStatus.STATUS_DELETE);
		String where = DownloadColumns._ID + "=" + request.getId();
		mDBHelper.delete(where, null);
	}
	
	/**
	 * 查询下载任务的历史记录
	 * 
	 * 参数请说明{@linkplain SQLiteDatabase#query(String, String[], String, String[], String, String, String)}
	 * 
	 * @param selection
	 *            A filter declaring which rows to return, formatted as an SQL
	 *            Where clause, passing null will return all rows.
	 * @param selectionArgs
	 *            You may include ?s in selection, which will be replace by the
	 *            values from this parameters, in the order that they appear in
	 *            the selection. The values will be bound as Strings
	 * @param orderBy
	 *            How to order the rows, formatted as and SQL ORDER BY clause.
	 *            Passing null will use the default sort order, which may be
	 *            unordered.
	 * @return The result set according to selection.
	 */
	public List<DownloadRequest> query(String selection, 
			String[] selectionArgs, String orderBy) {

		List<DownloadRequest> resultSet = new ArrayList<DownloadRequest>();
		Cursor cursor = mDBHelper.query(null, selection, selectionArgs, orderBy);
		if (cursor != null && cursor.moveToFirst()) {
			do {
				long id = cursor.getLong(cursor.getColumnIndex(DownloadColumns._ID));
				DownloadRequest excutingRequest =
						mDownloadThreadPool.getDownloadRequest(id);
				if (excutingRequest != null) {
					LogUtil.v(TAG, "query excutingRequest != null");
					resultSet.add(excutingRequest);
				} else {
					LogUtil.v(TAG, " query excutingRequest == null");
					DownloadRequest request = new DownloadRequest(cursor);
					//request.setDownloadStatus(DownloadColumns.STATUS_IDLE);
					resultSet.add(request);
				}
			} while (cursor.moveToNext());
		}
		cursor.close();
		return resultSet;
	}
	
	/**
	 * <p><b>查询下载任务的历史记录</b></p>
	 * <p>当内存中没有对应的下载记录则从数据库中查找
	 * @param url
	 * @return
	 */
	public List<DownloadRequest> query(String url){
		List<DownloadRequest> resultSet = new ArrayList<DownloadRequest>();
		DownloadRequest excutingRequest = mDownloadThreadPool.getDownloadRequest(url);
		if(excutingRequest == null){
			//return query("src_url=?", new String[]{url}, null);
		}
		else{
			resultSet.add(excutingRequest);
		}
		return resultSet;
	}
	
	/**
	 * 下载所有在数据库中显示为完成的任务
	 * 
	 * @deprecated  请使用 query()和 enqueue()替代该方法
	 */
	public void enqueueHistory() {
		
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				String selection = DownloadColumns.DOWNLOAD_STATUS + 
						"!='" + DownloadStatus.STATUS_COMPLETE + "'";
				Cursor cursor = mDBHelper.query(null, selection, null, null);
				LogUtil.v(TAG, " enqueueHistory start run");
				if (cursor != null && cursor.moveToFirst()) {
					do {
						DownloadRequest request = new DownloadRequest(cursor);
						request.setDownloadStatus(DownloadStatus.STATUS_IDLE);
						LogUtil.v(TAG, " enqueueHistory request=" + request);
						mDownloadThreadPool.enqueue(request);
					} while (cursor.moveToNext());
				}
			}
		})).start();
	}
	
	/**
	 * 获取DatabaseHelper实例对象
	 * 
	 * @return DatabaseHelper
	 */
	public DatabaseHelper getDBHelper(){
		return mDBHelper;
	}
	
	/**
	 * 获取DownloadThreadPool实例对象
	 * 
	 * @return DownloadThreadPool
	 */
	public DownloadThreadPool getThreadPool(){
		return mDownloadThreadPool;
	}
	
}
