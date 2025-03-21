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
 * Title: UserPaymentDetails.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:22:01
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "userPaymentDetails")
public class UserPaymentDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "salesDateId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SalesDateDetails salesDateDetails;
	@Column(name = "userName")
    private String userName;
	@Column(name = "paymentMethod")
    private String paymentMethod;
	@Column(name = "paymentTotal")
    private Double paymentTotal;
	
}
