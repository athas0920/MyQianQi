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

import com.suning.snrf.fragment.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

class DownloadThreadPool implements DownloadListener {
	private final static String TAG = DownloadThreadPool.class.getSimpleName();
	private int mCorePoolSize = 2;
	private int maximumPoolSize = 2;
	private long keepAliveTime = 0L;
	private BlockingQueue<Runnable> workQueue;
	private RejectedExecutionHandler handler;
	private ExecutorService mThreadPool;
	private Context mContext;
	/* 任务队列*/
	private List<HttpDownloader> mDownloaders;
	//private List<DownloadRequest> mDownloadRequests;
	private Vector<DownloadRequest> mDownloadRequests;
	private List<DownloadListener> mDownloadListeners;
	
	DownloadThreadPool(Context context) {
		this(2,context);
	}

	DownloadThreadPool(int corePoolSize, Context context) {
		mCorePoolSize = corePoolSize;
		maximumPoolSize = corePoolSize;
		workQueue = new LinkedBlockingQueue<Runnable>();
        handler = new ThreadPoolExecutor.AbortPolicy();
		//mThreadPool = new ThreadPoolExecutor(mCorePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, handler);
        mThreadPool = Executors.newFixedThreadPool(mCorePoolSize);
		//mDownloadRequests = new ArrayList<DownloadRequest>();
		mDownloadRequests = new Vector<DownloadRequest>();
		mDownloaders = new ArrayList<HttpDownloader>();
		mDownloadListeners = new ArrayList<DownloadListener>();
		mContext = context;
	}

	void enqueue(DownloadRequest request) {
		LogUtil.v(TAG, "DownloadThreadPool enqueue() request=" + request.toString());
		HttpDownloader downloader = HttpDownloader.create(mContext, request)
				.setThreadNum(Config.DOWNLOAD_THREADS)
				.setDownloadListener(mDownloadListeners);
		
		mThreadPool.submit(downloader);
		mDownloadRequests.add(request);
	}
	
	/**
	 * 根据id获取正在执行的任务中与之对应的任务
	 * @param id
	 * @return
	 */
	DownloadRequest getDownloadRequest(long id) {
		for (DownloadRequest r : mDownloadRequests) {
			if (r.getId() == id) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * 根据下载地址url获取正在执行的任务中与之对应的任务
	 * @param url
	 * @return
	 */
	DownloadRequest getDownloadRequest(String url){
		for(DownloadRequest r : mDownloadRequests){
			if(r.getSrcUri().equals(url))
				return r;
		}
		return null;
	}
	
	/**
	 * 获取线程池的所有任务
	 * 不包括已经完成的任务
	 * 
	 * @return List<DownloadRequest>
	 */
	List<DownloadRequest> getDownloadRequestInPool(){
		return  mDownloadRequests;
	}
	
	List<DownloadListener> getDownloadListeners(){
		return mDownloadListeners;
	}
	
	
	void addTask(HttpDownloader task) {
		mDownloaders.add(task);
	}
	
	void removeTask(HttpDownloader task) {
		mDownloaders.remove(task);
	}
	
	void addDownloadListener(DownloadListener downloadListener){
		mDownloadListeners.add(downloadListener);
	}
	
	void removeDownloadListener(DownloadListener downloadListener){
		mDownloadListeners.remove(downloadListener);
	}

	@Override
	public void onStart(DownloadRequest request) {
		
	}

	@Override
	public void onProgress(DownloadRequest request) {
		
	}

	@Override
	public void onError(DownloadRequest request) {
		
	}

	@Override
	public void onComplete(DownloadRequest request) {
		mDownloadRequests.remove(request);
	}

	@Override
	public void onPause(DownloadRequest mDownloadRequest) {
		
	}
}
