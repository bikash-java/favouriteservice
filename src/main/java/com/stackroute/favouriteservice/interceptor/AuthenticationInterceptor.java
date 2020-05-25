package com.stackroute.favouriteservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stackroute.favouriteservice.bean.AuthToken;
import com.stackroute.favouriteservice.exception.AuthorizationException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	@CrossOrigin
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthorizationException {

		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization",request.getHeader("Authorization"));  
		
		System.out.println("Token : "+request.getHeader("Access-Control-Request-Method"));

		HttpEntity<AuthToken> requestEntity = new HttpEntity<AuthToken>(new AuthToken(false,null),headers);

		System.out.println("Rest Call hitting : http://user-service:9002/auth/isAuthenticated");
		
		ResponseEntity<AuthToken> responseEntity = restTemplate.postForEntity("http://localhost:9002/auth/isAuthenticated", requestEntity, AuthToken.class);
		
		if(responseEntity.getBody().isAuthenticated() || checkIfPreflight(request,response)) {
			return true;
		}else {
			throw new AuthorizationException();
		}
		
		//return true;
		
	}
	
	private boolean checkIfPreflight(HttpServletRequest request, HttpServletResponse response) {
		
		if(request.getHeader("Access-Control-Request-Method")!= null && request.getHeader("Origin") != null && request.getMethod().equals("OPTIONS")) {
			return true;
		}else {
			return false;
		}
		
	}

}
