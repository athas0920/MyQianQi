package com.suning.snrf.fragment.downloadmanager;
/**
 * 线程池配置类
 * 
 * @version 1.0.0 
 * @author Yan.Sairong(Ryan)
 *
 */
public class Config {
	public static int POOL_SIZE = 2;
	public static int DOWNLOAD_THREADS = 3;
	
	public static void setPoolSize(int poolSize){
		POOL_SIZE = poolSize;
	}
	
	public static void setDownloadThreads(int threadNum){
		DOWNLOAD_THREADS = threadNum;
	}
	
	public static int getPoolSize(){
		return POOL_SIZE;
	}
	
	public static int getDownloadThreads(){
		return DOWNLOAD_THREADS;
	}
}
