package com.gehua.moban.controller.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
public class SelfHandlerInterceptorAdapter extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SelfHandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("===========HandlerInterceptor1 preHandle");
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//System.out.println("===========HandlerInterceptor1 postHandle");
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("===========HandlerInterceptor1 afterCompletion");
	}

/*	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("[系统]IP("+getIpAddr(request)+") 访问了系统地址:"+request.getRequestURL()+(null==request.getQueryString()?"":"?"+request.getQueryString()));
		return super.preHandle(request, response, handler);
	}
	public static String getIpAddr(HttpServletRequest request) throws Exception {  
	    if (request == null) {  
	        return "";
	    }  
	    String ipString = request.getHeader("x-forwarded-for");  
	    if (null!=ipString || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (null!=ipString || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (null!=ipString || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getRemoteAddr();  
	    }  
	  
	    // 多个路由时，取第一个非unknown的ip  
	    final String[] arr = ipString.split(",");  
	    for (final String str : arr) {  
	        if (!"unknown".equalsIgnoreCase(str)) {  
	            ipString = str;  
	            break;  
	        }  
	    }  
	    return ipString;  
	} */
}