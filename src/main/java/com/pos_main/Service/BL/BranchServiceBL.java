package com.pos_main.Service.BL;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.BranchDao;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchBL.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Service
public class BranchServiceBL {

	@Autowired
	BranchDao branchDao;

	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BranchServiceBL.getAll()invoked");
		return branchDao.getAll(pageNumber, pageSize, searchParams);
	}

	public List<BranchDto> gellAllBranches() {
		log.info("BranchServiceBL.gellAllBranches() invoked");
		return branchDao.getAllBranches();

	}
	
	public BranchDto save(BranchDto branchDto) {
		log.info("BranchServiceBL.save() invoked.");
		return branchDao.save(branchDto);
	}

	public List<BranchDto> getbranchBySBUid(Integer sbuId) {
		log.info("BranchServiceBL.getAllbySbuId()invoked");
		return branchDao.getbranchBySBUid(sbuId);
	}
	
	public BranchDto updateBranch(BranchDto branchDto) {
	    log.info("BranchServiceBL.updateBranch() invoked.");
	    return branchDao.update(branchDto);
	}
	
	public List<BranchDto> getBranchByName(String branchName) {
	    log.info("BranchServiceBL.getBranchByName() invoked");
	    return branchDao.getBranchByName(branchName);
	}
	
	public BranchDto updateBranchStatus(Integer branchId, Boolean status) {
		BranchDto branchDto = branchDao.checkBranchAvailability(branchId);
		if (branchDto != null) {
			branchDto.setIsActive(status);
			return branchDao.update(branchDto);
		} else {
			return null;
		}
	}
	
	public List<BranchDto> getBranchById(Integer id) {
		log.info("BranchServiceBL.getBranchById()invoked");
		return branchDao.getBranchById(id);
	}




}
