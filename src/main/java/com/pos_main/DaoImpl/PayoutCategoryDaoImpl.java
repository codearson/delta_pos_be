package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.PayoutCategoryDao;
import com.pos_main.Domain.PayoutCategory;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutCategoryDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.PayoutCategoryTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PayoutCategoryDaoImpl extends BaseDaoImpl<PayoutCategory> implements PayoutCategoryDao {
    
    @Autowired
    PayoutCategoryTransformer payoutCategoryTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public PayoutCategoryDto save(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryDaoImpl.save() invoked");
        PayoutCategory payoutCategory = payoutCategoryTransformer.reverseTransform(payoutCategoryDto);
        PayoutCategory savedPayoutCategory = saveOrUpdate(payoutCategory);
        return payoutCategoryTransformer.transform(savedPayoutCategory);
    }

    @Override
    @Transactional
    public PayoutCategoryDto updatePayoutCategory(PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryDaoImpl.updatePayoutCategory() invoked");
        PayoutCategory payoutCategory = payoutCategoryTransformer.reverseTransform(payoutCategoryDto);
        PayoutCategory updatedPayoutCategory = saveOrUpdate(payoutCategory);
        return payoutCategoryTransformer.transform(updatedPayoutCategory);
    }

    @Override
    @Transactional
    public PayoutCategoryDto checkPayoutCategoryAvailability(Integer payoutCategoryId) {
        log.info("PayoutCategoryDaoImpl.checkPayoutCategoryAvailability() invoked with id: {}", payoutCategoryId);
        Criteria criteria = getCurrentSession().createCriteria(PayoutCategory.class);
        criteria.add(org.hibernate.criterion.Restrictions.eq("id", payoutCategoryId));
        PayoutCategory payoutCategory = (PayoutCategory) criteria.uniqueResult();
        return payoutCategory != null ? payoutCategoryTransformer.transform(payoutCategory) : null;
    }

    @Override
    @Transactional
    public List<PayoutCategoryDto> getAllPayoutCategory() {
        log.info("PayoutCategoryDaoImpl.getAllPayoutCategory() invoked");
        Criteria criteria = getCurrentSession().createCriteria(PayoutCategory.class);
        List<PayoutCategory> payoutCategories = criteria.list();
        return payoutCategories.stream()
                .map(payoutCategoryTransformer::transform)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPagePayoutCategory(int pageNumber, int pageSize, Map<String, String> searchParams) {
        log.info("PayoutCategoryDaoImpl.getAllPagePayoutCategory() invoked");
        PaginatedResponseDto paginatedResponseDto = null;
        List<PayoutCategory> allPayoutCategoryList;
        
        String countString = "SELECT COUNT(*) FROM payout_category";
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        Criteria criteria = getCurrentSession().createCriteria(PayoutCategory.class);
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        allPayoutCategoryList = criteria.list();

        if (allPayoutCategoryList != null && !allPayoutCategoryList.isEmpty()) {
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allPayoutCategoryList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(allPayoutCategoryList.stream()
                    .map(payoutCategoryTransformer::transform)
                    .collect(Collectors.toList()));
        }
        return paginatedResponseDto;
    }
    
}