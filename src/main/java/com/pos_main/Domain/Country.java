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
 * Mar 7, 2025 12:35:34 AM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

@Data
@Entity
@Table(name = "country")
public class Country implements Serializable {

    
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
    @Column(name = "countryName", nullable = false)
    private String countryName;
    
    @Column(name = "priceSymbol", nullable = false)
    private String priceSymbol;
}