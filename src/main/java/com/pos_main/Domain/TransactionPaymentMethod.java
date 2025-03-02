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

/**
 * Title: TransactionPaymentMethod.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Jan 29, 2025
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "transactionPaymentMethod")
public class TransactionPaymentMethod implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "transactionId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Transaction transaction;
	@JoinColumn(name = "paymentMethodId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private PaymentMethod paymentMethod;
	@Column(name ="amount", nullable = false)
	private Double amount;
	@Column(name = "isActive")
	private Boolean isActive;
	
}
