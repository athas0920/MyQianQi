package com.suning.snrf.fragment.webserver;

import com.suning.snrf.fragment.webserver.NanoHTTPD.Response;

import java.util.Map;


public class InternalRewrite extends Response {
    private final String uri;
    private final Map<String, String> headers;

    public InternalRewrite(Map<String, String> headers, String uri) {
        super(null);
        this.headers = headers;
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}