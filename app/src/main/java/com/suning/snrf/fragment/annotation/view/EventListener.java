package com.suning.snrf.fragment.annotation.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.suning.snrf.fragment.exception.ViewException;

import java.lang.reflect.Method;

public class EventListener implements OnClickListener, OnItemClickListener,
		OnItemLongClickListener, OnItemSelectedListener, OnLongClickListener {

	private Object handler;

	private String clickMethod;
	private String longClickMethod;
	private String itemClickMethod;
	private String itemSelectMethod;
	private String nothingSelectedMethod;
	private String itemLongClickMehtod;
	
	public EventListener(Object handler) {
		this.handler = handler;
	}
	
	public EventListener click(String method){
		this.clickMethod = method;
		return this;
	}

	public EventListener longClick(String method){
		this.longClickMethod = method;
		return this;
	}

	public EventListener itemLongClick(String method){
		this.itemLongClickMehtod = method;
		return this;
	}

	public EventListener itemClick(String method){
		this.itemClickMethod = method;
		return this;
	}

	public EventListener select(String method){
		this.itemSelectMethod = method;
		return this;
	}

	public EventListener noSelect(String method){
		this.nothingSelectedMethod = method;
		return this;
	}
	
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return invokeLongClickMethod(handler, longClickMethod, v);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		invokeItemSelectMethod(handler, itemSelectMethod, arg1, arg2, arg3);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		invokeNoSelectMethod(handler, nothingSelectedMethod, arg0);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return invokeItemLongClickMethod(handler, itemLongClickMehtod, arg1, arg2, arg3);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		invokeItemClickMethod(handler, itemClickMethod, arg1, arg2, arg3);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		invokeClickMethod(handler, clickMethod, v);
	}
	
	private static Object invokeClickMethod(Object handler, String methodName,  Object... params){
		if(handler == null) return null;
		Method method = null;
		try{   
			method = handler.getClass().getDeclaredMethod(methodName,View.class);
			if(method!=null)
				return method.invoke(handler, params);	
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;

	}


	private static boolean invokeLongClickMethod(Object handler, String methodName,  Object... params){
		if(handler == null) return false;
		Method method = null;
		try{   
			//public boolean onLongClick(View v)
			method = handler.getClass().getDeclaredMethod(methodName,View.class);
			if(method!=null){
				Object obj = method.invoke(handler, params);
				return obj==null?false:Boolean.valueOf(obj.toString());	
			}
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return false;

	}



	private static Object invokeItemClickMethod(Object handler, String methodName,  Object... params){
		if(handler == null) return null;
		Method method = null;
		try{   
			///onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
			if(method!=null)
				return method.invoke(handler, params);	
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}


	private static boolean invokeItemLongClickMethod(Object handler, String methodName,  Object... params){
		if(handler == null) throw new ViewException("invokeItemLongClickMethod: handler is null :");
		Method method = null;
		try{   
			///onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
			if(method!=null){
				Object obj = method.invoke(handler, params);
				return Boolean.valueOf(obj==null?false:Boolean.valueOf(obj.toString()));	
			}
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return false;
	}


	private static Object invokeItemSelectMethod(Object handler, String methodName,  Object... params){
		if(handler == null) return null;
		Method method = null;
		try{   
			///onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class,View.class,int.class,long.class);
			if(method!=null)
				return method.invoke(handler, params);	
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	private static Object invokeNoSelectMethod(Object handler, String methodName,  Object... params){
		if(handler == null) return null;
		Method method = null;
		try{   
			//onNothingSelected(AdapterView<?> arg0)
			method = handler.getClass().getDeclaredMethod(methodName,AdapterView.class);
			if(method!=null)
				return method.invoke(handler, params);	
			else
				throw new ViewException("no such method:"+methodName);
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

}
