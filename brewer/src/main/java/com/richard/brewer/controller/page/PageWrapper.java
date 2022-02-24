package com.richard.brewer.controller.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

public class PageWrapper<T> {
	
	private Page<T> page;
	private UriComponentsBuilder uriBuilder;

	public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
		this.page = page;
		
		String httpUrl = httpServletRequest.getRequestURL().append(
				null != httpServletRequest.getQueryString() ? "?" + httpServletRequest.getQueryString() : "")
				.toString().replaceAll("\\+", "%20").replace("excluded", "");
		
		this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(httpUrl);
	}
	
	public List<T> getContent() {
		return this.page.getContent();
	}
	
	public boolean isEmpty() {
		return this.page.getContent().isEmpty();
	}
	
	public int getCurrent() {
		return this.page.getNumber();
	}
	
	public boolean isFirst() {
		return this.page.isFirst();
	}
	
	public boolean isLast() {
		return this.page.isLast();
	}
	
	public int getTotal() {
		return this.page.getTotalPages();
	}
	
	public String urlForPage(int page) {
		return this.uriBuilder.replaceQueryParam("page", page).build(true).encode().toUriString();
	}
	
	public String urlOrdered(String property) {
		UriComponentsBuilder uriBuilderOrder = UriComponentsBuilder
				.fromUriString(this.uriBuilder.build(true).encode().toUriString());
		
		String valueSort = String.format("%s,%s", property, invertDirection(property)); 
		
		return uriBuilderOrder.replaceQueryParam("sort", valueSort).build(true).encode().toUriString();
	}
	
	public String invertDirection(String property) {
		String direction = "asc";
		
		Order order = getOrderOrNull(property);
		
		if (null != order) {
			direction = Sort.Direction.ASC.equals(order.getDirection()) ? "desc" : "asc"; 
		}
		return direction;
	}
	
	public boolean downward(String property) {
		return invertDirection(property).equals("asc");
	}
	
	public boolean ordered(String property) {
		
		Order order = getOrderOrNull(property);
		
		if (null == order) {
			return false;
		}
		
		return null != this.page.getSort().getOrderFor(property) ? true : false;
	}
	
	public Order getOrderOrNull(String property) {
		return null != this.page.getSort() ? this.page.getSort().getOrderFor(property) : null;
	}

}
