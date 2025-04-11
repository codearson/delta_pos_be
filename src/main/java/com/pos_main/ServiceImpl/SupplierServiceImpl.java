package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SupplierDto;
import com.pos_main.Service.SupplierService;
import com.pos_main.Service.BL.SupplierServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierServiceBL supplierServiceBL;
	
	@Autowired
	private ServiceUtil serviceUtil;
	
	@Override
	public ResponseDto saveSupplier(SupplierDto supplierDto) {
		log.info("SupplierServiceImpl.saveSupplier invoked");
		ResponseDto responseDto = null;
		try {
			SupplierDto saveSupplierDto = supplierServiceBL.saveSupplier(supplierDto);
			if (saveSupplierDto != null) {
				log.info("Supplier Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveSupplierDto);
			} else {
				log.info("Unable to save Supplier details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SUPPLIER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Supplier details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SUPPLIER_DETAILS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getSupplierByName(String name) {
		ResponseDto responseDto = null;
		try {
			List<SupplierDto> supplierDtoList = supplierServiceBL.getSupplierByName(name);
			if (supplierDtoList != null && !supplierDtoList.isEmpty()) {
				log.info("supplier detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(supplierDtoList);
			} else {
				log.info("Unable to Retrive supplier detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_SUPPLIER_BY_NAME);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the supplier detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_SUPPLIER_BY_NAME);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateSupplier(SupplierDto supplierDto) {
		log.info("SupplierServiceImpl.update(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			SupplierDto updateSupplierDto = supplierServiceBL.updateSupplier(supplierDto);
			if (updateSupplierDto != null) {
				log.info("Supplier Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateSupplierDto);
			} else {
				log.info("Unable to update Product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_RE_SUPPLIER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_RE_SUPPLIER_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updateSupplierStatus(Integer supplierId, Boolean status) {
		log.info("SupplierServiceImpl.updateSupplierStatus(SupplierDto supplierDto) invoked");
		ResponseDto responseDto = null;
		try {
			SupplierDto updateSupplierStatusDto = supplierServiceBL.updateSupplierStatus(supplierId, status);
			if (updateSupplierStatusDto != null) {
				log.info("Supplier Status updated.");
				responseDto = serviceUtil.getServiceResponse(updateSupplierStatusDto);
			} else {
				log.info("Unable to update Supplier status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_SUPPLIER_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Supplier status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_SUPPLIER_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getSupplierById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<SupplierDto> supplierDtoList = supplierServiceBL.getSupplierById(id);
			if (supplierDtoList != null && !supplierDtoList.isEmpty()) {
				log.info("supplier detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(supplierDtoList);
			} else {
				log.info("Unable to Retrive supplier detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_SUPPLIER_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the supplier detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_SUPPLIER_BY_ID);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllSupplier() {
		log.info("SupplierServiceImpl.getAllSupplier() invoked");
		ResponseDto responseDto = null;
		try {
			List<SupplierDto> supplierDtoList = supplierServiceBL.getAllSupplier();
			if (supplierDtoList != null && !supplierDtoList.isEmpty()) {
				log.info("Retrieve All Supplier Details.");
				responseDto = serviceUtil.getServiceResponse(supplierDtoList);
			} else {
				log.info("Unable to retrieve All Supplier details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SUPPLIER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Supplier details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SUPPLIER_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageSupplier(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("SupplierServiceImpl.getAllPageSupplier() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = supplierServiceBL.getAllPageSupplier(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Supplier Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Supplier details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PAGE_SUPPLIER_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Supplier details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PAGE_SUPPLIER_DETAILS);
		}
		return responseDto;
	}


}
