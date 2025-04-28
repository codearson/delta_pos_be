package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pos_main.Dao.ProductCategoryDao;
import com.pos_main.Domain.ProductCategory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductCategoryDto;
import com.pos_main.Transformer.ProductCategoryTransfomer;
import com.pos_main.Service.Utils.HttpReqRespUtils;

@Repository
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory> implements ProductCategoryDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryDaoImpl.class);

    @Autowired
    private ProductCategoryTransfomer transformer;

    @Override
    @Transactional
    public List<ProductCategoryDto> getAll() {
        logger.info("Executing getAll() in ProductCategoryDaoImpl");
        try {
            Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class);
            List<ProductCategory> productCategories = criteria.list();
            return productCategories.stream()
                .map(transformer::transform)
                .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAll() of ProductCategoryDaoImpl: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageProductCategory(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
        logger.info("Executing getAllPageProductCategory() in ProductCategoryDaoImpl");
        try {
            Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class);
            
            if (status != null) {
                criteria.add(Restrictions.eq("isActive", status));
            }
            
            if (searchParams != null && !searchParams.isEmpty()) {
                for (Map.Entry<String, String> entry : searchParams.entrySet()) {
                    if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                        // Only add restrictions for valid properties of ProductCategory
                        String propertyName = entry.getKey();
                        if (isValidProductCategoryProperty(propertyName)) {
                            criteria.add(Restrictions.like(propertyName, "%" + entry.getValue() + "%"));
                        } else {
                            logger.warn("Ignoring invalid search parameter: " + propertyName);
                        }
                    }
                }
            }
            
            // Get total count
            criteria.setProjection(Projections.rowCount());
            Long totalCount = (Long) criteria.uniqueResult();
            
            // Reset criteria for data
            criteria = getCurrentSession().createCriteria(ProductCategory.class);
            if (status != null) {
                criteria.add(Restrictions.eq("isActive", status));
            }
            if (searchParams != null && !searchParams.isEmpty()) {
                for (Map.Entry<String, String> entry : searchParams.entrySet()) {
                    if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                        // Only add restrictions for valid properties of ProductCategory
                        String propertyName = entry.getKey();
                        if (isValidProductCategoryProperty(propertyName)) {
                            criteria.add(Restrictions.like(propertyName, "%" + entry.getValue() + "%"));
                        }
                    }
                }
            }
            
            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);
            criteria.addOrder(Order.desc("id"));
            
            List<ProductCategory> productCategories = criteria.list();
            List<ProductCategoryDto> productCategoryDtos = productCategories.stream()
                .map(transformer::transform)
                .collect(java.util.stream.Collectors.toList());
            
            PaginatedResponseDto response = HttpReqRespUtils.paginatedResponseMapper(productCategories, pageNumber, pageSize, totalCount.intValue());
            response.setPayload(productCategoryDtos);
            
            return response;
        } catch (Exception e) {
            logger.error("Error in getAllPageProductCategory() of ProductCategoryDaoImpl: " + e.getMessage());
            return new PaginatedResponseDto();
        }
    }

    @Override
    @Transactional
    public ProductCategoryDto save(ProductCategoryDto productCategoryDto) {
        logger.info("Executing save() in ProductCategoryDaoImpl");
        try {
            ProductCategory productCategory = transformer.reverseTransform(productCategoryDto);
            getCurrentSession().save(productCategory);
            return transformer.transform(productCategory);
        } catch (Exception e) {
            logger.error("Error in save() of ProductCategoryDaoImpl: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public List<ProductCategoryDto> getAllByName(String name) {
        logger.info("Executing getAllByName() in ProductCategoryDaoImpl");
        try {
            Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class);
            criteria.add(Restrictions.like("productCategoryName", "%" + name + "%"));
            List<ProductCategory> productCategories = criteria.list();
            return productCategories.stream()
                .map(transformer::transform)
                .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in getAllByName() of ProductCategoryDaoImpl: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public ProductCategoryDto update(ProductCategoryDto productCategoryDto) {
        logger.info("Executing update() in ProductCategoryDaoImpl");
        try {
            ProductCategory productCategory = transformer.reverseTransform(productCategoryDto);
            getCurrentSession().update(productCategory);
            return transformer.transform(productCategory);
        } catch (Exception e) {
            logger.error("Error in update() of ProductCategoryDaoImpl: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public ProductCategoryDto checkUserAvailability(Integer id) {
        logger.info("Executing checkUserAvailability() in ProductCategoryDaoImpl");
        try {
            Criteria criteria = getCurrentSession().createCriteria(ProductCategory.class);
            criteria.add(Restrictions.eq("id", id));
            ProductCategory productCategory = (ProductCategory) criteria.uniqueResult();
            return productCategory != null ? transformer.transform(productCategory) : null;
        } catch (Exception e) {
            logger.error("Error in checkUserAvailability() of ProductCategoryDaoImpl: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if the given property name is a valid property of the ProductCategory class
     * @param propertyName The property name to check
     * @return true if the property is valid, false otherwise
     */
    private boolean isValidProductCategoryProperty(String propertyName) {
        // List of valid properties in ProductCategory class
        return "id".equals(propertyName) || 
               "productCategoryName".equals(propertyName) || 
               "isActive".equals(propertyName) || 
               "agevalidation".equals(propertyName);
    }
}
