package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductCategoryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductCategoryService;
import com.pos_main.Service.BL.ProductCategoryServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 14, 2024 4:43:56 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ProductCategoryServiceBL productCategoryServiceBL;
	
	@Override
	public ResponseDto getAll() {
		log.info("ProductCategoryServiceImpl.gellAll() invoked");
		ResponseDto responseDto = null;
		try {
			List<ProductCategoryDto> productCategoryDtoList = productCategoryServiceBL.gellAll();
			if (productCategoryDtoList != null && !productCategoryDtoList.isEmpty()) {
				log.info("Retrieve All Product Category Details.");
				responseDto = serviceUtil.getServiceResponse(productCategoryDtoList);
			} else {
				log.info("Unable to retrieve All Product Category details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATRGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Product Category details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CATRGORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("CustomerServiceImpl.getAllPageCustomer() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = productCategoryServiceBL.getAllPageProductCategory(pageNumber, pageSize, status, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All ProductCategory Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All ProductCategory details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Customer details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CATEGORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(ProductCategoryDto productCategoryDto) {
		log.info("ProductCategoryServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryDto  saveProductCategoryDto = productCategoryServiceBL.save(productCategoryDto);
			if (saveProductCategoryDto != null) {
				log.info("Product Category Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductCategoryDto);
			} else {
				log.info("Unable to save Product Category details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCT_CATRGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Product Category details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCT_CATRGORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
    public ResponseDto update(ProductCategoryDto productCategoryDto) {
        log.info("ProductCategoryServiceImpl.update() invoked");
        ResponseDto responseDto = null;
        try {
            ProductCategoryDto updateProductCategoryDto = productCategoryServiceBL.update(productCategoryDto);
            if (updateProductCategoryDto != null) {
                log.info("Product Category Details updated.");
                responseDto = serviceUtil.getServiceResponse(updateProductCategoryDto);
            } else {
                log.info("Unable to update Product Category details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_CATRGORY_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while updating Product Category details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_CATRGORY_DETAILS);
        }
        return responseDto;
    }

	@Override
    public ResponseDto getAllByName(String productCategoryName) {
        log.info("ProductCategoryServiceImpl.getAllByName() invoked");
        ResponseDto responseDto = null;
        try {
            List<ProductCategoryDto> productCategoryDtoList = productCategoryServiceBL.getAllByName(productCategoryName);
            if (productCategoryDtoList != null && !productCategoryDtoList.isEmpty()) {
                log.info("Retrieve Product Category Details by Name.");
                responseDto = serviceUtil.getServiceResponse(productCategoryDtoList);
            } else {
                log.info("Unable to retrieve Product Category by productCategoryName.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_CATEGORY_BY_NAME);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Product Category by productCategoryName.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_CATEGORY_BY_NAME);
        }
        return responseDto;
    }
	
	@Override
	public ResponseDto updateProductCategoryStatus(Integer Id, Boolean status) {
		log.info("InvoiceServiceImpl.updateUserStatus(ProductCategoryDto ProductCategoryDto) invoked");
		ResponseDto responseDto = null;
		try {
			ProductCategoryDto updatedProductCategoryStatusDto = productCategoryServiceBL.updateProductCategoryStatus(Id, status);
			if (updatedProductCategoryStatusDto != null) {
				log.info("User Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedProductCategoryStatusDto);
			} else {
				log.info("Unable to update User status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_CATEGORY_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating User status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_USER_STATUS);
		}
		return responseDto;
	}
	

}
