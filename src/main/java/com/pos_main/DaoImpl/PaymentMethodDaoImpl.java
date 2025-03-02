package com.pos_main.DaoImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.PaymentMethodDao;
import com.pos_main.Domain.PaymentMethod;
import com.pos_main.Dto.PaymentMethodDto;
import com.pos_main.Transformer.PaymentMethodTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod> implements PaymentMethodDao{
	
	@Autowired
	PaymentMethodTransformer paymentMethodTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	@Transactional
	public PaymentMethodDto save(PaymentMethodDto paymentMethodDto) {
		log.info("PaymentMethodDaoImpl.save() invoked.");
		PaymentMethod paymentMethod = paymentMethodTransformer.reverseTransform(paymentMethodDto);
		PaymentMethod savePaymentMethod = saveOrUpdate(paymentMethod);
		return paymentMethodTransformer.transform(savePaymentMethod);
	}
}
