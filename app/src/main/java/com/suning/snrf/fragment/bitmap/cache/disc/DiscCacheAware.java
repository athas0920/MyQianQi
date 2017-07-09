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

import java.io.File;

/**
 * 磁盘缓存接口
 *
 * @author Ryan
 * @since 1.0.0
 */
public interface DiscCacheAware {
	/**
	 * 事实上该方法并不能把文件保存在文件系统中。
	 * 它在图像被缓存到缓存目录后被调用并且在内存中被解码成bitmap。
	 * 这些次序使防止在文件尝试解码成bitmap之前被缓存到磁盘之后中间可能产生的一些误删操作
	 */
	void put(String key, File file);

	/**
	 * 根据传入的key返回合适的  {@linkplain File file object}.<br />
	 * <b>注意:</b> 不能返回空. 不管文件存不存在，该方法必须返回一个 {@linkplain File file object}
	 */
	File get(String key);

	/** 清除缓存目录 */
	void clear();
}
