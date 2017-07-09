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
 * 下载状态
 * @version 1.0.0
 * @author Yan.Sairong
 *
 */
public class DownloadStatus {
	/**
	 * 下载任务处于挂起状态
	 */
	public static final String STATUS_IDLE = "status_idle";
	/**
	 * 下载任务处于开始状态
	 */
	public static final String STATUS_START = "status_start";
	/**
	 * 下载任务处于暂停状态
	 */
	public static final String STATUS_PAUSE = "status_pause";
	/**
	 * 下载任务处于结束状态
	 */
	public static final String STATUS_COMPLETE = "status_complete";
	/**
	 * 下载任务时出错
	 * 例如：网络错误，io读写错误等
	 */
	public static final String STATUS_ERROR = "status_error";
	/**
	 * 下载任务被删除
	 */
	public static final String STATUS_DELETE = "status_delete";
}
