package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ProductDao;
import com.pos_main.Domain.Branch;
import com.pos_main.Domain.Product;
import com.pos_main.Domain.ProductCategory;
import com.pos_main.Domain.Tax;
import com.pos_main.Domain.Transaction;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.ProductTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 17, 2024 10:33:38 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

	@Autowired
	ProductTransformer productTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public ProductDto save(ProductDto productDto) {
		log.info("ProductDaoImpl.save() invoked.");
		Product product = productTransformer.reverseTransform(productDto);
		Product saveProduct = saveOrUpdate(product);
		return productTransformer.transform(saveProduct);
	}

	@Override
	@Transactional
	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams, Boolean status) {
		log.info("ProductDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Product> allProductList = null;

		Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");
		criteria.add(Restrictions.eq("product.isActive", status));
		
		// Add restrictions to exclude products with "Non Scan" and "Custom" categories
		criteria.createAlias("product.productCategory", "productCategory");
		criteria.add(Restrictions.ne("productCategory.productCategoryName", "Non Scan"));
		criteria.add(Restrictions.ne("productCategory.productCategoryName", "Custom"));
		
		criteria.addOrder(Order.desc("id"));
		
		String countString = "SELECT COUNT(*) FROM product p JOIN product_category pc ON p.product_category = pc.id WHERE p.is_active = " + (status ? "1" : "0") + 
		                     " AND pc.product_category_name != 'Non Scan' AND pc.product_category_name != 'Custom'";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);
		
		if (pageSize == 0) {
			pageSize = count;
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		
		allProductList = criteria.list();
		
		if (allProductList != null && !allProductList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allProductList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allProductList.stream()
					.map(product -> productTransformer.transform(product))
					.collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

	@Override
	@Transactional
	public List<ProductDto> getAllProducts() {
		log.info("ProductDaoImpl.getAllProducts() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");
		criteria.add(Restrictions.eq("product.isActive", true));
		criteria.addOrder(Order.desc("id"));
		List<ProductDto> productDtoList = null;
		List<Product> productList = (List<Product>) criteria.list();
		if (productList != null && !productList.isEmpty()) {
			productDtoList = new ArrayList<>();
			for (Product product : productList) {
				productDtoList.add(productTransformer.transform(product));
			}
		}
		return productDtoList;
	}
	
	@Override
	@Transactional
    public List<ProductDto> getProductByBarcode(String barcode) {
        org.hibernate.Session session = null;
        List<ProductDto> productDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Product.class, "product");
            criteria.add(Restrictions.eq("isActive", true));
            criteria.add(Restrictions.eq("product.barcode", barcode));
            List<Product> productList = criteria.list();

            if (productList != null && !productList.isEmpty()) {
                productDtoList = new ArrayList<>();
                for (Product product : productList) {
                    productDtoList.add(productTransformer.transform(product));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return productDtoList;
    }

	@Override
	@Transactional
	public List<ProductDto> getProductByName (String name) {
	    log.info("ProductDaoImpl.getProductByName() invoked");

	   
	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Product> cq = cb.createQuery(Product.class);
	    Root<Product> root = cq.from(Product.class);

	    cq.select(root).where(cb.equal(root.get("name"),name));
	    
	    Criteria criteria = getCurrentSession().createCriteria(Product.class, "product");
	 	criteria.add(Restrictions.eq("isActive", true));
	    
	    List<Product> productList = getCurrentSession().createQuery(cq).getResultList();
	    return productList.stream()
	            .map(productTransformer::transform)
	            .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public ProductDto updateProduct(ProductDto productDto) {
	    log.info("ProductDaoImpl.updateProduct() invoked.");
	    Product product = productTransformer.reverseTransform(productDto);
	    Product updateProduct = saveOrUpdate(product);
	    return productTransformer.transform(updateProduct);
	}
	
	@Override
	@Transactional
	public ProductDto checkProductAvailability(Integer productId) {
		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		criteria.add(Restrictions.eq("Product.id", productId));
		Product product = (Product) criteria.uniqueResult();
		ProductDto productDto = null;
		if (product != null) {
			productDto = productTransformer.transform(product);
		}
		return productDto;
	}
	
	@Override
    public List<ProductDto> getProductById(Integer id) {
        org.hibernate.Session session = null;
        List<ProductDto> productDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Product.class, "product");
            criteria.add(Restrictions.eq("isActive", true));
            criteria.add(Restrictions.eq("product.id", id));
            List<Product> productList = criteria.list();

            if (productList != null && !productList.isEmpty()) {
            	productDtoList = new ArrayList<>();
                for (Product product : productList) {
                	productDtoList.add(productTransformer.transform(product));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return productDtoList;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getByProductCategoryName(int pageNumber, int pageSize, Map<String, String> searchParams, String categoryName, Boolean status) {
        log.info("ProductDaoImpl.getByProductCategoryName() invoked");
        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        Join<Product, ProductCategory> productCategoryJoin = root.join("productCategory");
        
        cq.select(root)
          .where(
              cb.and(
                  cb.equal(productCategoryJoin.get("productCategoryName"), categoryName),
                  cb.equal(root.get("isActive"), status)
              )
          )
          .orderBy(cb.desc(root.get("id")));
        
        // Get total count
        String countString = "SELECT COUNT(*) FROM product p JOIN product_category pc ON p.product_category = pc.id " +
                           "WHERE p.is_active = " + (status ? "1" : "0") + " AND pc.product_category_name = '" + categoryName + "'";
        int count = jdbcTemplate.queryForObject(countString, Integer.class);
        
        if (pageSize == 0) {
            pageSize = count;
        }
        
        productList = getCurrentSession().createQuery(cq)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        
        if (productList != null && !productList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productList.stream()
                    .map(product -> productTransformer.transform(product))
                    .collect(Collectors.toList()));
        }
        return paginatedResponseDto;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getByTaxPercentage(int pageNumber, int pageSize, Map<String, String> searchParams, Double taxPercentage, Boolean status) {
        log.info("ProductDaoImpl.getByTaxPercentage() invoked");
        PaginatedResponseDto paginatedResponseDto = null;
        List<Product> productList = null;

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        Join<Product, Tax> taxJoin = root.join("tax");
        
        cq.select(root)
          .where(
              cb.and(
                  cb.equal(taxJoin.get("taxPercentage"), taxPercentage),
                  cb.equal(root.get("isActive"), status)
              )
          )
          .orderBy(cb.desc(root.get("id")));
        
        String countString = "SELECT COUNT(*) FROM product p JOIN tax t ON p.tax = t.id " +
                           "WHERE p.is_active = " + (status ? "1" : "0") + " AND t.tax_percentage = " + taxPercentage;
        int count = jdbcTemplate.queryForObject(countString, Integer.class);
        
        if (pageSize == 0) {
            pageSize = count;
        }
        
        productList = getCurrentSession().createQuery(cq)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        
        if (productList != null && !productList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(productList.stream()
                    .map(product -> productTransformer.transform(product))
                    .collect(Collectors.toList()));
        }
        return paginatedResponseDto;
    }

}
