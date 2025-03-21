package com.pos_main.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.SalesReportDao;
import com.pos_main.Domain.CategoryTotals;
import com.pos_main.Domain.OverallPaymentTotals;
import com.pos_main.Domain.SalesDateDetails;
import com.pos_main.Domain.SalesReport;
import com.pos_main.Domain.UserPaymentDetails;
import com.pos_main.Dto.CategoryTotalsDto;
import com.pos_main.Dto.OverallPaymentTotalsDto;
import com.pos_main.Dto.SalesDateDetailsDto;
import com.pos_main.Dto.SalesReportDto;
import com.pos_main.Dto.UserPaymentDetailsDto;
import com.pos_main.Transformer.CategoryTotalsTransformer;
import com.pos_main.Transformer.OverallPaymentTotalsTransformer;
import com.pos_main.Transformer.SalesDateDetailsTransformer;
import com.pos_main.Transformer.SalesReportTransformer;
import com.pos_main.Transformer.UserPaymentDetailsTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: SalesReportDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:40:26
 * @version 1.0
 **/

@Slf4j
@Repository
public class SalesReportDaoImpl implements SalesReportDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SalesReportTransformer salesReportTransformer;

    @Autowired
    private SalesDateDetailsTransformer salesDateDetailsTransformer;

    @Autowired
    private CategoryTotalsTransformer categoryTotalsTransformer;

    @Autowired
    private OverallPaymentTotalsTransformer overallPaymentTotalsTransformer;

    @Autowired
    private UserPaymentDetailsTransformer userPaymentDetailsTransformer;

    @Override
    @Transactional
    public SalesReportDto save(SalesReportDto salesReportDto) {
        log.info("SalesReportDaoImpl.save() invoked");

        SalesReport salesReport = salesReportTransformer.reverseTransform(salesReportDto);
        entityManager.persist(salesReport);
        entityManager.flush();

        for (SalesDateDetailsDto dateDetailsDto : salesReportDto.getSalesDateDetails()) {
            SalesDateDetails dateDetails = salesDateDetailsTransformer.reverseTransform(dateDetailsDto);
            dateDetails.setSalesReport(salesReport);
            entityManager.persist(dateDetails);

            for (CategoryTotalsDto categoryTotalsDto : dateDetailsDto.getCategoryTotals()) {
                CategoryTotals categoryTotals = categoryTotalsTransformer.reverseTransform(categoryTotalsDto);
                categoryTotals.setSalesDateDetails(dateDetails);
                entityManager.persist(categoryTotals);
            }

            for (OverallPaymentTotalsDto paymentTotalsDto : dateDetailsDto.getOverallPaymentTotals()) {
                OverallPaymentTotals paymentTotals = overallPaymentTotalsTransformer.reverseTransform(paymentTotalsDto);
                paymentTotals.setSalesDateDetails(dateDetails);
                entityManager.persist(paymentTotals);
            }

            for (UserPaymentDetailsDto userPaymentDto : dateDetailsDto.getUserPaymentDetails()) {
                UserPaymentDetails userPayment = userPaymentDetailsTransformer.reverseTransform(userPaymentDto);
                userPayment.setSalesDateDetails(dateDetails);
                entityManager.persist(userPayment);
            }
        }

        entityManager.flush();
        entityManager.clear();
        return salesReportTransformer.transform(salesReport);
    }
}
