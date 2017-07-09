/** 
 * Copyright 2011-2013
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suning.snrf.fragment.downloadmanager;

import android.content.Context;

import com.suning.snrf.fragment.downloadmanager.DownloadThread.Status;
import com.suning.snrf.fragment.utils.LogUtil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 下载器
 * 
 * @version 1.0.0
 * @author Yan.Sairong
 */
public class HttpDownloader implements Runnable {
	private static final String TAG = HttpDownloader.class.getSimpleName();
	private static HttpDownloader httpDownloader;
	private Context mContext;
	private int mThreadNum;
	private ConcurrentHashMap<Integer, Long> cache = new ConcurrentHashMap<Integer, Long>();
	private DownloadThread[] thread;
	private long[] mBlock;
	private String status;
	private boolean retred;
	private volatile long downloadSize;
	private boolean isCancel;
	private DatabaseHelper databaseHelper;
	private String mSrcUri;
	private String mDestFile;
	private DownloadRequest mDownloadRequest;
	private DownloadManager downloadManager;
	private List<DownloadListener> mDownloadListeners;

	private HttpDownloader(Context context, DownloadRequest request) {
		downloadManager = new DownloadManager.Builder().setContext(context)
				.setDownloadThreadNum(Config.DOWNLOAD_THREADS)
				.setPoolSize(Config.POOL_SIZE).build();
		databaseHelper = downloadManager.getDBHelper();
		mDownloadRequest = request;
		downloadSize = mDownloadRequest.getDownloadSize();
		mSrcUri = request.getSrcUri();
		mDestFile = request.getDestUri();
		mContext = context;
		File file = new File(mDestFile);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
	}

	public static HttpDownloader create(Context context, DownloadRequest request) {
		httpDownloader = new HttpDownloader(context, request);
		return httpDownloader;
	}

	public HttpDownloader setThreadNum(int threadNum) {
		mThreadNum = threadNum;
		thread = new DownloadThread[mThreadNum];
		mBlock = new long[mThreadNum];
		return httpDownloader;
	}

	public HttpDownloader setDownloadListener(
			List<DownloadListener> downloadListeners) {
		mDownloadListeners = downloadListeners;
		return httpDownloader;
	}

	public void doDownload() {
		init();
		notifyStart(mDownloadRequest);
		for (int i = 0; i < mThreadNum; i++) {
			long startPos = i * mBlock[0];
			long endPos = startPos + mBlock[i] - 1;
			thread[i] = new DownloadThread(this, mSrcUri, new File(mDestFile),
					startPos, endPos, cache.get(i), i);
			thread[i].start();
		}
		while (true) {
			sleep(500);
			notifyProgress(mDownloadRequest);
			status = getStatus();
			LogUtil.i(TAG, status);
			if (DownloadStatus.STATUS_PAUSE.equals(status)
					|| DownloadStatus.STATUS_ERROR.equals(status)
					|| DownloadStatus.STATUS_COMPLETE.equals(status)
					|| DownloadStatus.STATUS_DELETE.equals(status)
					|| isAllFinish()) {
				break;
			}
			checkThread();
		}
		LogUtil.e(TAG, "@" + hashCode() + "===> HttpDownload task finish");
		if (isFinish()) {
			notifyComplete(mDownloadRequest);
		} else if (DownloadStatus.STATUS_ERROR.equals(status)) {
			notifyError(mDownloadRequest);
		} else if (DownloadStatus.STATUS_PAUSE.equals(status)) {
			notifyPause(mDownloadRequest);
		}
	}

	public void removeDownloadListener(DownloadListener listener) {
		mDownloadListeners.remove(listener);
	}

	private void notifyStart(DownloadRequest request) {
		request.setDownloadStatus(DownloadStatus.STATUS_START);
		for (DownloadListener l : mDownloadListeners) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onStart() length = "
					+ mDownloadListeners.size());
			l.onStart(request);
		}
		if (request.getDownloadListener() != null) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onStart() request");
			request.getDownloadListener().onStart(request);
		}
	}

	private void notifyComplete(DownloadRequest request) {
		request.setDownloadStatus(DownloadStatus.STATUS_COMPLETE);
		for (DownloadListener l : mDownloadListeners) {
			LogUtil.v(TAG,
					"HttpDownloader notifyComplete() onComplete() length = "
							+ mDownloadListeners.size());
			l.onComplete(request);
		}
		if (request.getDownloadListener() != null) {
			LogUtil.v(TAG,
					"HttpDownloader notifyComplete() onComplete() request");
			request.getDownloadListener().onComplete(request);
		}
	}

	private void notifyProgress(DownloadRequest request) {
		for (DownloadListener l : mDownloadListeners) {
			LogUtil.v(TAG,
					"HttpDownloader notifyProgress() onProgress() length = "
							+ mDownloadListeners.size());
			l.onProgress(request);
		}
		if (request.getDownloadListener() != null) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onProgress() request");
			request.getDownloadListener().onProgress(request);
		}
	}

	private void notifyPause(DownloadRequest request) {
		request.setDownloadStatus(DownloadStatus.STATUS_PAUSE);
		for (DownloadListener l : mDownloadListeners) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onStart() length = "
					+ mDownloadListeners.size());
			l.onStart(request);
		}
		if (request.getDownloadListener() != null) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onStart() request");
			request.getDownloadListener().onStart(request);
		}
	}

	private void notifyError(DownloadRequest request) {
		request.setDownloadStatus(DownloadStatus.STATUS_ERROR);
		for (DownloadListener l : mDownloadListeners) {
			LogUtil.v(TAG, "HttpDownloader notifyError() onError() length = "
					+ mDownloadListeners.size());
			l.onError(request);
		}
		if (request.getDownloadListener() != null) {
			LogUtil.v(TAG, "HttpDownloader notifyStart() onError() request");
			request.getDownloadListener().onError(request);
		}
	}

	/**
	 * 获取即将下载文件的实际大小
	 * 
	 * @param uri
	 * @return
	 */
	public static long getRemoteFileSize(String uri) {
		long size = -1;
		HttpClient httpClient = getHttpClient();
		HttpGet get = new HttpGet(uri);
		HttpResponse response = null;
		try {
			//response = SNInstrumentation.execute(httpClient, get);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpEntity entry = response.getEntity();
		size = entry.getContentLength();
		return size;
	}

	/**
	 * 获取httpClient
	 * 
	 * @return
	 */
	private static HttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}

	/**
	 * 睡眠当前线程
	 * 
	 * @param time
	 *            睡眠时间
	 */
	private void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建随机文件
	 * 
	 * @param size
	 *            文件大小
	 * @param uri
	 *            文件存放的路径
	 */
	private void createRandomFile(long size, String uri) {
		RandomAccessFile file = null;
		try {
			File f = new File(uri);
			file = new RandomAccessFile(f, "rwd");
			file.setLength(size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null)
					file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取下载器状态
	 * 
	 * @return
	 */
	public String getStatus() {
		return mDownloadRequest.getDownloadStatus();
	}

	/**
	 * 设置下载器状态
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
		mDownloadRequest.setDownloadStatus(status);
	}

	/**
	 * 设置是否已经连接重试
	 * 
	 * @param retred
	 */
	public void setRetred(boolean retred) {
		this.retred = retred;
	}

	/**
	 * 累计已下载大小
	 * 
	 * @param size
	 */
	synchronized protected void append(long size) {
		downloadSize += size;
		mDownloadRequest.setDownloadSize(downloadSize);
	}

	/**
	 * 更新指定线程最后下载的位置
	 * 
	 * @param threadId
	 *            线程id
	 * @param pos
	 *            最后下载的位置
	 */
	public void update(int threadId, Long pos) {
		cache.put(threadId, pos);
	}

	/**
	 * 是否取消下载
	 * 
	 * @return
	 */
	public boolean isCancel() {
		return isCancel;
	}

	/**
	 * 实时保存下载记录到数据库
	 */
	synchronized public void save() {
		ThreadModel threadModel = new ThreadModel(cache);
		String c = threadModel.toString();
		mDownloadRequest.setExtraValue(c);
		mDownloadRequest.setDownloadSize(downloadSize);
		String where = DownloadColumns._ID + "=" + mDownloadRequest.getId();
		databaseHelper.update(mDownloadRequest.toContentValues(), where, null);
	}

	/**
	 * 计算每条线程下载的长度
	 * 
	 * @param fileSize
	 * @param block
	 * @return
	 */
	private void computerBlock(long fileSize, long[] block) {
		for (int i = 0; i < block.length; i++) {
			block[i] = fileSize / block.length;
			if (i == block.length - 1 && fileSize % block.length != 0)
				block[i] = fileSize - (block[0] * (block.length - 1));
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		ThreadModel threadModel = new ThreadModel(
				mDownloadRequest.getExtraValue());
		cache = threadModel.getThreadSet();
		long size = getRemoteFileSize(mSrcUri);
		createRandomFile(size, mDestFile);
		computerBlock(size, mBlock);
		mDownloadRequest.setTotalSize(size);
	}

	/**
	 * 判断是否完成下载
	 * 
	 * @return
	 */
	public boolean isFinish() {
		if (downloadSize == mDownloadRequest.getTotalSize()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断下载线程是否已经全部结束，结束不代表下载完成，异常可能导致结束
	 * 
	 * @return
	 */
	public boolean isAllFinish() {
		for (DownloadThread t : thread) {
			if (!t.isFinish())
				return false;
		}
		return true;
	}

	public String getDownloadSrcUri() {
		return mSrcUri;
	}

	public void addDownloadListener(DownloadListener listener) {
		mDownloadListeners.add(listener);
	}

	public DownloadRequest getDownloadRequest() {
		return mDownloadRequest;
	}

	/**
	 * 检查下载子线程的状态如果没有结束并且没有出错则继续下载
	 */
	private void checkThread() {
		for (int i = 0; i < thread.length; i++) {
			if (!thread[i].isFinish() && !thread[i].isError()
					&& thread[i].getStatus() == Status.FINISH) {
				long startPos = i * mBlock[0];
				long endPos = startPos + mBlock[i] - 1;
				thread[i] = new DownloadThread(this, mSrcUri, new File(
						mDestFile), startPos, endPos, cache.get(i), i);
				thread[i].start();
			}
		}
	}

	@Override
	public void run() {
		doDownload();
	}
}
