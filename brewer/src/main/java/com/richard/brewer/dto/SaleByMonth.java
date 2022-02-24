package com.richard.brewer.dto;

public class SaleByMonth {
	
	private String month;
	private Integer total;

	public SaleByMonth() {
	}
	
	public SaleByMonth(String month) {
		this.month = month;
	}

	public SaleByMonth(String month, Integer total) {
		this.month = month;
		this.total = total;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	
}
