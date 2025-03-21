package com.pos_main.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Title: SalesReport.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 18:12:55
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "salesReport")
public class SalesReport implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "reportGeneratedBy")
	private String reportGeneratedBy;
	@Column(name =  "startDate")
	private LocalDateTime startDate;
	@Column(name =  "endDate")
	private LocalDateTime endDate;
	@Column(name = "fullyTotalSales")
	private Double fullyTotalSales;
	@Column(name = "report_type")
	private String reportType;

}
