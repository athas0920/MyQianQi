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

/**
 * 下载状态的监听器
 * <p>
 * <strong>注意：该接口方法有可能在一个新线程中被调用</strong>
 * 
 * @version 1.0.0
 * @author Yan.Sairong
 */
public interface DownloadListener {
	/**
	 * 当一个在等待中的任务开始下载的时候被调用
	 * 注意：不能直接在该方法中更新UI
	 * 
	 * @param request DownloadRequest对象
	 */
	void onStart(DownloadRequest request);

	/**
	 * 当下载进度改变是被调用
	 * 
	 * @param request DownloadRequest对象
	 */
	void onProgress(DownloadRequest request);

	/**
	 * 当下载任务出错失败时被调用
	 * 注意：不能直接在该方法中更新UI
	 * 
	 * @param request DownloadRequest对象
	 */
	void onError(DownloadRequest request);

	/**
	 * 当下载任务完成时被调用
	 * 注意：不能直接在该方法中更新UI
	 * 
	 * @param request DownloadRequest对象
	 */
	void onComplete(DownloadRequest request);

	/**
	 * 当下载任务被暂停时被调用
	 * 注意：不能直接在该方法中更新UI
	 * 
	 * @param request DownloadRequest对象
	 */
	void onPause(DownloadRequest mDownloadRequest);

}
