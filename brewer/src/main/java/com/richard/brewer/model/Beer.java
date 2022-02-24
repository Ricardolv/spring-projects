package com.richard.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.richard.brewer.annotation.SKU;

@Entity
@Table(name = "beer")
public class Beer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code;
	
	@SKU
	@NotBlank
	private String sku;
	
	@NotBlank
	private String name;
	
	@NotNull(message = "Valor é obrigatório")
	@DecimalMin(value = "0.50", message = "O valor da cerveja deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99")
	private BigDecimal value;
	
	@NotNull(message = "O teor alcóolico é obrigatório")
	@DecimalMax(value = "100.0", message = "O valor do teor alcóolico deve ser menor que 100")
	@Column(name = "content_alcohol") //teor alcoolico
	private BigDecimal contentAlcohol;
	
	@NotNull(message = "Comissão é obrigatório")
	@DecimalMax(value = "100.0", message = "A comissão deve ser igual ou menor que 100")
	private BigDecimal commission; //comissao
	
	@NotNull(message = "Estoque é obrigatório")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	@Column(name = "quantity_stock")
	private Integer quantityStock; //quantidade estoque
	
	@NotNull(message = "Origem é obrigatória")
	@Enumerated(EnumType.STRING)
	private Origin origin; //origem
	
	@NotNull(message = "Sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Flavor flavor; //sabor
	
	@NotNull(message = "Estilo é obrigatório")
	@ManyToOne
	@JoinColumn(name = "code_style" )
	private Style style;
	
	@NotBlank(message = "Descrição é obrigatória")
	@Size(max = 50, message = "O tamanho da descrição deve ser estar entre 1 e 50")
	private String description;
	
	private String photo;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean newPhoto;
	
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

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getContentAlcohol() {
		return contentAlcohol;
	}

	public void setContentAlcohol(BigDecimal contentAlcohol) {
		this.contentAlcohol = contentAlcohol;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public Integer getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(Integer quantityStock) {
		this.quantityStock = quantityStock;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public Flavor getFlavor() {
		return flavor;
	}

	public void setFlavor(Flavor flavor) {
		this.flavor = flavor;
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public boolean isNewPhoto() {
		return newPhoto;
	}

	public void setNewPhoto(boolean newPhoto) {
		this.newPhoto = newPhoto;
	}
	
	/** BUSINESS */
	
	@PrePersist 
	@PreUpdate
 	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
	public String getPhotoOrMock() {
		return !StringUtils.isEmpty(this.photo) ? this.photo : "beer-mock.png";
	}
	
	public boolean havePhoto() {
		return !StringUtils.isEmpty(this.photo);
	}
	
	public boolean isNew() {
		return null == this.code;
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
		Beer other = (Beer) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}
