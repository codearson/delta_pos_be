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

import com.pos_main.Dao.TransactionEmployeeDao;
import com.pos_main.Domain.Transaction;
import com.pos_main.Domain.TransactionEmployee;
import com.pos_main.Dto.TransactionEmployeeDto;
import com.pos_main.Transformer.TransactionEmployeeTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionEmployeeDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 17:46:10
 * @version 1.0
 **/

@Slf4j
@Repository
public class TransactionEmployeeDaoImpl extends BaseDaoImpl<TransactionEmployee> implements TransactionEmployeeDao {

    @Autowired
    TransactionEmployeeTransformer transactionEmployeeTransformer;
    
    @Override
    @Transactional
    public List<TransactionEmployeeDto> getByTransactionId(Integer transactionId) {
        log.info("TransactionEmployeeDaoImpl.getByTransactionId() invoked with transactionId: {}", transactionId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<TransactionEmployee> cq = cb.createQuery(TransactionEmployee.class);
        Root<TransactionEmployee> root = cq.from(TransactionEmployee.class);

        Join<TransactionEmployee, Transaction> branchJoin = root.join("transaction");
        cq.select(root).where(cb.equal(branchJoin.get("id"), transactionId));

        List<TransactionEmployee> transactionEmployeeList = getCurrentSession().createQuery(cq).getResultList();

        return transactionEmployeeList.stream()
                .map(transactionEmployeeTransformer::transform)
                .collect(Collectors.toList());
    }
}