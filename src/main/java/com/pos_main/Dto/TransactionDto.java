package com.pos_main.Dto;

import java.time.LocalDateTime;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TransactionDto {
	private Integer id;
	private LocalDateTime dateTime;
	private Double totalAmount;
	private String status;
	private BranchDto branchDto;
	private ShopDetailsDto shopDetailsDto;
	private UserDto userDto;
	private CustomerDto customerDto;
	private String notification;
	private Boolean isActive;
	private List<TransactionDetailsListDto> transactionDetailsList = new ArrayList<>();
    private List<TransactionPaymentMethodDto> transactionPaymentMethod = new ArrayList<>();
    private List<TransactionEmployeeDto> transactionEmployee = new ArrayList<>();
    private LocalDateTime generateDateTime;
    private Double manualDiscount;
    private Double employeeDiscount;
    private Double balanceAmount;
    private Double taxAmount;
}
