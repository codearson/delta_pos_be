package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ShopDetailsDao;
import com.pos_main.Domain.Product;
import com.pos_main.Domain.ShopDetails;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ShopDetailsDto;
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

}
