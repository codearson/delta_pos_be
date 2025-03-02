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

@Data
@Entity
@Table(name = "shopDetails")
public class ShopDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@Column(name = "contactNumber")
	private String contactNumber;
	@Column(name = "whatsappNumber")
	private String whatsappNumber;
	@Column(name = "email")
	private String email;
	@JoinColumn(name = "branch", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
	@Column(name = "isActive")
	private Boolean isActive;

}
