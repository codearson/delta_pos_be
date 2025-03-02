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

@Data
@Entity
@Table(name = "shifts")
public class Shifts implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@JoinColumn(name = "user", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
	@Column(name =  "startTime")
	private LocalDateTime startTime;
	@Column(name =  "endTime")
	private LocalDateTime endTime;
	@Column(name = "isActive")
	private Boolean isActive;
	
}
