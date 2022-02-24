package com.richard.brewer.service.event.sale;

import com.richard.brewer.model.Sale;

public class SaleEvent {
	
	private Sale sale;

	public SaleEvent(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {
		return sale;
	}

}
