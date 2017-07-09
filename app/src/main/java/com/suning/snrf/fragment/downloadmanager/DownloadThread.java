/**
 * Copyright 2011-2013
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suning.snrf.fragment.downloadmanager;

import java.io.File;

/**
 * <b>下载线程
 * @version 1.0.0
 * @author Yan.Sairong
 */
public class DownloadThread extends Thread {
    private File saveFile;
    private String downUrl;
    private long block;
    private long startPos;
    private long endPos;
    private int threadId = -1;
    private long downLength;
    private boolean finish = false;
    private Status state = Status.RUNING;
    /**
     // * @see {@link }
     */
    private HttpDownloader downloader;
    /**
     * 下载线程是否出错
     * @note 下载线程完成了下载但是在关闭流的时候出错，不属于下载出错
     */
    private boolean isError = false;

    /**
     * @param downloader <a>HttpDownloader</a>
     * @param downUrl 下载地址
     * @param saveFile 保存的文件
     * @param startPos 下载线程从startPos开始下载
     * @param endPos 下载线程下载至endPos
     * @param downLength 已经下载的长度
     * @param tid 线程ID
     */
    public DownloadThread(HttpDownloader downloader, String downUrl, File saveFile, long startPos, long endPos, long downLength, int tid) {
        this.downUrl = downUrl;
        this.saveFile = saveFile;
        this.startPos = startPos;
        this.endPos = endPos;
        this.downloader = downloader;
        this.threadId = tid;
        this.downLength = downLength;
        this.block = endPos - startPos + 1;
    }

    @Override
    public void run() {
        doDownload();
    }

    private void doDownload() {

    }

    /**
     * 下载是否完成
     * @return
     */
    public boolean isFinish() {
        return finish;
    }

    /**
     * 已经下载的内容大小
     * @return 如果返回值为-1,代表下载失败
     */
    public long getDownLength() {
        return downLength;
    }

    /**
     * 下载线程是否发生错误
     * @return
     */
    public boolean isError() {
        return isError;
    }

    public Status getStatus() {
        return state;
    }

    public enum Status {RUNING, FINISH}
}
