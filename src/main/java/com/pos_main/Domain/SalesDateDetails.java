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

/**
 * Title: SalesDateDetails.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 18:56:31
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "salesDateDetails")
public class SalesDateDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "reportId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SalesReport salesReport; 
    @Column(name = "salesDate")
    private String salesDate;
    @Column(name = "totalTransactions")
    private Integer totalTransactions;
    @Column(name = "totalSales")
    private Double totalSales;
}
