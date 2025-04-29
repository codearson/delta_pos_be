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
 * Title: DeviceAuth.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:34:21
 * @version 1.0
 **/

@Data
@Entity
@Table(name = "deviceAuth")
public class DeviceAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "tillName")
	private String tillName;
	@Column(name = "tillId")
	private String tillId;
	@Column(name = "approveStatus")
	private String approveStatus;
	@Column(name = "loginStatus")
	private String loginStatus;
	@Column(name = "isActive")
	private String isActive;
	
}
