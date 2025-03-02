package com.pos_main.ServiceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.CustomerDto;
import java.util.Map;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.CustomerService;
import com.pos_main.Service.BL.CustomerServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerServiceBL customerServiceBL;
	
	@Autowired
	private ServiceUtil serviceUtil;
	
	@Transactional
	@Override
	public ResponseDto getCustomerBySearch(String firstName, String lastName) {
		ResponseDto responseDto = null;
		try {
			List<CustomerDto> customerDtoList = customerServiceBL.getCustomerBySearch(firstName, lastName);
			if (customerDtoList != null && !customerDtoList.isEmpty()) {
				log.info("customer detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(customerDtoList);
			} else {
				log.info("Unable to Retrive customer detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_CUSTOMER_BY_SEARCH);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the customer detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_USER_CUSTOMER_BY_SEARCH);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllCustomer(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("CustomerServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = customerServiceBL.getAllCustomer(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Customer Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Customer details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CUSTOMER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Customer details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CUSTOMER_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveCustomer(CustomerDto customerDto) {
		log.info("CustomerServiceImpl.saveCustomer invoked");
		ResponseDto responseDto = null;
		try {
			CustomerDto saveCustomerDto = customerServiceBL.saveCustomer(customerDto);
			if (saveCustomerDto != null) {
				log.info("Customer Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveCustomerDto);
			} else {
				log.info("Unable to save Customer details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_CUSTOMER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Customer details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_CUSTOMER_DETAILS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getCustomerById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<CustomerDto> customerDtoList = customerServiceBL.getCustomerById(id);
			if (customerDtoList != null && !customerDtoList.isEmpty()) {
				log.info("customer detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(customerDtoList);
			} else {
				log.info("Unable to Retrive customer detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_CUSTOMER_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the customer detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_CUSTOMER_BY_ID);
		}
		return responseDto;
	}
	
}
