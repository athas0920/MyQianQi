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
package com.suning.snrf.fragment.bitmap.cache.memory;

import java.util.Collection;

/**
 * 内存缓存接口
 *
 * @author Ryan
 * @since 1.0.0
 */
public interface MemoryCacheAware<K, V> {
	/**
	 * 通过key存放值
	 *
	 * @return <b>true</b> - 如果值成功存放, <b>false</b> - 如果值没有被存放进缓存
	 */
	boolean put(K key, V value);

	/** 通过key返回值，如果没有这个key则返回null */
	V get(K key);

	/** 通过key移除一个元素 */
	void remove(K key);

	/** 返回所有缓存中的key */
	Collection<K> keys();

	/** 清除所有缓存中的元素 */
	void clear();
}
