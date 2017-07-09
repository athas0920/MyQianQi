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

import com.suning.snrf.fragment.bitmap.cache.disc.BaseDiscCache;
import com.suning.snrf.fragment.bitmap.cache.disc.naming.FileNameGenerator;
import com.suning.snrf.fragment.bitmap.core.DefaultConfigurationFactory;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载时间限制的缓存. 缓存大小未设置.
 *
 * @author Ryan
 * @see BaseDiscCache
 * @since 1.3.1
 */
public class LimitedAgeDiscCache extends BaseDiscCache {

	private final long maxFileAge;

	private final Map<File, Long> loadingDates = Collections.synchronizedMap(new HashMap<File, Long>());

	/**
	 * @param cacheDir 缓存目录
	 * @param maxAge   文件缓存所存在的的最大时间(毫秒). 如果文件缓存存在的时间超过所指定的最大值，那么下个时刻会被移除即在重新加载之前
	 */
	public LimitedAgeDiscCache(File cacheDir, long maxAge) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), maxAge);
	}

	/**
	 * @param cacheDir          缓存目录
	 * @param fileNameGenerator 文件名生成器
	 * @param maxAge            文件缓存所存在的的最大时间(毫秒). 如果文件缓存存在的时间超过所指定的最大值，那么下个时刻会被移除即在重新加载之前
	 */
	public LimitedAgeDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, long maxAge) {
		super(cacheDir, fileNameGenerator);
		this.maxFileAge = maxAge * 1000; // to milliseconds
	}

	@Override
	public void put(String key, File file) {
		long currentTime = System.currentTimeMillis();
		file.setLastModified(currentTime);
		loadingDates.put(file, currentTime);
	}

	@Override
	public File get(String key) {
		File file = super.get(key);
		if (file.exists()) {
			boolean cached;
			Long loadingDate = loadingDates.get(file);
			if (loadingDate == null) {
				cached = false;
				loadingDate = file.lastModified();
			} else {
				cached = true;
			}

			if (System.currentTimeMillis() - loadingDate > maxFileAge) {
				file.delete();
				loadingDates.remove(file);
			} else if (!cached) {
				loadingDates.put(file, loadingDate);
			}
		}
		return file;
	}
}