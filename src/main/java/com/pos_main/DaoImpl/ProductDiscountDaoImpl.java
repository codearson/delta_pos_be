package com.pos_main.DaoImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ProductDiscountDao;
import com.pos_main.Domain.ProductDiscount;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDiscountDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.ProductDiscountTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductDiscountDaoImpl extends BaseDaoImpl<ProductDiscount> implements ProductDiscountDao {

	@Autowired
	ProductDiscountTransformer productDiscountTransformer;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public ProductDiscountDto save(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountDaoImpl.save() invoked.");
		ProductDiscount productDiscount = productDiscountTransformer.reverseTransform(productDiscountDto);
		ProductDiscount saveProductDiscount = saveOrUpdate(productDiscount);
		return productDiscountTransformer.transform(saveProductDiscount);
	}

	@Override
	@Transactional
	public ProductDiscountDto update(ProductDiscountDto productDiscountDto) {
		log.info("ProductDiscountDaoImpl.update() invoked");
		ProductDiscount productDiscount = productDiscountTransformer.reverseTransform(productDiscountDto);
		ProductDiscount updatedProductDiscount = saveOrUpdate(productDiscount);
		return productDiscountTransformer.transform(updatedProductDiscount);
	}

	@Override
	@Transactional
	public ProductDiscountDto checkPayoutCategoryAvailability(Integer id) {
		log.info("ProductDiscountDaoImpl.checkPayoutCategoryAvailability() invoked with id: {}", id);
		Criteria criteria = getCurrentSession().createCriteria(ProductDiscount.class);
		criteria.add(org.hibernate.criterion.Restrictions.eq("id", id));
		ProductDiscount productDiscount = (ProductDiscount) criteria.uniqueResult();
		ProductDiscountDto productDiscountDto = null;

		if (productDiscount != null) {
			if (productDiscount.getEndDate() != null && productDiscount.getEndDate().isBefore(LocalDate.now())) {
				productDiscount.setIsActive(false);
				saveOrUpdate(productDiscount);
			}
			productDiscountDto = productDiscountTransformer.transform(productDiscount);
		}
		return productDiscountDto;
	}

	@Override
	@Transactional
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("ProductDiscountDaoImpl.getAllPage() invoked");
		PaginatedResponseDto paginatedResponseDto = null;

		String countString = "SELECT COUNT(*) FROM product_discount";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(ProductDiscount.class);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductDiscount> allProductDiscountList = criteria.list();

		// Update isActive if endDate has passed
		for (ProductDiscount pd : allProductDiscountList) {
			if (pd.getEndDate() != null && pd.getEndDate().isBefore(LocalDate.now())) {
				pd.setIsActive(false);
				saveOrUpdate(pd); // Make sure this properly updates the DB
			}
		}

		if (allProductDiscountList != null && !allProductDiscountList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allProductDiscountList, pageNumber,
					pageSize, count);
			paginatedResponseDto.setPayload(allProductDiscountList.stream().map(productDiscountTransformer::transform)
					.collect(Collectors.toList()));
		}

		return paginatedResponseDto;
	}

}
