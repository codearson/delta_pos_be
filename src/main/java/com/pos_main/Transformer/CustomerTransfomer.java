package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.Customer;
import com.pos_main.Dto.CustomerDto;

/**
 * Feb 13, 2024 
 * 10:50:38 PM
 * @author Lathusan Thurairajah
 **/

@Component
public class CustomerTransfomer implements BaseTransformer<Customer, CustomerDto>{

	@Override
	public CustomerDto transform(Customer customer) {
		CustomerDto customerDto = null;
		if (customer != null) {
			customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setMobileNumber(customer.getMobileNumber());
			customerDto.setCreatedDate(customer.getCreatedDate());
			customerDto.setIsActive(customer.getIsActive());
		}
		return customerDto;
	}

	@Override
	public Customer reverseTransform(CustomerDto customerDto) {
		Customer customer = null;
		if (customerDto != null) {
			customer = new Customer();
			customer.setId(customerDto.getId());
			customer.setName(customerDto.getName());
			customer.setMobileNumber(customerDto.getMobileNumber());
			customer.setCreatedDate(customerDto.getCreatedDate());
			customer.setIsActive(customerDto.getIsActive());
		}
		return customer;
	}

}
