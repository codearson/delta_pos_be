package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
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
import com.pos_main.Dao.ProductCategoryDao;
import com.pos_main.Domain.ProductCategory;
import com.pos_main.Dto.ProductCategoryDto;
import com.pos_main.Transformer.ProductCategoryTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 14, 2024 4:53:20 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory> implements ProductCategoryDao{

	@Autowired
	ProductCategoryTransfomer productCategoryTransfomer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public List<ProductCategoryDto> getAll() {
		log.info("ProductCategoryDaoImpl.gellAll() invoked");
		Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");
		
		// Next line using for filter only active product Category
		criteria.add(Restrictions.eq("isActive", true));
		
		List<ProductCategoryDto> productCategoryDtoList = null;
		List<ProductCategory> productCategoryList = (List<ProductCategory>) criteria.list();
		if (productCategoryList != null && !productCategoryList.isEmpty()) {
			productCategoryDtoList = new ArrayList<>();
			for (ProductCategory productCategory : productCategoryList) {
				productCategoryDtoList.add(productCategoryTransfomer.transform(productCategory));
			}
		}
		return productCategoryDtoList;
	}

	@Override
	@Transactional
	public ProductCategoryDto save(ProductCategoryDto productCategoryDto) {
		log.info("ProductCategoryDaoImpl.save() invoked.");
		ProductCategory productCategory = productCategoryTransfomer.reverseTransform(productCategoryDto);
		ProductCategory saveProductCategory = saveOrUpdate(productCategory);
		return productCategoryTransfomer.transform(saveProductCategory);
	}
	
	@Override
	@Transactional
	public List<ProductCategoryDto> getAllByName(String productCategoryName) {
	    log.info("ProductCategoryDaoImpl.getAllByName() invoked");
	   
	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    
	    CriteriaQuery<ProductCategory> cq = cb.createQuery(ProductCategory.class);
	    Root<ProductCategory> root = cq.from(ProductCategory.class);

	    cq.select(root).where(cb.equal(root.get("productCategoryName"),productCategoryName));
	    
	    Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");
	 	criteria.add(Restrictions.eq("isActive", true));

	    
	    List<ProductCategory> productCategoryList = getCurrentSession().createQuery(cq).getResultList();
	    return productCategoryList.stream()
	            .map(productCategoryTransfomer::transform)
	            .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ProductCategoryDto update(ProductCategoryDto productCategoryDto) {
	    log.info("ProductCategoryDaoImpl.update() invoked.");

	   
	    ProductCategory productCategory = productCategoryTransfomer.reverseTransform(productCategoryDto);
	    ProductCategory updatedProductCategory = saveOrUpdate(productCategory);

	    return productCategoryTransfomer.transform(updatedProductCategory);
	}
	
	@Override
	@Transactional
	public ProductCategoryDto checkUserAvailability(Integer Id) {
		Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class, "productCategory");
		criteria.add(Restrictions.eq("productCategory.id", Id));
		ProductCategory productCategory = (ProductCategory) criteria.uniqueResult();
		ProductCategoryDto productCategoryDto = null;
		if (productCategory != null) {
			productCategoryDto = productCategoryTransfomer.transform(productCategory);
		}
		return productCategoryDto;
	}




	
}
