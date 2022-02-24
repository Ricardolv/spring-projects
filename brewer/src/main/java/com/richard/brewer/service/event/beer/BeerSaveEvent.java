package com.richard.brewer.service.event.beer;

import org.springframework.util.StringUtils;

import com.richard.brewer.model.Beer;

public class BeerSaveEvent {
	
	private Beer beer;

	public BeerSaveEvent(Beer beer) {
		this.beer = beer;
	}

	public Beer getBeer() {
		return beer;
	}
	
	public boolean isHavePhoto() {
		return !isNotHavePhoto();
	}
	
	public boolean isNewPhoto() {
		return this.beer.isNewPhoto();
	}
	
	private boolean isNotHavePhoto() {
		return StringUtils.isEmpty(this.beer.getPhoto());
	}
	
	

}
