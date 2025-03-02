package com.pos_main.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.BranchService;
import com.pos_main.Service.BL.BranchServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchServiceImpl.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	BranchServiceBL branchServiceBL;

	@Override
	public ResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BranchServiceImpl.getAll() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = branchServiceBL.getAll(pageNumber, pageSize, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Branch Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRANCH_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllBranches() {
		log.info("BranchServiceImpl.gellAllBranches() invoked");
		ResponseDto responseDto = null;
		try {
			List<BranchDto> branchDtoList = branchServiceBL.gellAllBranches();
			if (branchDtoList != null && !branchDtoList.isEmpty()) {
				log.info("Retrieve All Branch Details.");
				responseDto = serviceUtil.getServiceResponse(branchDtoList);
			} else {
				log.info("Unable to retrieve All Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Barcode details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRANCH_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(BranchDto branchDto) {
		log.info("BranchServiceImpl.save(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			BranchDto saveBranchDto = branchServiceBL.save(branchDto);
			if (saveBranchDto != null) {
				log.info("Branch Details saved.");
				responseDto = serviceUtil.getServiceResponse(saveBranchDto);
			} else {
				log.info("Unable to save Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_BRANCH_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllBranchesBySbuId(Integer sbuId) {
		ResponseDto responseDto = null;
		try {
			List<BranchDto> branchDtoList = branchServiceBL.getbranchBySBUid(sbuId);
			if (branchDtoList != null && !branchDtoList.isEmpty()) {
				log.info("branch detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(branchDtoList);
			} else {
				log.info("Unable to Retrive branch detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_BRANCH_BY_SBU_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the branch detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_BRANCH_BY_SBU_ID);
		}
		return responseDto;
	}
	
	@Override
	public ResponseDto updateBranch(BranchDto branchDto) {
	    log.info("BranchServiceImpl.updateBranch() invoked");
	    ResponseDto responseDto = null;
	    try {
	        BranchDto updatedBranch = branchServiceBL.updateBranch(branchDto);
	        if (updatedBranch != null) {
	            log.info("Branch details updated successfully.");
	            responseDto = serviceUtil.getServiceResponse(updatedBranch);
	        } else {
	            log.info("Failed to update Branch details.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_RE_BRANCH_DETAILS);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while updating Branch details.", e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	                ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_RE_BRANCH_DETAILS);
	    }
	    return responseDto;
	}

	
	@Override
	public ResponseDto getBranchByName(String branchName) {
	    log.info("BranchServiceImpl.getBranchByName() invoked");

	    try {
	        List<BranchDto> branchList = branchServiceBL.getBranchByName(branchName);
	        if (!branchList.isEmpty()) {
	            return serviceUtil.getServiceResponse(branchList);
	        } else {
	            return serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_BRANCH_BY_NAME);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while retrieving Branch by name", e);
	        return serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_BRANCH_BY_NAME);
	    }
	}
	
	@Override
	public ResponseDto updateBranchStatus(Integer branchId, Boolean status) {
		log.info("InvoiceServiceImpl.updateBranchStatus(BranchDto branchDto) invoked");
		ResponseDto responseDto = null;
		try {
			BranchDto updatedBranchStatusDto = branchServiceBL.updateBranchStatus(branchId, status);
			if (updatedBranchStatusDto != null) {
				log.info("Branch Status updated.");
				responseDto = serviceUtil.getServiceResponse(updatedBranchStatusDto);
			} else {
				log.info("Unable to update Branch status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BRANCH_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Branch status.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_BRANCH_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getBranchById(Integer id) {
		ResponseDto responseDto = null;
		try {
			List<BranchDto> branchDtoList = branchServiceBL.getBranchById(id);
			if (branchDtoList != null && !branchDtoList.isEmpty()) {
				log.info("branch detail retreived successfully.");
				responseDto = serviceUtil.getServiceResponse(branchDtoList);
			} else {
				log.info("Unable to Retrive branch detail");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_BRANCH_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while Retrive the branch detail.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_BRANCH_BY_ID);
		}
		return responseDto;
	}
}
