package com.gehua.moban.common.http;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestBean {

	private String uri;
	
	private Map<String,String> params = new HashMap<String, String>();
	
	private HttpMethod method = HttpMethod.GET;
	
	private int timeout;
	
	private int currentRetry = 0;
	
	private String param;
	
	private Map<String,String> headers = new HashMap<String, String>();
	
	public int getCurrentRetry() {
		currentRetry++;
		return currentRetry;
	}
	
	public void setCurrentRetry(int currentRetry) {
		this.currentRetry = currentRetry;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public HttpMethod getMethod() {
		if(null == method){
			method = HttpMethod.GET;
		}
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public HttpRequestBean() {
	}

	public HttpRequestBean(String uri, Map<String, String> params, HttpMethod method,
			int timeout) {
		this.uri = uri;
		this.params = params;
		this.method = method;
		this.timeout = timeout;
	}

	public HttpRequestBean(String uri, Map<String, String> params, HttpMethod method) {
		this.uri = uri;
		this.params = params;
		this.method = method;
	}

	public HttpRequestBean(String uri, Map<String, String> params) {
		this.uri = uri;
		this.params = params;
	}

	public HttpRequestBean(String uri) {
		this.uri = uri;
	}
	
	/**
	 * uri不能为空
	 * @return
	 */
	public boolean notNullValidate(){
		return null != uri;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
}