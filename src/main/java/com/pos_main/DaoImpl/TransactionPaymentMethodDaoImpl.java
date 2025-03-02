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

import com.pos_main.Dao.TransactionPaymentMethodDao;
import com.pos_main.Domain.Transaction;
import com.pos_main.Domain.TransactionPaymentMethod;
import com.pos_main.Dto.TransactionPaymentMethodDto;
import com.pos_main.Transformer.TransactionPaymentMethodTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionPaymentMethodDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 14, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class TransactionPaymentMethodDaoImpl extends BaseDaoImpl<TransactionPaymentMethod> implements TransactionPaymentMethodDao {
	
	@Autowired
	TransactionPaymentMethodTransformer transactionPaymentMethodTransformer;
	
	@Override
    @Transactional
    public List<TransactionPaymentMethodDto> getByTransactionId(Integer transactionId) {
        log.info("TransactionPaymentMethodDaoImpl.getByTransactionId() invoked with transactionId: {}", transactionId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<TransactionPaymentMethod> cq = cb.createQuery(TransactionPaymentMethod.class);
        Root<TransactionPaymentMethod> root = cq.from(TransactionPaymentMethod.class);

        Join<TransactionPaymentMethod, Transaction> branchJoin = root.join("transaction");
        cq.select(root).where(cb.equal(branchJoin.get("id"), transactionId));

        List<TransactionPaymentMethod> transactionPaymentMethod = getCurrentSession().createQuery(cq).getResultList();

        return transactionPaymentMethod.stream()
                .map(transactionPaymentMethodTransformer::transform)
                .collect(Collectors.toList());
    }
	
}
