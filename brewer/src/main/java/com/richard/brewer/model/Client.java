package com.richard.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.richard.brewer.model.validation.ClientGroupProvider;
import com.richard.brewer.model.validation.group.CnpjGroup;
import com.richard.brewer.model.validation.group.CpfGroup;

@Entity
@Table(name = "client")
@GroupSequenceProvider(ClientGroupProvider.class)
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;

	@NotBlank(message = "Nome é obrigatório")
	private String name;

	@NotNull(message = "Tipo pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	@Column(name = "person_type")
	private PersonType personType;

	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	private String phone;

	@Email(message = "E-mail inválido")
	private String email;

	@JsonIgnore
	@Embedded
	private Anddress anddress;
	
	@PrePersist @PreUpdate
 	private void prePersistUpdate() {
		this.cpfCnpj = PersonType.removeFormatting(this.cpfCnpj);
	}
	
	@PostLoad
	private void postLoad() {
		this.cpfCnpj = this.personType.formatting(this.cpfCnpj);
	}
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Anddress getAnddress() {
		return anddress;
	}

	public void setAnddress(Anddress anddress) {
		this.anddress = anddress;
	}
	
	/** BUSINESS */
	
	public boolean isNew() {
		return null == this.code;
	}
	
	public String getCpfCnpjWithoutFormatting() {
		return PersonType.removeFormatting(this.cpfCnpj);
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
		Client other = (Client) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}
