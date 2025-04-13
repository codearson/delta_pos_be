package com.pos_main.Domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="productDiscount")
public class ProductDiscount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "discount")
	private Double discount;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "endDate")
	private LocalDate endDate;
	@Column(name = "isActive")
	private Boolean isActive;
	@JoinColumn(name = "productDiscountType", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ProductDiscountType productDiscountType;
	@JoinColumn(name = "product", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Product product;
	
}
