package com.richard.brewer.dto;

import java.math.BigDecimal;

public class StockValueItems {
	
	private BigDecimal value;
	private Long totalItems;
	
	public StockValueItems() {
	}
	
	public StockValueItems(BigDecimal value, Long totalItems) {
		this.value = value;
		this.totalItems = totalItems;
	}

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}
	

}
