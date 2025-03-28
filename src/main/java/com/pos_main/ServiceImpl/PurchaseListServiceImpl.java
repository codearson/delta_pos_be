package com.pos_main.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PurchaseListService;
import com.pos_main.Service.BL.PurchaseListServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PurchaseListServiceImpl implements PurchaseListService {

    @Autowired
    private PurchaseListServiceBL purchaseListServiceBL;

    @Autowired
    private ServiceUtil serviceUtil;

    @Override
    public ResponseDto savePurchaseList(PurchaseListDto purchaseListDto) {
        log.info("PurchaseListServiceImpl.savePurchaseList invoked");
        ResponseDto responseDto;
        try {
            PurchaseListDto savedDto = purchaseListServiceBL.savePurchaseList(purchaseListDto);
            if (savedDto != null) {
                log.info("PurchaseList Details saved.");
                responseDto = serviceUtil.getServiceResponse(savedDto);
            } else {
                log.info("Unable to save PurchaseList details.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PURCHASE_LIST_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while saving PurchaseList details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PURCHASE_LIST_DETAILS);
        }
        return responseDto;
    }
    
    @Override
	public ResponseDto getAll() {
		log.info("PurchaseListServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			List<PurchaseListDto> purchaseListDtoList = purchaseListServiceBL.getAll();
			if (purchaseListDtoList != null && !purchaseListDtoList.isEmpty()) {
				log.info("Retrieve All Details.");
				responseDto = serviceUtil.getServiceResponse(purchaseListDtoList);
			} else {
				log.info("Unable to retrieve All details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_DETAILS);
		}
		return responseDto;
	}
    
    @Override
    public ResponseDto deleteAll() {
        log.info("PurchaseListServiceImpl.deleteAll() invoked");
        ResponseDto responseDto;
        try {
            boolean isDeleted = purchaseListServiceBL.deleteAll();
            if (isDeleted) {
                log.info("All PurchaseList details deleted successfully.");
                responseDto = serviceUtil.getServiceResponse("All PurchaseList records deleted successfully");
            } else {
                log.info("No PurchaseList records found to delete.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_ALL_PURCHASE_LIST_DETAILS);
            }
        } catch (Exception e) {
            log.error("Exception while deleting all PurchaseList details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_DELETE_ALL_PURCHASE_LIST_DETAILS);
        }
        return responseDto;
    }
    
    @Override
    public ResponseDto deleteById(Integer id) {
        log.info("PurchaseListServiceImpl.deleteById() invoked with id: {}", id);
        ResponseDto responseDto;
        try {
            boolean isDeleted = purchaseListServiceBL.deleteById(id);
            if (isDeleted) {
                log.info("Purchase list item deleted successfully.");
                responseDto = serviceUtil.getServiceResponse("Purchase list item deleted successfully");
            } else {
                log.info("Purchase list item with ID {} not found", id);
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_PURCHASE_LIST_BY_ID);
            }
        } catch (Exception e) {
            log.error("Exception while deleting purchase list item with id: {}", id, e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_DELETE_PURCHASE_LIST_BY_ID);
        }
        return responseDto;
    }
}
