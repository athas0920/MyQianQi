package com.suning.snrf.fragment.webserver;

public interface WebServerPluginInfo {
    String[] getMimeTypes();

    String[] getIndexFilesForMimeType(String mime);

    WebServerPlugin getWebServerPlugin(String mimeType);
}