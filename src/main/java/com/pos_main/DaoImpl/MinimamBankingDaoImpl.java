package com.pos_main.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.MinimamBankingDao;
import com.pos_main.Domain.MinimamBanking;
import com.pos_main.Dto.MinimamBankingDto;
import com.pos_main.Transformer.MinimamBankingTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: MinimamBankingDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:03:56
 * @version 1.0
 **/

@Slf4j
@Repository
public class MinimamBankingDaoImpl extends BaseDaoImpl<MinimamBanking> implements MinimamBankingDao {

    @Autowired
    MinimamBankingTransformer minimamBankingTransformer;

    @Override
    @Transactional
    public MinimamBankingDto save(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingDaoImpl.save() invoked.");
        MinimamBanking minimamBanking = minimamBankingTransformer.reverseTransform(minimamBankingDto);
        MinimamBanking savedMinimamBanking = saveOrUpdate(minimamBanking);
        return minimamBankingTransformer.transform(savedMinimamBanking);
    }

    @Override
    @Transactional
    public MinimamBankingDto update(MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingDaoImpl.update() invoked");
        MinimamBanking minimamBanking = minimamBankingTransformer.reverseTransform(minimamBankingDto);
        MinimamBanking updatedMinimamBanking = saveOrUpdate(minimamBanking);
        return minimamBankingTransformer.transform(updatedMinimamBanking);
    }

    @Override
    @Transactional
    public List<MinimamBankingDto> getAll() {
        log.info("MinimamBankingDaoImpl.getAll() invoked");
        
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<MinimamBanking> cq = cb.createQuery(MinimamBanking.class);
        Root<MinimamBanking> root = cq.from(MinimamBanking.class);
        
        cq.select(root);
        
        List<MinimamBanking> minimamBankingList = getCurrentSession().createQuery(cq).getResultList();
        return minimamBankingList.stream()
            .map(minimamBankingTransformer::transform)
            .collect(Collectors.toList());
    }
}
