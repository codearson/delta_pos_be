package com.pos_main.Dto;

import lombok.Data;

/**
 * Title: OverallPaymentTotalsDto.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 20:01:27
 * @version 1.0
 **/

@Data
public class OverallPaymentTotalsDto {
	
	private Integer id;
	private SalesDateDetailsDto salesDateDetailsDto;
	private String paymentMethod;
    private Double paymentTotal;

}
