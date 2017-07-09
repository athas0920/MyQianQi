package com.suning.snrf.fragment;

import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class FinalAdapter extends BaseAdapter {
	
	
	private List mData;
	
	public FinalAdapter(List data) {
		mData = data;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}
	
	/**
	 * listView局部更新方法
	 * 
	 * @param listView
	 * @param index 需要更新列表位置
	 * @param object
	 */
	public abstract void updateItemView(ListView listView, int index, Object object);
}
