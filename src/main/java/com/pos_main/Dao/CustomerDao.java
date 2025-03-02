package com.pos_main.Dao;

import java.util.List;
import java.util.Map;

import com.pos_main.Domain.Customer;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;

public interface CustomerDao extends BaseDao<Customer>{
	
	List<CustomerDto> getCustomerBySearch(String firstName, String lastName);

	PaginatedResponseDto getAllCustomer(int pageNumber, int pageSize, Map<String, String> searchParams);
	
	CustomerDto saveCustomer (CustomerDto customerDto);
	
	List<CustomerDto> getCustomerById(Integer id);

}
