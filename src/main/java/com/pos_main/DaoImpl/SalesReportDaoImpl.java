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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SalesReportDto> findByReportType(String reportType) {
        log.info("SalesReportDaoImpl.findByReportType() invoked for: {}", reportType);
        
        String jpql = "SELECT sr FROM SalesReport sr WHERE sr.reportType = :reportType";
        List<SalesReport> salesReports = entityManager.createQuery(jpql, SalesReport.class)
                .setParameter("reportType", reportType)
                .getResultList();
        
        return transformSalesReports(salesReports);
    }

    @Override
    public List<SalesReportDto> findByReportTypeWithPagination(String reportType, int pageNumber, int pageSize) {
        log.info("SalesReportDaoImpl.findByReportTypeWithPagination() invoked for: {}, page: {}, size: {}", 
                reportType, pageNumber, pageSize);
        
        String jpql = "SELECT sr FROM SalesReport sr WHERE sr.reportType = :reportType";
        List<SalesReport> salesReports = entityManager.createQuery(jpql, SalesReport.class)
                .setParameter("reportType", reportType)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        
        return transformSalesReports(salesReports);
    }

    private List<SalesReportDto> transformSalesReports(List<SalesReport> salesReports) {
        List<SalesReportDto> reportDtos = new ArrayList<>();
        
        for (SalesReport report : salesReports) {
            SalesReportDto dto = salesReportTransformer.transform(report);
            
            // Fetch and set SalesDateDetails for this report
            String dateDetailsQuery = "SELECT sd FROM SalesDateDetails sd WHERE sd.salesReport.id = :reportId";
            List<SalesDateDetails> dateDetailsList = entityManager.createQuery(dateDetailsQuery, SalesDateDetails.class)
                    .setParameter("reportId", report.getId())
                    .getResultList();
            
            List<SalesDateDetailsDto> dateDetailsDtos = new ArrayList<>();
            
            for (SalesDateDetails dateDetails : dateDetailsList) {
                SalesDateDetailsDto dateDetailsDto = salesDateDetailsTransformer.transform(dateDetails);
                
                // Fetch and set CategoryTotals
                String categoryQuery = "SELECT ct FROM CategoryTotals ct WHERE ct.salesDateDetails.id = :dateDetailsId";
                List<CategoryTotals> categoryTotals = entityManager.createQuery(categoryQuery, CategoryTotals.class)
                        .setParameter("dateDetailsId", dateDetails.getId())
                        .getResultList();
                dateDetailsDto.setCategoryTotals(categoryTotals.stream()
                        .map(categoryTotalsTransformer::transform)
                        .collect(Collectors.toList()));
                
                // Fetch and set OverallPaymentTotals
                String paymentQuery = "SELECT pt FROM OverallPaymentTotals pt WHERE pt.salesDateDetails.id = :dateDetailsId";
                List<OverallPaymentTotals> paymentTotals = entityManager.createQuery(paymentQuery, OverallPaymentTotals.class)
                        .setParameter("dateDetailsId", dateDetails.getId())
                        .getResultList();
                dateDetailsDto.setOverallPaymentTotals(paymentTotals.stream()
                        .map(overallPaymentTotalsTransformer::transform)
                        .collect(Collectors.toList()));
                
                // Fetch and set UserPaymentDetails
                String userPaymentQuery = "SELECT up FROM UserPaymentDetails up WHERE up.salesDateDetails.id = :dateDetailsId";
                List<UserPaymentDetails> userPayments = entityManager.createQuery(userPaymentQuery, UserPaymentDetails.class)
                        .setParameter("dateDetailsId", dateDetails.getId())
                        .getResultList();
                dateDetailsDto.setUserPaymentDetails(userPayments.stream()
                        .map(userPaymentDetailsTransformer::transform)
                        .collect(Collectors.toList()));
                
                dateDetailsDtos.add(dateDetailsDto);
            }
            
            dto.setSalesDateDetails(dateDetailsDtos);
            reportDtos.add(dto);
        }
        
        return reportDtos;
    }
}
