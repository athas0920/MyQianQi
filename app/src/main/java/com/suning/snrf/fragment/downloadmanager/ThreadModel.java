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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p><b>线程模型</b></p>
 * 用户记录多线程下载时的各线程下载的实时信息
 * 
 * @version 1.0.0
 * @author Yan.Sairong
 *
 */
@SuppressWarnings("serial")
public class ThreadModel implements Serializable {
	private ConcurrentHashMap<Integer, Long> threadSet;

	public ConcurrentHashMap<Integer, Long> getThreadSet() {
		return threadSet;
	}

	public ThreadModel setThreadSet(ConcurrentHashMap<Integer, Long> threadSet) {
		this.threadSet = threadSet;
		return this;
	}
	
	public ThreadModel(ConcurrentHashMap<Integer, Long> threadSet) {
		this.threadSet = threadSet;
	}
	
	@SuppressWarnings("unchecked")
	public ThreadModel(String json){
		try {
			JSONObject jsonObject = new JSONObject(json);
			JSONObject thread = jsonObject.getJSONObject("thread");
			threadSet = new ConcurrentHashMap<Integer, Long>();
			Iterator<String> keys = thread.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				threadSet.put(Integer.valueOf(key), Long.valueOf(thread.getString(key)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		Set<Integer> keys = threadSet.keySet();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"" + "thread" + "\"" + ":" + "{");
		for(int key : keys){
			sb.append("\"" + key + "\"" + ":" + "\"" + threadSet.get(key) + "\"" + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}}");
		return sb.toString();
	}
	
}
