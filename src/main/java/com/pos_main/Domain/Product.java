package com.pos_main.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;

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

/**
 * Feb 13, 2024 
 * 9:50:25 PM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "barcode")
	private String barcode;
	@Column(name = "pricePerUnit")
	private Double pricePerUnit;
	@JoinColumn(name = "tax", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Tax tax;
	@Column(name =  "createdDate")
	private LocalDateTime createdDate;
	@JoinColumn(name = "productCategory", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ProductCategory productCategory;
	@Column(name =  "quantity")
	private Integer quantity;
	@Column(name =  "lowStock")
	private Integer lowStock;
	@Column(name = "purchasePrice")
	private Double purchasePrice;
	@Column(name = "isActive")
	private Boolean isActive;
	@Column(name = "discountValidation")
	private Boolean discountValidation;
}
