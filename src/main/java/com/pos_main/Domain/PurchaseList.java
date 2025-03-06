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
 * Mar 3, 2025 05:31:13 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 **/

@Data
@Entity
@Table(name = "purchaseList")
public class PurchaseList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "productName", nullable = false)
	private String ProductName;
	@Column(name = "barcode", nullable = false)
	private String Barcode;
	
}
