package com.richard.brewer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.richard.brewer.annotation.ConfirmationAttributes;

@ConfirmationAttributes(attribute = "password", attributeConfirm = "passwordConfirm", message = "Confirmação da senha não confere")
@Entity
@Table(name = "tb_user")
@DynamicUpdate
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@NotBlank(message= "Nome é obrigatório")
	private String name;
	
	@NotBlank(message= "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	//@NotNull(message = "Data de nascimento é obrigatório")
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	private Boolean active;
	
	@Size(min = 1,  message = "Seleciione pelo menos um grupo")
	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "code_user"),
	inverseJoinColumns = @JoinColumn(name = "code_group"))
	private List<Group> groups;
	
	@PreUpdate
	public void preUpdate() {
		this.passwordConfirm = this.password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	/** BUSINESS */
	
	public boolean isNew() {
		return null == this.code;
	}
	
	public boolean isEdit() {
		return !isNew();
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
		User other = (User) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}
