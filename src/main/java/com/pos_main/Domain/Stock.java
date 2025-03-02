package com.pos_main.Domain;

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
 * Title: Stock.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:02:57
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "stock")
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "productId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Product product;
	@JoinColumn(name = "branchId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "isActive")
	private Boolean isActive;
	
}
