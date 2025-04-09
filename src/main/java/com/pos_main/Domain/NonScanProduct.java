package com.pos_main.Domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Title: NonScanProduct.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:22:58
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "nonScanProduct")
public class NonScanProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nonScanProduct")
	private String nonScanProduct;
	@Column(name = "icon")
	private String icon;
	@Column(name = "price")
	private Double price;
	@Column(name = "isActive")
	private Boolean isActive;
}
