package com.richard.brewer.repository.filter;

import com.richard.brewer.model.PersonType;

public class ClientFilter {
	
	private String name;
	private String cpfCnpj;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	public Object getCpfCnpjWithoutFormatting() {
		return PersonType.removeFormatting(this.cpfCnpj);
	}

}
