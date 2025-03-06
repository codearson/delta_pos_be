package com.pos_main.Service;

import java.util.Map;

import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.ResponseDto;

public interface CustomerService {

	public ResponseDto saveCustomer(CustomerDto customerDto);
	
	public ResponseDto getAllPageCustomer(int pageNumber, int pageSize, Map<String, String> searchParameters);

	public ResponseDto getCustomerBySearch(String name);
	
	public ResponseDto getCustomerByMobileNumber(String mobileNumber);
	
	public ResponseDto getCustomerById(Integer id);
	
	public ResponseDto updateCustomer(CustomerDto customerDto);
	
	public ResponseDto getAllCustomer();
	
	public ResponseDto updateCustomerStatus(Integer customerId, Boolean status);
	
}
