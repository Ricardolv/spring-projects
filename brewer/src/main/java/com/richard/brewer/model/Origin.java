package com.richard.brewer.model;

public enum Origin {
	
	NATIONAL("Nacional"),
	INTERNATIONAL("Internacional");
	
	private String description;

	private Origin(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
