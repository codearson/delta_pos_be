package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.ResponseDto;

public interface CustomerService {

	public ResponseDto saveCustomer(CustomerDto customerDto);
	
	public ResponseDto getAllCustomer(int pageNumber, int pageSize, Map<String, String> searchParameters);

	public ResponseDto getCustomerBySearch(String firstName, String lastName);
	
	public ResponseDto getCustomerById(Integer id);
	
}
