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
 * Feb 5, 2024 
 * 10:01:39 AM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "firstName",nullable = false)
	private String firstName;
	@Column(name = "lastName",nullable = false)
	private String lastName;
	@Column(name = "password",nullable = false)
	private String password;
	@Column(name = "address")
	private String address;
	@Column(name = "emailAddress",nullable = false)
	private String emailAddress;
	@Column(name = "mobileNumber")
	private String mobileNumber;
	@Column(name =  "createdDate")
	private LocalDateTime createdDate;
	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;
	@Column(name = "isActive")
	private Boolean isActive;
	@JoinColumn(name = "userRoleId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private UserRole userRole;
	@JoinColumn(name = "branchId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
	
}
