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
package com.suning.snrf.fragment.bitmap.cache.disc.impl;

import com.suning.snrf.fragment.bitmap.cache.disc.LimitedDiscCache;
import com.suning.snrf.fragment.bitmap.cache.disc.naming.FileNameGenerator;
import com.suning.snrf.fragment.bitmap.core.DefaultConfigurationFactory;
import com.suning.snrf.fragment.bitmap.utils.L;

import java.io.File;

/**
 * 缓存合计限制的磁盘缓存. 如果超过缓存总数那么最旧的最后日期使用的文件将被移除
 *
 * @author Ryan
 * @see LimitedDiscCache
 * @since 1.0.0
 */
public class TotalSizeLimitedDiscCache extends LimitedDiscCache {

	private static final int MIN_NORMAL_CACHE_SIZE_IN_MB = 2;
	private static final int MIN_NORMAL_CACHE_SIZE = MIN_NORMAL_CACHE_SIZE_IN_MB * 1024 * 1024;

	/**
	 * @param cacheDir     缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param maxCacheSize 缓存目录的所缓存的最大值 (字节数). 如果超过这个值那么最旧的最后日期使用的文件将被移除
	 */
	public TotalSizeLimitedDiscCache(File cacheDir, int maxCacheSize) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), maxCacheSize);
	}

	/**
	 * @param cacheDir          缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param fileNameGenerator 缓存文件名称生成器
	 * @param maxCacheSize      缓存目录的所缓存的最大值 (字节数). 如果超过这个值那么最旧的最后日期使用的文件将被移除
	 */
	public TotalSizeLimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int maxCacheSize) {
		super(cacheDir, fileNameGenerator, maxCacheSize);
		if (maxCacheSize < MIN_NORMAL_CACHE_SIZE) {
			L.w("你设置的磁盘缓存太小  (小于  %1$d Mb)", MIN_NORMAL_CACHE_SIZE_IN_MB);
		}
	}

	@Override
	protected int getSize(File file) {
		return (int) file.length();
	}
}
