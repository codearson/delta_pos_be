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
 * Feb 12, 2024 8:27:17 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "payout")
public class Payout implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "amount")
	private Double amount;
	@JoinColumn(name = "payoutCategoryId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private PayoutCategory payoutcategory;
	@Column(name =  "dateTime")
	private LocalDateTime dateTime;
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@Column(name = "isActive")
	private Boolean isActive;

}
