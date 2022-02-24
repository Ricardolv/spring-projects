package com.richard.brewer.service.event.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.richard.brewer.storage.PhotoStorage;

@Component
public class BeerListener {
	
	@Autowired
	private PhotoStorage photoStorage;
	
	@EventListener(condition = "#event.isHavePhoto() and #event.isNewPhoto()")
	public void beersave(BeerSaveEvent event) {
		photoStorage.save(event.getBeer().getPhoto());
	}

}
