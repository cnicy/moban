package com.momix.sdk.net.http.api;

import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;

import java.io.IOException;

/**
 * Created by rono on 2015/11/27.
 */
public interface SdkHttp {
    /**
     * GET 请求
     * @param requestParams
     * @return
     */
    public HttpResponseParam get(HttpRequestParams requestParams) throws IOException;

    /**
     * POST请求
     * @param requestParams
     * @return
     */
    public HttpResponseParam post(HttpRequestParams requestParams)throws IOException;
}
