package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.CustomerDao;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceBL {
	
	@Autowired
	private CustomerDao customerDao;
	
	public List<CustomerDto> getCustomerBySearch(String name) {
		log.info("CustomerServiceBL.getCustomerBySearch()invoked");
		return customerDao.getCustomerBySearch(name);
	}
	
	public List<CustomerDto> getCustomerByMobileNumber(String mobileNumber) {
		log.info("CustomerServiceBL.getCustomerByMobileNumber()invoked");
		return customerDao.getCustomerByMobileNumber(mobileNumber);
	}
	
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		log.info("CustomerServiceBL.saveCustomer() invoked.");
		return customerDao.saveCustomer(customerDto);
	}
	
	public PaginatedResponseDto getAllPageCustomer(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("CustomerServiceBL.getAllPageCustomer()invoked");
		return customerDao.getAllPageCustomer(pageNumber, pageSize, searchParams);
	}
	
	public List<CustomerDto> getCustomerById(Integer id) {
		log.info("CustomerServiceBL.getCustomerById()invoked");
		return customerDao.getCustomerById(id);
	}
	
	public CustomerDto updateCustomer(CustomerDto customerDto) {
		log.info("CustomerServiceBL.updateCustomer() invoked.");
		return customerDao.updateCustomer(customerDto);
	}
	
	public List<CustomerDto> getAllCustomer() {
		log.info("CustomerServiceBL.getAllCustomer()invoked");
		return customerDao.getAllCustomer();
	}
	
	public CustomerDto updateCustomerStatus(Integer customerId, Boolean status) {
		CustomerDto customerDto = customerDao.checkCustomerAvailability(customerId);
		if (customerDto != null) {
			customerDto.setIsActive(status);
			return customerDao.updateCustomer(customerDto);
		} else {
			return null;
		}
	}

}
