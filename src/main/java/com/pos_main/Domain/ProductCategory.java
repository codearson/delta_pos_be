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
 * Feb 13, 2024 
 * 10:12:48 PM
 * @author Lathusan Thurairajah
 **/

@Data
@Entity
@Table(name = "productCategory")
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "productCategoryName")
	private String productCategoryName;
	@Column(name = "isActive")
	private Boolean isActive;

}
