package com.richard.brewer.model;

public enum SaleStatus {
	
	BUDGET("Orçamento"), //Orçamento
	ISSUED("Emitida"), //Emitida
	CANCELED("Cancelada"); //Cancelada

	private String description;

	SaleStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
