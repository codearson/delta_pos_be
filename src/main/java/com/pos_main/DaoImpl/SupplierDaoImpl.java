package com.pos_main.DaoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.SupplierDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.Supplier;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.SupplierDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.SupplierTransfomer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SupplierDaoImpl extends BaseDaoImpl<Supplier> implements SupplierDao {

	@Autowired
	SupplierTransfomer supplierTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	public SupplierDto saveSupplier(SupplierDto supplierDto) {
		log.info("SupplierDaoImpl.saveSuppler() invoked.");
		supplierDto.setCreatedDate(LocalDateTime.now());
		Supplier supplier = supplierTransformer.reverseTransform(supplierDto);
		Supplier saveSupplier = saveOrUpdate(supplier);
		return supplierTransformer.transform(saveSupplier);
		
	}
	@Override
    public List<SupplierDto> getSupplierByName(String name) {
        org.hibernate.Session session = null;
        List<SupplierDto> supplierDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Supplier.class, "supplier");
            criteria.add(Restrictions.eq("supplier.name", name));
    	 	criteria.add(Restrictions.eq("isActive", true));
            List<Supplier> supplierList = criteria.list();

            if (supplierList != null && !supplierList.isEmpty()) {
                supplierDtoList = new ArrayList<>();
                for (Supplier supplier : supplierList) {
                    supplierDtoList.add(supplierTransformer.transform(supplier));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return supplierDtoList;
    }
	
	@Override
	@Transactional
	public SupplierDto updateSupplier(SupplierDto supplierDto) {
	    log.info("SupplierDaoImpl.updateSupplier() invoked.");
	    supplierDto.setCreatedDate(LocalDateTime.now());
	    Supplier supplier = supplierTransformer.reverseTransform(supplierDto);
	    Supplier updateSupplier = saveOrUpdate(supplier);
	    return supplierTransformer.transform(updateSupplier);
	}
	
	@Override
	@Transactional
	public SupplierDto checkProductAvailability(Integer supplierId) {
		Criteria criteria = getCurrentSession().createCriteria(Supplier.class, "Supplier");
		criteria.add(Restrictions.eq("Supplier.id", supplierId));
		Supplier supplier = (Supplier) criteria.uniqueResult();
		SupplierDto supplierDto = null;
		if (supplier != null) {
			supplierDto = supplierTransformer.transform(supplier);
		}
		return supplierDto;
	}
	
	@Override
    public List<SupplierDto> getSupplierById(Integer id) {
        org.hibernate.Session session = null;
        List<SupplierDto> supplierDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Supplier.class, "supplier");
            criteria.add(Restrictions.eq("isActive", true));
            criteria.add(Restrictions.eq("supplier.id", id));
            List<Supplier> supplierList = criteria.list();

            if (supplierList != null && !supplierList.isEmpty()) {
            	supplierDtoList = new ArrayList<>();
                for (Supplier supplier : supplierList) {
                	supplierDtoList.add(supplierTransformer.transform(supplier));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return supplierDtoList;
    }
	
	@Override
	@Transactional
	public List<SupplierDto> getAllSupplier() {
		log.info("SupplierDaoImpl.getAllSupplier() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Supplier.class, "supplier");
		criteria.addOrder(Order.asc("id"));
		List<SupplierDto> supplierDtoList = null;
		List<Supplier> supplierList = (List<Supplier>) criteria.list();
		if (supplierList != null && !supplierList.isEmpty()) {
			supplierDtoList = new ArrayList<>();
			for (Supplier supplier : supplierList) {
				supplierDtoList.add(supplierTransformer.transform(supplier));
			}
		}
		return supplierDtoList;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageSupplier(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("SupplierDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Supplier> allSupplierList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM supplier";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Supplier.class, "supplier");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allSupplierList = criteria.list();
		recordCount = allSupplierList.size();
		if (allSupplierList != null && !allSupplierList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allSupplierList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allSupplierList.stream().map(Invoice -> {
				return supplierTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}


}
