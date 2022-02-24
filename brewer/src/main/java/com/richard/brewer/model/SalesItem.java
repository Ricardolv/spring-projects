package com.richard.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sale_item")
public class SalesItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	private Integer quantity;

	@Column(name = "unitary_value")
	private BigDecimal unitaryValue;

	@ManyToOne
	@JoinColumn(name = "code_beer")
	private Beer beer;

	@ManyToOne
	@JoinColumn(name = "code_sale")
	private Sale sale;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(BigDecimal unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public Beer getBeer() {
		return beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	/** 
     * Business 
     */
	public BigDecimal getTotalValue() {
		return this.unitaryValue.multiply(new BigDecimal(this.quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesItem other = (SalesItem) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
