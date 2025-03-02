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
 * Feb 13, 2024 
 * 9:51:03 PM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "emailAddress")
	private String emailAddress;
	@Column(name = "mobileNumber", nullable = false)
	private String mobileNumber;
	@Column(name = "whatsappNumber", nullable = false)
	private String whatsappNumber;
	@Column(name =  "createdDate")
	private LocalDateTime createdDate;
	@Column(name = "isActive")
	private Boolean isActive;

}
