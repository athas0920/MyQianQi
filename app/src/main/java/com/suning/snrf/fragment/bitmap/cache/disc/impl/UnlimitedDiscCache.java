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
import com.suning.snrf.fragment.bitmap.cache.disc.DiscCacheAware;
import com.suning.snrf.fragment.bitmap.cache.disc.naming.FileNameGenerator;
import com.suning.snrf.fragment.bitmap.core.DefaultConfigurationFactory;

import java.io.File;

/**
 * 磁盘缓存默认的实现 {@linkplain DiscCacheAware disc cache}. 缓存大小未被限制.
 *
 * @author Ryan
 * @see BaseDiscCache
 * @since 1.0.0
 */
public class UnlimitedDiscCache extends BaseDiscCache {

	/** @param 缓存文件目录 */
	public UnlimitedDiscCache(File cacheDir) {
		this(cacheDir, DefaultConfigurationFactory.createFileNameGenerator());
	}

	/**
	 * @param cacheDir          缓存文件目录
	 * @param fileNameGenerator 文件名称生成器
	 */
	public UnlimitedDiscCache(File cacheDir, FileNameGenerator fileNameGenerator) {
		super(cacheDir, fileNameGenerator);
	}

	@Override
	public void put(String key, File file) {
		// Do nothing
	}
}
