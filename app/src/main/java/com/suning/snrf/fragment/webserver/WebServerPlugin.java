package com.suning.snrf.fragment.webserver;

import java.io.File;
import java.util.Map;

public interface WebServerPlugin {

    void initialize(Map<String, String> commandLineOptions);

    boolean canServeUri(String uri, File rootDir);

    NanoHTTPD.Response serveFile(String uri, Map<String, String> headers, File file, String mimeType);
}