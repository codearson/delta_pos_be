package com.pos_main.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.TaxDao;
import com.pos_main.Domain.Tax;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.TaxTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TaxDaoImpl extends BaseDaoImpl<Tax> implements TaxDao{
	
	@Autowired
	TaxTransformer taxTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public TaxDto save(TaxDto taxDto) {
		log.info("TaxDaoImpl.save() invoked.");
		Tax tax = taxTransformer.reverseTransform(taxDto);
		Tax saveTax = saveOrUpdate(tax);
		return taxTransformer.transform(saveTax);
	}
	
	@Override
    @Transactional
    public TaxDto update(TaxDto taxDto) {
        log.info("TaxDaoImpl.update() invoked");
        
        Tax tax = taxTransformer.reverseTransform(taxDto);
        Tax updatedTax = saveOrUpdate(tax);
        
        return taxTransformer.transform(updatedTax);
    }

    @Override
    @Transactional
    public TaxDto checkTaxAvailability(Integer id) {
        Criteria criteria = getCurrentSession().createCriteria(Tax.class, "tax");
        criteria.add(Restrictions.eq("tax.id", id));
        Tax tax = (Tax) criteria.uniqueResult();
        TaxDto taxDto = null;
        if (tax != null) {
            taxDto = taxTransformer.transform(tax);
        }
        return taxDto;
    }
	
	@Override
    @Transactional
    public List<TaxDto> getTaxByName(Double taxPercentage) {
        log.info("TaxDaoImpl.getTaxByName() invoked");
        
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tax> cq = cb.createQuery(Tax.class);
        Root<Tax> root = cq.from(Tax.class);
        
        cq.select(root)
            .where(
                cb.and(
                    cb.equal(root.get("taxPercentage"), taxPercentage),
                    cb.equal(root.get("isActive"), true)
                )
            );
        
        List<Tax> taxList = getCurrentSession().createQuery(cq).getResultList();
        return taxList.stream()
            .map(taxTransformer::transform)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TaxDto> getAll() {
        log.info("TaxDaoImpl.getAll() invoked");
        
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Tax> cq = cb.createQuery(Tax.class);
        Root<Tax> root = cq.from(Tax.class);
        
        cq.select(root);
        
        List<Tax> taxList = getCurrentSession().createQuery(cq).getResultList();
        return taxList.stream()
            .map(taxTransformer::transform)
            .collect(Collectors.toList());
    }
    
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageTax(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("TaxDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Tax> allTaxList = null;
		
		// Create criteria for count query
		Criteria countCriteria = getCurrentSession().createCriteria(Tax.class);
		if (status != null) {
			countCriteria.add(Restrictions.eq("isActive", status));
		}
		countCriteria.setProjection(Projections.rowCount());
		Long count = (Long) countCriteria.uniqueResult();

		if (pageSize == 0) {
			pageSize = count.intValue();
		}

		Criteria criteria = getCurrentSession().createCriteria(Tax.class, "tax");
		if (status != null) {
			criteria.add(Restrictions.eq("isActive", status));
		}
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allTaxList = criteria.list();

		if (allTaxList != null && !allTaxList.isEmpty()) {
			paginatedResponseDto = new PaginatedResponseDto();
			paginatedResponseDto.setPageNumber(pageNumber);
			paginatedResponseDto.setPageSize(pageSize);
			paginatedResponseDto.setTotalRecords(count.intValue());
			paginatedResponseDto.setPayload(allTaxList.stream()
					.map(taxTransformer::transform)
					.collect(Collectors.toList()));
		} else {
			// Return empty paginated response with correct structure
			paginatedResponseDto = new PaginatedResponseDto();
			paginatedResponseDto.setPageNumber(pageNumber);
			paginatedResponseDto.setPageSize(pageSize);
			paginatedResponseDto.setTotalRecords(count.intValue());
			paginatedResponseDto.setPayload(new ArrayList<>());
		}
		return paginatedResponseDto;
	}
}
