package com.pos_main.DaoImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.BranchDao;
import com.pos_main.Domain.Branch;
import com.pos_main.Dto.BranchDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.BranchTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Repository
public class BranchDaoImpl extends BaseDaoImpl<Branch> implements BranchDao {

	@Autowired
	BranchTransformer branchTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("BranchDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Branch> allBranchList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM branch";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
		
		// Check if status is provided in searchParams
		if (searchParams != null && searchParams.containsKey("status")) {
			Boolean status = Boolean.parseBoolean(searchParams.get("status"));
			criteria.add(Restrictions.eq("isActive", status));
		} else {
			criteria.add(Restrictions.eq("isActive", true));
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allBranchList = criteria.list();
		recordCount = allBranchList.size();
		if (allBranchList != null && !allBranchList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allBranchList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allBranchList.stream().map(Invoice -> {
				return branchTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

	@Override
	@Transactional
	public List<BranchDto> getAllBranches() {
		log.info("BranchDaoImpl.gellAllBranches() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
//	 	criteria.add(Restrictions.eq("isActive", true));
		List<BranchDto> branchDtoList = null;
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (branchList != null && !branchList.isEmpty()) {
			branchDtoList = new ArrayList<>();
			for (Branch branch : branchList) {
				branchDtoList.add(branchTransformer.transform(branch));
			}
		}
		return branchDtoList;
	}

	@Override
	@Transactional
	public BranchDto save(BranchDto branchDto) {
		log.info("BranchDaoImpl.save() invoked.");
		Branch branch = branchTransformer.reverseTransform(branchDto);
		Branch saveBranch = saveOrUpdate(branch);
		return branchTransformer.transform(saveBranch);
	}

	@Override
	public List<BranchDto> getbranchBySBUid(Integer sbuId) {
		log.info("BranchDaoImpl.getbranchBySBUid(String username) invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
		criteria.createAlias("branch.sbuId", "sbu");
		criteria.add(Restrictions.eq("sbu.id", sbuId));
	 	criteria.add(Restrictions.eq("isActive", true));

		List<BranchDto> branchDtoList = null;
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (branchList != null && !branchList.isEmpty()) {
			branchDtoList = new ArrayList<>();
			for (Branch branch : branchList) {
				branchDtoList.add(branchTransformer.transform(branch));
			}
		}
		return branchDtoList;
	}
	
	@Override
	@Transactional
	public BranchDto update(BranchDto branchDto) {
	    log.info("BranchDaoImpl.update() invoked.");
	    Branch branch = branchTransformer.reverseTransform(branchDto);
	    Branch updatedBranch = saveOrUpdate(branch);
	    return branchTransformer.transform(updatedBranch);
	}
		
	@Override
	@Transactional
	public List<BranchDto> getBranchByName(String branchName) {
	    log.info("BranchDaoImpl.getBranchByName() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
	    Root<Branch> root = cq.from(Branch.class);

	    cq.select(root).where(cb.equal(root.get("branchName"), branchName));
	    
	    Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<Branch> branchList = getCurrentSession().createQuery(cq).getResultList();

	    return branchList.stream()
	        .map(branchTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public BranchDto checkBranchAvailability(Integer branchId) {
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
		criteria.add(Restrictions.eq("branch.id", branchId));
		Branch branch = (Branch) criteria.uniqueResult();
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = branchTransformer.transform(branch);
		}
		return branchDto;
	}
	
	@Override
	@Transactional
	public List<BranchDto> getBranchById(Integer id) {
	    log.info("BranchDaoImpl.getBranchById() invoked");

	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Branch> cq = cb.createQuery(Branch.class);
	    Root<Branch> root = cq.from(Branch.class);

	    cq.select(root).where(cb.equal(root.get("id"), id));
	    
	    Criteria criteria = getCurrentSession().createCriteria(Branch.class, "branch");
	 	criteria.add(Restrictions.eq("isActive", true));

	    List<Branch> branchList = getCurrentSession().createQuery(cq).getResultList();

	    return branchList.stream()
	        .map(branchTransformer::transform)
	        .collect(Collectors.toList());
	}
	







}
