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

import android.provider.BaseColumns;

/**
 * 数据库列信息
 * 
 * @author Yan.Sairong
 */
public final class DownloadColumns implements BaseColumns {
	/**
	 * 预留的唯一性字段
	 */
	public static final String UUID = "uuid";	
	/**
	 * 下载地址
	 */
	public static final String SRC_URI = "src_url";
	/**
	 * 文件下载后保存的路径
	 */
	public static final String DEST_URI = "dest_url";
	/**
	 * 文件名可以为空，也可以用于存放其他信息
	 */
	public static final String TITLE = "title";
	/**
	 * 是否支持断点下载
	 * 0 不支持， 1 支持
	 */
	public static final String SUPPORT_CONTINUE = "support_continue";
	/**
	 * 文件总大小
	 */
	public static final String TOTAL_SIZE = "total_size";
	/**
	 * 已经下载的文件长度
	 * 当调用pause方法后会记录当前下载的长度
	 */
	public static final String DOWNLOAD_SIZE = "download_size";
	/**
	 * 下载状态.
	 * 例如： IDLE, START, PAUSE, COMPLETE, ERROR.
	 */
	public static final String DOWNLOAD_STATUS = "status";
	/**
	 * 下载完成的时间戳，也可以用来记录下载开始的时间戳，可选
	 */
	public static final String TIMESTAMP = "timestamp";
	/**
	 * 预留字段
	 * 多线程下载时用来存放，各线程下载的信息，例如子线程已下载的长度
	 */
	public static final String EXTRA_VALUE = "extra_value";

}
