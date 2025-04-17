package com.pos_main.DaoImpl;

import java.time.LocalDateTime;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.PayoutDao;
import com.pos_main.Domain.Payout;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.PayoutDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.PayoutTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PayoutDaoImpl extends BaseDaoImpl<Payout> implements PayoutDao {

    @Autowired
    PayoutTransformer payoutTransformer;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<PayoutDto> getAllPayout() {
        log.info("PayoutDaoImpl.getAllPayout() invoked");
        try {
            EntityManager em = getCurrentSession().getSessionFactory().createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Payout> cq = cb.createQuery(Payout.class);
            Root<Payout> root = cq.from(Payout.class);
            cq.select(root);

            TypedQuery<Payout> query = em.createQuery(cq);
            List<Payout> payoutList = query.getResultList();

            if (payoutList != null && !payoutList.isEmpty()) {
                return payoutList.stream()
                        .map(payoutTransformer::transform)
                        .collect(Collectors.toList());
            }
            log.warn("No payout records found.");
            return new ArrayList<>();
        } catch (Exception e) {
            log.error("Error retrieving all payout details: {}", e.getMessage(), e);
            throw e;
        }
    }

	@Override
	@Transactional
	public PaginatedResponseDto getAllPagePayout(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("PayoutDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Payout> allPayoutList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM payout";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}
		
		Criteria criteria = getCurrentSession().createCriteria(Payout.class, "payout");
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allPayoutList = criteria.list();
		recordCount = allPayoutList.size();
		if (allPayoutList != null && !allPayoutList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allPayoutList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allPayoutList.stream().map(Invoice -> {
				return payoutTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

    @Override
    @Transactional
    public PayoutDto save(PayoutDto payoutDto) {
        log.info("PayoutDaoImpl.save() invoked.");
        try {
            Payout payout = payoutTransformer.reverseTransform(payoutDto);
            Payout savedPayout = saveOrUpdate(payout);
            return payoutTransformer.transform(savedPayout);
        } catch (Exception e) {
            log.error("Error saving payout: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public PayoutDto updatePayout(PayoutDto payoutDto) {
        log.info("PayoutDaoImpl.update() invoked.");
        try {
            Payout payout = payoutTransformer.reverseTransform(payoutDto);
            Payout updatedPayout = saveOrUpdate(payout);
            return payoutTransformer.transform(updatedPayout);
        } catch (Exception e) {
            log.error("Error updating payout: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public PayoutDto checkPayoutAvailability(Integer payoutId) {
        log.info("PayoutDaoImpl.checkPayoutAvailability() invoked with payoutId: {}", payoutId);
        try {
            EntityManager em = getCurrentSession().getSessionFactory().createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Payout> cq = cb.createQuery(Payout.class);
            Root<Payout> root = cq.from(Payout.class);
            cq.select(root).where(cb.equal(root.get("id"), payoutId));

            TypedQuery<Payout> query = em.createQuery(cq);
            Payout payout = query.getSingleResult();
            return payout != null ? payoutTransformer.transform(payout) : null;
        } catch (Exception e) {
            log.error("Error checking payout availability for id {}: {}", payoutId, e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    @Transactional
    public Double getTotalPayout() {
        log.info("PayoutDaoImpl.getTotalPayout() invoked");
        Double totalAmount = 0.0;
        int count = 0;
        
        try {
            // Get all payout records ordered by ID
            Criteria criteria = getCurrentSession().createCriteria(Payout.class);
            criteria.addOrder(Order.asc("id"));
            List<Payout> allPayoutList = criteria.list();
            
            if (allPayoutList != null && !allPayoutList.isEmpty()) {
                // Check if any record has generatedDateTime
                boolean hasGeneratedDateTime = allPayoutList.stream()
                        .anyMatch(payout -> payout.getGeneratedDateTime() != null);
                
                if (hasGeneratedDateTime) {
                    // Find the last record with generatedDateTime
                    Payout lastGeneratedRecord = null;
                    int lastGeneratedIndex = -1;
                    
                    for (int i = 0; i < allPayoutList.size(); i++) {
                        Payout payout = allPayoutList.get(i);
                        if (payout.getGeneratedDateTime() != null) {
                            lastGeneratedRecord = payout;
                            lastGeneratedIndex = i;
                        }
                    }
                    
                    // Sum amounts from records after the last generatedDateTime
                    for (int i = lastGeneratedIndex + 1; i < allPayoutList.size(); i++) {
                        Payout payout = allPayoutList.get(i);
                        if (payout.getAmount() != null) {
                            totalAmount += payout.getAmount();
                            count++;
                        }
                    }
                    
                    log.info("Found last generatedDateTime at ID: {}, summing {} records after this", 
                            lastGeneratedRecord.getId(), count);
                } else {
                    // Sum all amounts if no record has generatedDateTime
                    for (Payout payout : allPayoutList) {
                        if (payout.getAmount() != null) {
                            totalAmount += payout.getAmount();
                            count++;
                        }
                    }
                    log.info("No generatedDateTime found, summing all {} records", count);
                }
            }
            
            log.info("Total payout amount: {}, Count of records summed: {}", totalAmount, count);
            
        } catch (Exception e) {
            log.error("Exception occurs while calculating total payout amount.", e);
        }
        
        return totalAmount;
    }

    @Override
    @Transactional
    public Integer getPayoutCount(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("PayoutDaoImpl.getPayoutCount() invoked for period: {} to {}", startDate, endDate);
        try {
            Criteria criteria = getCurrentSession().createCriteria(Payout.class);
            criteria.add(Restrictions.between("dateTime", startDate, endDate));
            criteria.add(Restrictions.eq("isActive", true));
            return criteria.list().size();
        } catch (Exception e) {
            log.error("Error counting payout transactions: {}", e.getMessage(), e);
            return 0;
        }
    }

}