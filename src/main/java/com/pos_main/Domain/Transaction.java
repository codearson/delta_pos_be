package com.pos_main.Domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "dateTime", nullable = false)
	private LocalDateTime dateTime;
    @Column(name ="totalAmount", nullable = false)
	private Double totalAmount;
    @Column(name ="status", nullable = false)
    private String status;
    @JoinColumn(name = "branchId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Branch branch;
    @JoinColumn(name = "shopdetailsId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private ShopDetails shopDetails;
    @JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private User user;
    @JoinColumn(name = "customerId", referencedColumnName = "id")
	@ManyToOne(optional = false)
	private Customer customer;
    @Column(name = "isActive")
	private Boolean isActive;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionPaymentMethod> transactionPaymentMethod;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionDetailsList> transactionDetailsList;
    @Column(name = "generateDateTime")
	private LocalDateTime generateDateTime;
    @Column(name = "manualDiscount")
	private Double manualDiscount;
    @Column(name = "employeeDiscount")
	private Double employeeDiscount;
    
}