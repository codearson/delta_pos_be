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
 * Feb 5, 2024 
 * 10:18:18 AM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "userRole")
public class UserRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "userRole")
	private String userRole;
	@Column(name = "isActive")
	private Boolean isActive;

}
