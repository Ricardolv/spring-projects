package com.richard.brewer.service.event.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.richard.brewer.model.Beer;
import com.richard.brewer.model.SalesItem;
import com.richard.brewer.repository.Beers;

@Component
public class SaleListener {
	
	@Autowired
	private Beers beers;
	
	@EventListener
	public void saleIssued(SaleEvent saleEvent) {
		for(SalesItem item : saleEvent.getSale().getItems()) {
			Beer beer = beers.getOne(item.getBeer().getCode());
			beer.setQuantityStock(beer.getQuantityStock() - item.getQuantity());
			beers.save(beer);
		}
	}

}
