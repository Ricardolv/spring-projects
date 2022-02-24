package com.richard.brewer.model;

public enum Flavor {
	
	SWEETENED("Adocicada"), //Adocicada
	BITTER("Amarga"), //Amarga
	STRONG("Forte"), //Forte
	FRUITY("Frutada"), //Frutada
	SOFT("Suave"); //Suave
	
	private String description;

	private Flavor(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
