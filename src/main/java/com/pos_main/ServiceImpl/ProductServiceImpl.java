package com.pos_main.ServiceImpl;

import java.util.List;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductService;
import com.pos_main.Service.BL.ProductServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 17, 2024 
 * 10:34:33 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ServiceUtil serviceUtil;
	
	@Autowired
	ProductServiceBL productServiceBL;
	
	@Override
	public ResponseDto save(ProductDto productDto) {
		log.info("ProductServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			ProductDto  saveProductDto = productServiceBL.save(productDto);
			if (saveProductDto != null) {
				log.info("Product Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveProductDto);
			} else {
				log.info("Unable to save Product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_CATRGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_CATRGORY_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams, Boolean status) {
		log.info("ProductServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = productServiceBL.getAll(pageNumber, pageSize, searchParams, status);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Product Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto getAllProducts() {
		log.info("ProductServiceImpl.getAllProducts() invoked");
		ResponseDto responseDto = null;
		try {
			List<ProductDto> productDtoList = productServiceBL.getAllProducts();
			if (productDtoList != null && !productDtoList.isEmpty()) {
				log.info("Retrieve All Product Details.");
				responseDto = serviceUtil.getServiceResponse(productDtoList);
			} else {
				log.info("Unable to retrieve All Product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getProductByBarcode(String barcode) {
		ResponseDto responseDto = null;
		try {
			List<ProductDto> productDtoList = productServiceBL.getProductByBarcode(barcode);
			if (productDtoList != null && !productDtoList.isEmpty()) {
				log.info("product detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(productDtoList);
			} else {
				log.info("Unable to Retrive product detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCT_BY_BARCODE);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the product detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCT_BY_BARCODE);
		}
		return responseDto;
	}

	@Transactional	
	@Override
    public ResponseDto getProductByName (String name) {
        log.info("ProductServiceImpl.getProductByName () invoked");
        ResponseDto responseDto = null;
        try {
            List<ProductDto> productDtoList = productServiceBL.getProductByName(name);
            if (productDtoList != null && !productDtoList.isEmpty()) {
                log.info("Retrieve Product Details by Name.");
                responseDto = serviceUtil.getServiceResponse(productDtoList);
            } else {
                log.info("Unable to retrieve Product by name.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCT_BY_NAME);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Product Category by productCategoryName.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCT_BY_NAME);
        }
        return responseDto;
    }
	
	@Override
	public ResponseDto updateProduct(ProductDto productDto) {
		log.info("ProductServiceImpl.update(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			ProductDto updateProductDto = productServiceBL.updateProduct(productDto);
			if (updateProductDto != null) {
				log.info("Product Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateProductDto);
			} else {
				log.info("Unable to update Product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_RE_PRODUCT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Product details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_RE_PRODUCT_DETAILS);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updateProductStatus(Integer productId, Boolean status) {
		log.info("ProductServiceImpl.updateProductStatus(ProductDto productDto) invoked");
		ResponseDto responseDto = null;
		try {
			ProductDto updatedProductStatusDto = productServiceBL.updateProductStatus(productId, status);
			if (updatedProductStatusDto != null) {
				log.info("Product Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedProductStatusDto);
			} else {
				log.info("Unable to update Product status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Product status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getProductById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<ProductDto> productDtoList = productServiceBL.getProductById(id);
			if (productDtoList != null && !productDtoList.isEmpty()) {
				log.info("product detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(productDtoList);
			} else {
				log.info("Unable to Retrive product detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCT_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the product detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCT_BY_ID);
		}
		return responseDto;
	}

}
