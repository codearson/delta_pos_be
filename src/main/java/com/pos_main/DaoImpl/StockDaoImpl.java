package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.StockDao;
import com.pos_main.Domain.Product;
import com.pos_main.Domain.Stock;
import com.pos_main.Dto.StockDto;
import com.pos_main.Transformer.ProductTransformer;
import com.pos_main.Transformer.StockTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:40:38
 * @version 1.0
 **/

@Slf4j
@Repository
public class StockDaoImpl extends BaseDaoImpl<Stock> implements StockDao{
	
	@Autowired
	StockTransformer stockTransformer;
	
	@Autowired
	ProductTransformer productTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public StockDto save(StockDto stockDto) {
		log.info("StockDaoImpl.save() invoked.");
		Stock stock = stockTransformer.reverseTransform(stockDto);
		Stock saveStock = saveOrUpdate(stock);
		return stockTransformer.transform(saveStock);
	}
	
	@Override
	@Transactional
	public List<StockDto> getAllStock() {
		log.info("StockDaoImpl.getAllStock() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Stock.class, "stock");
	 	criteria.add(Restrictions.eq("isActive", true));
		List<StockDto> stockDtoList = null;
		List<Stock> stockList = (List<Stock>) criteria.list();
		if (stockList != null && !stockList.isEmpty()) {
			stockDtoList = new ArrayList<>();
			for (Stock stock : stockList) {
				stockDtoList.add(stockTransformer.transform(stock));
			}
		}
		return stockDtoList;
	}
	
	@Override
	@Transactional
	public StockDto updateStock(StockDto stockDto) {
	    log.info("StockDaoImpl.updateStock() invoked.");
	    Stock stock = stockTransformer.reverseTransform(stockDto);
	    Stock updateStock = saveOrUpdate(stock);
	    return stockTransformer.transform(updateStock);
	}
	
	@Override
	@Transactional
	public StockDto checkProductAvailability(Integer stockId) {
		Criteria criteria = getCurrentSession().createCriteria(Stock.class, "Stock");
		criteria.add(Restrictions.eq("Stock.id", stockId));
		Stock stock = (Stock) criteria.uniqueResult();
		StockDto stockDto = null;
		if (stock != null) {
			stockDto = stockTransformer.transform(stock);
		}
		return stockDto;
	}
	
	@Override
    public List<StockDto> getStockById(Integer id) {
        org.hibernate.Session session = null;
        List<StockDto> stockDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Stock.class, "stock");
            criteria.add(Restrictions.eq("stock.id", id));
            criteria.add(Restrictions.eq("isActive", true));
            
            List<Stock> stockList = criteria.list();

            if (stockList != null && !stockList.isEmpty()) {
                stockDtoList = new ArrayList<>();
                for (Stock stock : stockList) {
                    stockDtoList.add(stockTransformer.transform(stock));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return stockDtoList;
    }
	
	@Override
	@Transactional
	public List<StockDto> getStockByProductCategoryId(Integer productCategoryId) {
	    log.info("StockDaoImpl.getStockByProductCategoryId() invoked");
	 
	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
	    Root<Stock> stockRoot = cq.from(Stock.class);
	    
	    Join<Stock, Product> productJoin = stockRoot.join("product");
	 
	    cq.select(stockRoot)
	      .where(cb.equal(productJoin.get("productCategory"), productCategoryId));
	 
	    List<Stock> stockList = getCurrentSession().createQuery(cq).getResultList();
	 
	    return stockList.stream()
	        .map(stockTransformer::transform)
	        .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<StockDto> getStockByProductId(Integer productId) {
	    log.info("StockDaoImpl.getStockByProductId() invoked");
	 
	    CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
	    CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
	    Root<Stock> root = cq.from(Stock.class);
	    
	    Join<Stock, Product> branchJoin = root.join("product");
	 
	    cq.select(root)
	      .where(cb.equal(branchJoin.get("id"), productId));
	 
	    List<Stock> stock = getCurrentSession().createQuery(cq).getResultList();
	 
	    return stock.stream()
	        .map(stockTransformer::transform)
	        .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<StockDto> getStockByQuantityRange (Integer minQuantity, Integer maxQuantity) {
		log.info("StockDaoImpl.getStockByQuantityRange() invoked");
		 
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
        Root<Stock> root = cq.from(Stock.class);
 
        cq.select(root).where(
            cb.between(root.get("quantity"), minQuantity, maxQuantity)
        );
 
        List<Stock> stockList = entityManager.createQuery(cq).getResultList();
 
        return stockList.stream()
                .map(stockTransformer::transform)
                .collect(Collectors.toList());
	}
}
