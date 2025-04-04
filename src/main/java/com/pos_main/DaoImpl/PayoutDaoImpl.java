package com.pos_main.DaoImpl;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.PayoutDao;
import com.pos_main.Domain.Payout;
import com.pos_main.Dto.PayoutDto;
import com.pos_main.Transformer.PayoutTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: BranchDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c)
 * 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Slf4j
@Repository
public class PayoutDaoImpl extends BaseDaoImpl<Payout> implements PayoutDao {

	@Autowired
	PayoutTransformer payoutTransformer;
	
	

	@Override
	@Transactional
	public List<PayoutDto> getAllPayout() {
		log.info("PayoutDaoImpl.gellAllPayout() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Payout.class, "payout");
//	 	criteria.add(Restrictions.eq("isActive", true));
		List<PayoutDto> payoutDtoList = null;
		List<Payout> payoutList = (List<Payout>) criteria.list();
		if (payoutList != null && !payoutList.isEmpty()) {
			payoutDtoList = new ArrayList<>();
			for (Payout payout : payoutList) {
				payoutDtoList.add(payoutTransformer.transform(payout));
			}
		}
		return payoutDtoList;
	}

	@Override
	@Transactional
	public PayoutDto save(PayoutDto payoutDto) {
		log.info("PayoutDaoImpl.save() invoked.");
		Payout payout = payoutTransformer.reverseTransform(payoutDto);
		Payout savePayout = saveOrUpdate(payout);
		return payoutTransformer.transform(savePayout);
	}

	@Override
	@Transactional
	public PayoutDto update(PayoutDto payoutDto) {
	    log.info("PayoutDaoImpl.update() invoked.");
	    Payout payout = payoutTransformer.reverseTransform(payoutDto);
	    Payout updatedPayout = saveOrUpdate(payout);
	    return payoutTransformer.transform(updatedPayout);
	}
	
	@Override
	@Transactional
	public PayoutDto checkPayoutAvailability(Integer payoutId) {
		Criteria criteria = getCurrentSession().createCriteria(Payout.class, "payout");
		criteria.add(Restrictions.eq("payout.id", payoutId));
		Payout payout = (Payout) criteria.uniqueResult();
		PayoutDto payoutDto = null;
		if (payout != null) {
			payoutDto = payoutTransformer.transform(payout);
		}
		return payoutDto;
	}
	

}
