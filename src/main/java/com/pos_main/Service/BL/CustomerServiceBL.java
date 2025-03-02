package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.CustomerDao;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceBL {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<CustomerDto> getCustomerBySearch(String firstName, String lastName) {
		log.info("CustomerServiceBL.getCustomerBySearch()invoked");
		return customerDao.getCustomerBySearch(firstName, lastName);
	}
	
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		log.info("CustomerServiceBL.saveCustomer() invoked.");
		return customerDao.saveCustomer(customerDto);
	}
	
	public PaginatedResponseDto getAllCustomer(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("CustomerServiceBL.getAll()invoked");
		return customerDao.getAllCustomer(pageNumber, pageSize, searchParams);
	}
	
	public List<CustomerDto> getCustomerById(Integer id) {
		log.info("CustomerServiceBL.getCustomerById()invoked");
		return customerDao.getCustomerById(id);
	}

}
