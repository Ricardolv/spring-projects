package com.richard.brewer.dto;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;


import com.richard.brewer.model.Origin;

public class BeerDTO {
	
	private Long code;
	private String sku;
	private String name;
	private String origin;
	private BigDecimal value;
	private String photo;
	
	public BeerDTO(Long code, String sku, String name, Origin origin, BigDecimal value, String photo) {
		this.code = code;
		this.sku = sku;
		this.name = name;
		this.origin = origin.getDescription();
		this.value = value;
		this.photo = StringUtils.isEmpty(photo) ? "beer-mock.png" : photo;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
