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

import java.io.File;

/**
 * 文件数量限制的磁盘缓存. 如何超过指定的值那么最旧的最后日期使用的文件将被删除
 *
 * @author Ryan
 * @see LimitedDiscCache
 * @since 1.0.0
 */
public class FileCountLimitedDiscCache extends LimitedDiscCache {
	/**
	 * @param cacheDir     文件缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param maxFileCount 缓存的最大文件数. 如何超过这个值那么最旧的最后日期使用的文件将被删除
	 */
	public FileCountLimitedDiscCache(File cacheDir, int maxFileCount) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator(), maxFileCount);
	}

	/**
	 * @param cacheDir          文件缓存目录. <b>注意:</b> 指定单独的文件夹缓存. 这是正确缓存所必须的操作
	 * @param fileNameGenerator 缓存文件名称生成器
	 * @param maxFileCount      缓存的最大文件数. 如何超过这个值那么最旧的最后日期使用的文件将被删除
	 */
	public FileCountLimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator, int maxFileCount) {
		super(cacheDir, fileNameGenerator, maxFileCount);
	}

	@Override
	protected int getSize(File file) {
		return 1;
	}
}
