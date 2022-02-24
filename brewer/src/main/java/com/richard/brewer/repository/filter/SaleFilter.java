package com.richard.brewer.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.richard.brewer.model.SaleStatus;

public class SaleFilter {
	
	private Long code;
	private SaleStatus status;

	private LocalDate since;
	private LocalDate upUntil;
	private BigDecimal minimumValue;
	private BigDecimal maximumValue;

	private String clientName;
	private String clientCpfCnpj;
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public SaleStatus getStatus() {
		return status;
	}
	public void setStatus(SaleStatus status) {
		this.status = status;
	}
	public LocalDate getSince() {
		return since;
	}
	public void setSince(LocalDate since) {
		this.since = since;
	}
	public LocalDate getUpUntil() {
		return upUntil;
	}
	public void setUpUntil(LocalDate upUntil) {
		this.upUntil = upUntil;
	}
	public BigDecimal getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(BigDecimal minimumValue) {
		this.minimumValue = minimumValue;
	}
	public BigDecimal getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(BigDecimal maximumValue) {
		this.maximumValue = maximumValue;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientCpfCnpj() {
		return clientCpfCnpj;
	}
	public void setClientCpfCnpj(String clientCpfCnpj) {
		this.clientCpfCnpj = clientCpfCnpj;
	}
	
}
