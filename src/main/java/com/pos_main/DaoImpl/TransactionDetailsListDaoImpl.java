package com.pos_main.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.TransactionDetailsListDao;
import com.pos_main.Domain.Transaction;
import com.pos_main.Domain.TransactionDetailsList;
import com.pos_main.Dto.TransactionDetailsListDto;
import com.pos_main.Transformer.TransactionDetailsListTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionDetailsListDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class TransactionDetailsListDaoImpl extends BaseDaoImpl<TransactionDetailsList> implements TransactionDetailsListDao {

	@Autowired
	TransactionDetailsListTransformer transactionDetailsListTransformer;
	
	@Override
    @Transactional
    public List<TransactionDetailsListDto> getByTransactionId(Integer transactionId) {
        log.info("TransactionDetailsListDaoImpl.getByTransactionId() invoked with transactionId: {}", transactionId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<TransactionDetailsList> cq = cb.createQuery(TransactionDetailsList.class);
        Root<TransactionDetailsList> root = cq.from(TransactionDetailsList.class);

        Join<TransactionDetailsList, Transaction> branchJoin = root.join("transaction");
        cq.select(root).where(cb.equal(branchJoin.get("id"), transactionId));

        List<TransactionDetailsList> transactionDetailsList = getCurrentSession().createQuery(cq).getResultList();

        return transactionDetailsList.stream()
                .map(transactionDetailsListTransformer::transform)
                .collect(Collectors.toList());
    }
	
	@Override
	@Transactional
	public List<TransactionDetailsListDto> getAll() {
		log.info("TransactionDetailsListDaoImpl.getAll() invoked");

		CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<TransactionDetailsList> cq = cb.createQuery(TransactionDetailsList.class);
		Root<TransactionDetailsList> root = cq.from(TransactionDetailsList.class);

		cq.select(root);

		List<TransactionDetailsList> transactionDetailsList = getCurrentSession().createQuery(cq).getResultList();

		return transactionDetailsList.stream()
				.map(transactionDetailsListTransformer::transform)
				.collect(Collectors.toList());
	}
	
	
}
