package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Customer;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.PaginatedResponseDto;

public interface CustomerDao extends BaseDao<Customer>{
	
	List<CustomerDto> getCustomerBySearch(String name);
	
	List<CustomerDto> getCustomerByMobileNumber(String mobileNumber);

	PaginatedResponseDto getAllPageCustomer(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams);
	
	CustomerDto saveCustomer (CustomerDto customerDto);
	
	List<CustomerDto> getCustomerById(Integer id);
	
	CustomerDto updateCustomer (CustomerDto customerDto);
	
	List<CustomerDto> getAllCustomer();
	
	CustomerDto checkCustomerAvailability(Integer customerId);
	
}
