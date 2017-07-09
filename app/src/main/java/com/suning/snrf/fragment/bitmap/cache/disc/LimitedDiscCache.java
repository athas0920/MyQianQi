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
package com.suning.snrf.fragment.bitmap.cache.disc;

import com.suning.snrf.fragment.bitmap.cache.disc.naming.FileNameGenerator;
import com.suning.snrf.fragment.bitmap.core.DefaultConfigurationFactory;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过一些参数被限制的磁盘缓存。
 * 如果缓存超过被限制的值那么最旧的文件将被删除
 *
 * @author Ryan
 * @see BaseDiscCache
 * @see FileNameGenerator
 * @since 1.0.0
 */
public abstract class LimitedDiscCache extends BaseDiscCache {

	private static final int INVALID_SIZE = -1;

	private final AtomicInteger cacheSize;

	private final int sizeLimit;

	private final Map<File, Long> lastUsageDates = Collections.synchronizedMap(new HashMap<File, Long>());

	/**
	 * @param cacheDir  文件缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param sizeLimit 缓存大小. 如果超过这个值那么最旧的文件将被删除 
	 */
	public LimitedDiscCache(File cacheDir, int sizeLimit) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), sizeLimit);
	}

	/**
	 * @param cacheDir          文件缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param fileNameGenerator 缓存文件名称生成器
	 * @param sizeLimit         缓存大小. 如果超过这个值那么最旧的文件将被删除 
	 */
	public LimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int sizeLimit) {
		super(cacheDir, fileNameGenerator);
		this.sizeLimit = sizeLimit;
		cacheSize = new AtomicInteger();
		calculateCacheSizeAndFillUsageMap();
	}

	private void calculateCacheSizeAndFillUsageMap() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int size = 0;
				File[] cachedFiles = cacheDir.listFiles();
				if (cachedFiles != null) { // rarely but it can happen, don't know why
					for (File cachedFile : cachedFiles) {
						size += getSize(cachedFile);
						lastUsageDates.put(cachedFile, cachedFile.lastModified());
					}
					cacheSize.set(size);
				}
			}
		}).start();
	}

	@Override
	public void put(String key, File file) {
		int valueSize = getSize(file);
		int curCacheSize = cacheSize.get();

		while (curCacheSize + valueSize > sizeLimit) {
			int freedSize = removeNext();
			if (freedSize == INVALID_SIZE) break; // 缓存为空 (没有可删的对象)
			curCacheSize = cacheSize.addAndGet(-freedSize);
		}
		cacheSize.addAndGet(valueSize);

		Long currentTime = System.currentTimeMillis();
		file.setLastModified(currentTime);
		lastUsageDates.put(file, currentTime);
	}

	@Override
	public File get(String key) {
		File file = super.get(key);

		Long currentTime = System.currentTimeMillis();
		file.setLastModified(currentTime);
		lastUsageDates.put(file, currentTime);

		return file;
	}

	@Override
	public void clear() {
		lastUsageDates.clear();
		cacheSize.set(0);
		super.clear();
	}

	/** 移除下个文件 并返回它的大小 */
	private int removeNext() {
		if (lastUsageDates.isEmpty()) {
			return INVALID_SIZE;
		}
		Long oldestUsage = null;
		File mostLongUsedFile = null;
		Set<Entry<File, Long>> entries = lastUsageDates.entrySet();
		synchronized (lastUsageDates) {
			for (Entry<File, Long> entry : entries) {
				if (mostLongUsedFile == null) {
					mostLongUsedFile = entry.getKey();
					oldestUsage = entry.getValue();
				} else {
					Long lastValueUsage = entry.getValue();
					if (lastValueUsage < oldestUsage) {
						oldestUsage = lastValueUsage;
						mostLongUsedFile = entry.getKey();
					}
				}
			}
		}

		int fileSize = 0;
		if (mostLongUsedFile != null) {
			if (mostLongUsedFile.exists()) {
				fileSize = getSize(mostLongUsedFile);
				if (mostLongUsedFile.delete()) {
					lastUsageDates.remove(mostLongUsedFile);
				}
			} else {
				lastUsageDates.remove(mostLongUsedFile);
			}
		}
		return fileSize;
	}

	protected abstract int getSize(File file);
}