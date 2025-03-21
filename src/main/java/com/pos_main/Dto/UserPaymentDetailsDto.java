package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: UserPaymentDetailsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:22:18
 * @version 1.0
 **/

@Data
public class UserPaymentDetailsDto {
	
	private Integer id;
	private SalesDateDetailsDto salesDateDetailsDto;
    private String userName;
    private String paymentMethod;
    private Double paymentTotal;

}
