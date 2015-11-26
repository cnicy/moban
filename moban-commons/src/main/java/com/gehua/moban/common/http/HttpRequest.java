package com.gehua.moban.common.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpRequest {
	private static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
	public HttpResponseBean execute(HttpRequestBean requestBean)throws Exception{
		if(null == requestBean){
			throw new NullPointerException("the requestBean must not be null.");
		}
		if(requestBean.notNullValidate()){
			int timeout = requestBean.getTimeout();
			String uri = requestBean.getUri();
			HttpMethod method = requestBean.getMethod();
			Map<String,String> params = requestBean.getParams();
			
			if(requestBean.getMethod()==HttpMethod.GET)
				return _execute(uri,getParams(params),method,timeout,requestBean.getHeaders());
			else if(requestBean.getMethod()==HttpMethod.POST)
				return _execute(uri,requestBean.getParam(),method,timeout,requestBean.getHeaders());
		}else{
			throw new NullPointerException("the requestBean parameters must not be null.");
		}
		return null;
	}
	
	/**
	 * 获得完成的uri地址(主要是get方法，参数以key1=value1&key2=value2呈现)
	 * @param params
	 * @return
	 */
	private String getParams(Map<String,String> params){
		StringBuilder builder = new StringBuilder(50);
		if(null != params && params.size() > 0){
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
		}
		return builder.toString();
	}
	
	private HttpResponseBean _execute (String uri,String params,HttpMethod method,int timeout,Map<String,String> headers)throws Exception {
		StringBuilder builder = new StringBuilder();
		HttpResponseBean res = new HttpResponseBean();
		HttpURLConnection conn = null;
		logger.info(String.format("REQ>> uri:{%s} params:{%s} method:{%s} timeout:{%s}", uri,params,method.getValue(),timeout));
		try {
			switch (method) {
			case GET:
				URL geturl = new URL(uri+((null!=params && params.length()>0)?"?"+params:""));
				conn = (HttpURLConnection)geturl.openConnection();
				// 设置http头
				if(null!=headers && headers.size()>0){
					 for (String key : headers.keySet()) {
						 conn.setRequestProperty(key, headers.get(key));
					 }
				}
				break;
			case POST:
				URL posturl = new URL(uri);
				conn = (HttpURLConnection)posturl.openConnection();
				// 设置http头
				if(null!=headers && headers.size()>0){
					 for (String key : headers.keySet()) {
						 conn.setRequestProperty(key, headers.get(key));
					 }
				}
				conn.setDoOutput(true);
				byte[] bytes = params.getBytes("UTF-8");
		        conn.getOutputStream().write(bytes);// 输入参数
				break;
			default:
				break;
			}
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = null;
			while(null != (line = reader.readLine())){
				builder.append(line);
			}
			reader.close();
			res.setCode(conn.getResponseCode());
			res.setMsg(conn.getResponseMessage());
		} finally{
			if(null != conn){
				conn.disconnect();
			}
		}
		String resStr = builder.toString();
		res.setResponse(resStr);
		logger.info("RES:"+resStr);
		return res;
	}
	public HttpResponseBean execute1(HttpRequestBean requestBean)throws Exception{
		if(null == requestBean){
			throw new NullPointerException("the requestBean must not be null.");
		}
		if(requestBean.notNullValidate()){
			int timeout = requestBean.getTimeout();
			String uri = requestBean.getUri();
			HttpMethod method = requestBean.getMethod();
			Map<String,String> params = requestBean.getParams();
			HttpResponseBean response = new HttpResponseBean();
			if(requestBean.getMethod()==HttpMethod.GET)
				response.setContent(_execute1(uri,getParams(params),method,timeout));
			else if(requestBean.getMethod()==HttpMethod.POST)
				response.setContent(_execute1(uri,requestBean.getParam(),method,timeout));
				
			return response;
		}else{
			throw new NullPointerException("the requestBean parameters must not be null.");
		}
	}
	private byte[] _execute1 (String uri,String params,HttpMethod method,int timeout)throws Exception {
		logger.info(String.format("REQ>> uri:{} params:{} method:{} timeout:{}", uri,params,method.getValue(),timeout));
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayOutputStream swapStream = null;
		try {
			switch (method) {
				case GET:
					URL geturl = new URL(uri+'?'+params);
					conn = (HttpURLConnection)geturl.openConnection();
					break;
				case POST:
					URL posturl = new URL(uri);
					conn = (HttpURLConnection)posturl.openConnection();
					conn.setDoOutput(true);
					byte[] bytes = params.toString().getBytes();
			        conn.getOutputStream().write(bytes);// 输入参数
					break;
				default:
					break;
			}
			conn.setConnectTimeout(timeout);
			conn.setReadTimeout(timeout);
			conn.connect();
			
		/*	is =conn.getInputStream();
			FileOutputStream out = new FileOutputStream(new File("D:\\2.jpg"));
			int b = 0;
			byte[] buffer = new byte[1024];
			while(-1 !=(b = is.read())){
				out.write(buffer, 0,b);
			}
			out.close();*/
			is =conn.getInputStream();
			swapStream = new ByteArrayOutputStream();
			
			byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = is.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        } 
	        return swapStream.toByteArray();
		} finally{
			if(null!=swapStream)
				swapStream.close();
			if(null!=is)
				is.close();
			if(null != conn)
				conn.disconnect();
		}
	}
}