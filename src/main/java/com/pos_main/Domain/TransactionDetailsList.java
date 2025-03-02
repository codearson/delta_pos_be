package com.pos_main.Domain;

import java.io.Serializable;

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
@Table(name = "transactionDetailsList")
public class TransactionDetailsList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "transactionId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Transaction transaction;
	@JoinColumn(name = "productId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Product product;
	@Column(name = "unitPrice")
	private Double unitPrice;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "discount")
	private Double discount;
	
}
