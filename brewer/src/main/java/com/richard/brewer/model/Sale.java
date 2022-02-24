package com.richard.brewer.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "sale")
@DynamicUpdate
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	
	@Column(name = "freight_value")
	private BigDecimal freightValue;
	
	@Column(name = "discount_value")
	private BigDecimal discountValue;
	
	@Column(name = "total_value")
	private BigDecimal totalValue = BigDecimal.ZERO;
	
	private String note;
	
	@Column(name = "delivery_hour_date")
	private LocalDateTime deliveryHourDate;
	
	@ManyToOne
	@JoinColumn(name = "code_client")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "code_user")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private SaleStatus status = SaleStatus.BUDGET;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SalesItem> items = new ArrayList<>();
	
	@Transient
	private String uuid;
	
	@Transient
	private LocalDate deliveryDate;
	
	@Transient
	private LocalTime deliveryHour;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getFreightValue() {
		return freightValue;
	}

	public void setFreightValue(BigDecimal freightValue) {
		this.freightValue = freightValue;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getDeliveryHourDate() {
		return deliveryHourDate;
	}

	public void setDeliveryHourDate(LocalDateTime deliveryHourDate) {
		this.deliveryHourDate = deliveryHourDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SaleStatus getStatus() {
		return status;
	}

	public void setStatus(SaleStatus status) {
		this.status = status;
	}

	public List<SalesItem> getItems() {
		return items;
	}

	public void setItems(List<SalesItem> items) {
		this.items = items;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LocalTime getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(LocalTime deliveryHour) {
		this.deliveryHour = deliveryHour;
	}
	
	/*********** Business *************/
	
	public boolean isNew() {
		return this.code == null;
	}
	
	public void addItems(List<SalesItem> items) {
		this.items = items;
		this.items.forEach(i -> i.setSale(this));
	}
	
	public BigDecimal getTotalValueItems() {
		return getItems().stream()
				.map(SalesItem::getTotalValue)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	public void calculateTotalValue() {
		this.totalValue = calculateTotalValue(getTotalValueItems(), getFreightValue(), getDiscountValue());
	}
	
	public Long getDaysCreative() {
		LocalDate start = null != this.creationDate ? this.creationDate.toLocalDate() :  LocalDate.now();
		return ChronoUnit.DAYS.between(start, LocalDateTime.now());
	}
	
	public boolean isNotSaveAllowed() {
		return !isSaveAllowed();
	}
	
	public boolean isSaveAllowed() {
		return !SaleStatus.CANCELED.equals(this.status);
	}
	
	private BigDecimal calculateTotalValue(BigDecimal totalValueItems, BigDecimal freightValue, BigDecimal dicountValue) {
		BigDecimal totalValue = totalValueItems
				.add(Optional.ofNullable(freightValue).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(dicountValue).orElse(BigDecimal.ZERO));
		return totalValue;
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
		Sale other = (Sale) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	

	
}
