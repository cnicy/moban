package com.momix.sdk.net.http.api.impl;

import com.momix.sdk.net.http.api.MyHttp;
import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HttpURLConnection 默认实现
 * Created by rono on 2015/11/27.
 */
public class MyHttpURLConnection implements MyHttp{
    public MyHttpURLConnection() {
        System.out.println("http init......");
    }

    @Override
    public HttpResponseParam get(HttpRequestParams req) throws IOException{
        if(null==req)
            throw new IllegalArgumentException("requestParams can not be null!");

        // 构造url参数
        builderUrl(req);
        HttpURLConnection conn = null;
        try{
            URL geturl = new URL(req.getUri());
            conn = (HttpURLConnection)geturl.openConnection();
            Map<String,String> headers = req.getHeards();
            // 设置http头
            if(null!=headers && headers.size()>0){
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(req.getConnTimeOut());
            conn.setReadTimeout(req.getReadTimeOut());
            conn.connect();
            //
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            while(null != (line = reader.readLine())){
                builder.append(line);
            }
            reader.close();

            HttpResponseParam httpResponse = new HttpResponseParam();
            httpResponse.setStatusCode(conn.getResponseCode());
            httpResponse.setStatusMsg(conn.getResponseMessage());
            if(builder.length()>0)
                httpResponse.setContent(builder.toString());

            return httpResponse;
        }finally{
            if(null != conn){
                conn.disconnect();
            }
        }
    }

    @Override
    public HttpResponseParam post(HttpRequestParams req)throws IOException {
        if(null==req)
            throw new IllegalArgumentException("requestParams can not be null!");

        // 构造url参数
        builderUrl(req);
        HttpURLConnection conn = null;
        try{
            URL geturl = new URL(req.getUri());
            conn = (HttpURLConnection)geturl.openConnection();
            Map<String,String> headers = req.getHeards();
            // 设置http头
            if(null!=headers && headers.size()>0){
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key));
                }
            }
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(req.getConnTimeOut());
            conn.setReadTimeout(req.getReadTimeOut());
            if(null!=req.getRequestParams()){
                conn.setDoOutput(true);
                byte[] bytes = req.getRequestParams().getBytes("UTF-8");
                conn.getOutputStream().write(bytes);// 输入参数
            }
            conn.connect();
            //
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line = null;
            while(null != (line = reader.readLine())){
                builder.append(line);
            }
            reader.close();

            HttpResponseParam httpResponse = new HttpResponseParam();
            httpResponse.setStatusCode(conn.getResponseCode());
            httpResponse.setStatusMsg(conn.getResponseMessage());
            if(builder.length()>0)
                httpResponse.setContent(builder.toString());

            return httpResponse;
        }finally{
            if(null != conn){
                conn.disconnect();
            }
        }
    }

    private HttpRequestParams builderUrl(HttpRequestParams requestParams){
        Map<String, String> params =  requestParams.getUrlParams();
        String url = requestParams.getUri();
        if(null!=params && params.size()>0){
            StringBuilder builder = new StringBuilder(url).append("?");
            String key;
            String value;
            boolean first = true;
            for(Map.Entry<String, String> entry : params.entrySet()){
                if(first == false){
                    builder.append('&');
                }else{
                    first = false;
                }
                key = entry.getKey();
                value = entry.getValue();
                builder.append(key);
                builder.append('=');
                builder.append(value);
            }
            requestParams.setUri(builder.toString());
        }
        return requestParams;
    }
}
