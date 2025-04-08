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
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ShopDetailsDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.ShopDetails;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ShopDetailsDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.ShopDetailsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ShopDetailsDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Feb 2025
 * @time 17:38:24
 * @version 1.0
 **/

@Slf4j
@Repository
public class ShopDetailsDaoImpl extends BaseDaoImpl<ShopDetails> implements ShopDetailsDao {
	
	@Autowired
	ShopDetailsTransformer shopDetailsTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public ShopDetailsDto save(ShopDetailsDto shopDetailsDto) {
		log.info("ProductDaoImpl.save() invoked.");
		ShopDetails shopDetails = shopDetailsTransformer.reverseTransform(shopDetailsDto);
		ShopDetails saveShopDetails = saveOrUpdate(shopDetails);
		return shopDetailsTransformer.transform(saveShopDetails);
	}
	
	@Override
	@Transactional
	public List<ShopDetailsDto> getAll() {
		log.info("ShopDetailsDaoImpl.getAll() invoked");
		Criteria criteria = getCurrentSession().createCriteria(ShopDetails.class, "shopDetails");
		criteria.addOrder(Order.asc("id"));
		List<ShopDetailsDto> shopDetailsDtoList = null;
		List<ShopDetails> shopDetailsList = (List<ShopDetails>) criteria.list();
		if (shopDetailsList != null && !shopDetailsList.isEmpty()) {
			shopDetailsDtoList = new ArrayList<>();
			for (ShopDetails shopDetails : shopDetailsList) {
				shopDetailsDtoList.add(shopDetailsTransformer.transform(shopDetails));
			}
		}
		return shopDetailsDtoList;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageShopDetails(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ShopDetailsDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<ShopDetails> allShopDetailsList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM shop_details";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(ShopDetails.class, "shopDetails");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allShopDetailsList = criteria.list();
		recordCount = allShopDetailsList.size();
		if (allShopDetailsList != null && !allShopDetailsList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allShopDetailsList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allShopDetailsList.stream().map(Invoice -> {
				return shopDetailsTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
	
	@Override
	@Transactional
	public List<ShopDetailsDto> getByName (String name) {
	    log.info("ShopDetailsDaoImpl.getByName() invoked");
		   
	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<ShopDetails> cq = cb.createQuery(ShopDetails.class);
	    Root<ShopDetails> root = cq.from(ShopDetails.class);

	    cq.select(root).where(cb.equal(root.get("name"),name));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ShopDetails.class, "shopDetails");
	 	criteria.add(Restrictions.eq("isActive", true));
	    
	    List<ShopDetails> shopDetailsList = getCurrentSession().createQuery(cq).getResultList();
	    return shopDetailsList.stream()
	            .map(shopDetailsTransformer::transform)
	            .collect(Collectors.toList());
	}

}
