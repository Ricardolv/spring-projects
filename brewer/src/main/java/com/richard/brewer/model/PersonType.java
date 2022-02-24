package com.richard.brewer.model;

import com.richard.brewer.model.validation.group.CnpjGroup;
import com.richard.brewer.model.validation.group.CpfGroup;

public enum PersonType {
	
	PHYSICAL("Física", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatting(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	
	JURIDICAL("Jurídica", "CPNJ", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatting(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};
	
	private String description;
	private String document;
	private String mask;
	private Class<?> group;
	
	PersonType(String description, String document, String mask, Class<?> group) {
		this.description = description;
		this.document = document;
		this.mask = mask;
		this.group = group;
	}
	
	public abstract String formatting(String cpfCnpj);

	public String getDescription() {
		return description;
	}

	public String getDocument() {
		return document;
	}

	public String getMask() {
		return mask;
	}

	public Class<?> getGroup() {
		return group;
	}
	
	public static String removeFormatting(String cpfCnpj) {
		return cpfCnpj.replaceAll("\\.|-|/", "");
	}
	
}
