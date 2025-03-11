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
 * Title: Branch.java. Company: www.codearson.com Copyright: Copyright (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "branch")
public class Branch implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "branch_name")
	private String branchName;
	@Column(name = "branch_code")
	private String branchCode;
	@Column(name = "address")
	private String address;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "email_address")
	private String emailAddress;
	@Column(name = "isActive")
	private Boolean isActive;
	@JoinColumn(name = "countryId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Country country;

}
