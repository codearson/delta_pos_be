package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDao;
import com.pos_main.Dao.TransactionDao;
import com.pos_main.Dao.UserDao;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Dto.TransactionPaymentMethodDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.TransactionDetailsListDto;
import com.pos_main.Dto.UserDto;

import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/**
 * Title: TransactionServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionServiceBL {

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	UserDao userDao;
	
	public List<TransactionDto> getTransactionByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("TransactionServiceBL.getTransactionByDateRange() invoked.");
        return transactionDao.getTransactionByDateRange(startDate, endDate);
    }
	
	public List<TransactionDto> getTransactionByBranchId(Integer branchId) {
	    log.info("TransactionServiceBL.getTransactionByBranchId() invoked with branchId: {}", branchId);
	    return transactionDao.getTransactionByBranchId(branchId);
	}
	
	public List<TransactionDto> getTransactionById(Integer id) {
	    log.info("TransactionServiceBL.getTransactionById() invoked with id: {}", id);
	    return transactionDao.getTransactionById(id);
	}
	
	public List<TransactionDto> getTransactionByUserId(Integer userId) {
	    log.info("TransactionServiceBL.getTransactionByUserId() invoked with userId: {}", userId);
	    return transactionDao.getTransactionByUserId(userId);
	}
	
	public List<TransactionDto> getTransactionByCustomerId(Integer customerId) {
	    log.info("TransactionServiceBL.getTransactionByCustomerId() invoked with customerId: {}", customerId);
	    return transactionDao.getTransactionByCustomerId(customerId);
	}
	
	public List<TransactionDto> getTransactionByPaymentMethodId(Integer paymentMethodId) {
	    log.info("TransactionServiceBL.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}", paymentMethodId);
	    return transactionDao.getTransactionByPaymentMethodId(paymentMethodId);
	}
	
	public List<TransactionDto> getAllTransaction() {
	    log.info("TransactionServiceBL.getAllTransaction() invoked.");
	    return transactionDao.getAllTransaction();
	}
	
	public List<TransactionDto> getTransactionByStatus(Boolean isActive) {
	    log.info("TransactionServiceBL.getTransactionByStatus() invoked with status: {}", isActive);
	    return transactionDao.getTransactionByStatus(isActive);
	}
	
//	public TransactionDto save(TransactionDto transactionDto, String alertMessage) {
//	    log.info("TransactionServiceBL.save() invoked.");
//	    
//	    for (TransactionDetailsListDto details : transactionDto.getTransactionDetailsList()) {
//	        if (details.getProductDto() != null) {
//	            Integer productId = details.getProductDto().getId();
//
//	            List<ProductDto> productList = productDao.getProductById(productId);
//	            
//	            alertMessage = null;
//
//	            if (productList != null && !productList.isEmpty()) {
//	                ProductDto productDto = productList.get(0);
//
//	                boolean isCustomCategory = productDto.getProductCategoryDto() != null && 
//	                    "Custom".equalsIgnoreCase(productDto.getProductCategoryDto().getProductCategoryName());
//
//	                if (!isCustomCategory) { 
//	                    Integer newQuantity = productDto.getQuantity() - details.getQuantity();
//	                    if (newQuantity < 0) {
//	                        log.info("Not enough stock for productId: " + productId);
//	                        throw new IllegalArgumentException("Insufficient stock for product ID: " + productId);
//	                    }
//	                    if (newQuantity <= productDto.getLowStock()) {
//	                        alertMessage = "ALERT: Product '" + productDto.getName() + "' (ID: " + productDto.getId() + ") is low on stock. Remaining quantity: " + newQuantity;
//	                        log.info(alertMessage);
//	                    }
//	                    productDto.setQuantity(newQuantity);
//	                    productDao.updateProduct(productDto);
//	                } else {
//	                    log.info("Skipping quantity update for Custom category productId: " + productId);
//	                }
//	            } else {
//	                log.info("Product not found for productId: " + productId);
//	            }
//	        } else {
//	            log.info("ProductDto is null in TransactionDetailsListDto");
//	        }
//	    }
//
//	    transactionDto.setDateTime(LocalDateTime.now());
//	    
//	    return transactionDao.save(transactionDto, alertMessage);
//	}
	
	public TransactionDto save(TransactionDto transactionDto, String alertMessage) {
	    log.info("TransactionServiceBL.save() invoked.");
	    
	    for (TransactionDetailsListDto details : transactionDto.getTransactionDetailsList()) {
	        if (details.getProductDto() != null) {
	            Integer productId = details.getProductDto().getId();

	            List<ProductDto> productList = productDao.getProductById(productId);
	            
	            alertMessage = null;

	            if (productList != null && !productList.isEmpty()) {
	                ProductDto productDto = productList.get(0);

	                boolean isSpecialCategory = productDto.getProductCategoryDto() != null && 
	                    ("Custom".equalsIgnoreCase(productDto.getProductCategoryDto().getProductCategoryName()) ||
	                     "nonScan".equalsIgnoreCase(productDto.getProductCategoryDto().getProductCategoryName()));

	                if (!isSpecialCategory) { 
	                    Integer newQuantity = productDto.getQuantity() - details.getQuantity();
	                    if (newQuantity < 0) {
	                        log.info("Not enough stock for productId: " + productId);
	                        throw new IllegalArgumentException("Insufficient stock for product ID: " + productId);
	                    }
	                    if (newQuantity <= productDto.getLowStock()) {
	                        alertMessage = "ALERT: Product '" + productDto.getName() + "' (ID: " + productDto.getId() + ") is low on stock. Remaining quantity: " + newQuantity;
	                        log.info(alertMessage);
	                    }
	                    productDto.setQuantity(newQuantity);
	                    productDao.updateProduct(productDto);
	                } else {
	                    log.info("Skipping quantity update for special category productId: " + productId + " (Category: " + productDto.getProductCategoryDto().getProductCategoryName() + ")");
	                }
	            } else {
	                log.info("Product not found for productId: " + productId);
	            }
	        } else {
	            log.info("ProductDto is null in TransactionDetailsListDto");
	        }
	    }

	    transactionDto.setDateTime(LocalDateTime.now());
	    
	    return transactionDao.save(transactionDto, alertMessage);
	}

	public TransactionDto updateTransaction(TransactionDto transactionDto) {
	    log.info("TransactionServiceBL.updateTransaction() invoked.");
	    
	    Double totalAmount = 0.0;
	    
	    for (TransactionDetailsListDto details : transactionDto.getTransactionDetailsList()) {
	        if (details.getProductDto() != null) {
	            Integer productId = details.getProductDto().getId();
	            
	            List<ProductDto> productList = productDao.getProductById(productId);
	            
	            if (productList != null && !productList.isEmpty()) {
	                ProductDto productDto = productList.get(0);
	                
	                details.setUnitPrice(productDto.getPricePerUnit());
	                
	                Double amountForProduct = (double) ((details.getUnitPrice() * details.getQuantity()) - details.getDiscount());
	                totalAmount += amountForProduct;
	            } else {
	                log.info("Product not found for productId: " + productId);
	            }
	        } else {
	            log.info("ProductDto is null in TransactionDetailsListDto");
	        }
	    }
	    
	    transactionDto.setTotalAmount(totalAmount);
	    transactionDto.setDateTime(transactionDto.getDateTime());
	    
	    return transactionDao.updateTransaction(transactionDto);
	}

	public List<TransactionDto> getTransactionByProductId(Integer productId) {
	    log.info("TransactionServiceBL.getTransactionByProductId() invoked with productId: {}", productId);
	    return transactionDao.getTransactionByProductId(productId);
	}

	public Map<String, Object> getXReport(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
	    log.info("TransactionServiceBL.getXReport() invoked with userId: {}, startDate: {}, endDate: {}", userId, startDate, endDate);
	    List<UserDto> userList = userDao.getUserById(userId);
	    if (userList.isEmpty()) {
	        throw new EntityNotFoundException("User not found with ID: " + userId);
	    }
	    
	    UserDto reportUser = userList.get(0);
	    String reportGeneratedBy = reportUser.getFirstName() + " " + reportUser.getLastName();
	    List<TransactionDto> allTransactions = transactionDao.getTransactionByDateRange(startDate, endDate);
	    Map<String, Double> categoryTotals = new HashMap<>();
	    Map<String, Double> overallPaymentMethodTotals = new HashMap<>();
	    Map<String, Map<String, Double>> userPaymentDetails = new HashMap<>();
	    
	    for (TransactionDto transaction : allTransactions) {
	        String userName = transaction.getUserDto().getFirstName() + " " + transaction.getUserDto().getLastName();
	        for (TransactionDetailsListDto detail : transaction.getTransactionDetailsList()) {
	            ProductDto product = detail.getProductDto();
	            String categoryName = product.getProductCategoryDto().getProductCategoryName();
	            
	            double amount = (detail.getUnitPrice() * detail.getQuantity()) - detail.getDiscount();
	            categoryTotals.merge(categoryName, amount, Double::sum);
	        }
	        
	        for (TransactionPaymentMethodDto payment : transaction.getTransactionPaymentMethod()) {
	            String methodName = payment.getPaymentMethodDto().getType();
	            Double amount = payment.getAmount();
	            overallPaymentMethodTotals.merge(methodName, amount, Double::sum);
	            userPaymentDetails
	                .computeIfAbsent(userName, k -> new HashMap<>())
	                .merge(methodName, amount, Double::sum);
	        }
	    }
	    
	    List<Map<String, Object>> userPaymentSummary = userPaymentDetails.entrySet().stream()
	        .map(entry -> {
	            Map<String, Object> userSummary = new HashMap<>();
	            userSummary.put("userName", entry.getKey());
	            userSummary.put("payments", entry.getValue());
	            return userSummary;
	        })
	        .collect(Collectors.toList());
	    Map<String, Object> response = new HashMap<>();
	    response.put("reportGeneratedBy", reportGeneratedBy);
	    response.put("startDate", startDate);
	    response.put("endDate", endDate);
	    response.put("categoryTotals", categoryTotals);
	    response.put("overallPaymentTotals", overallPaymentMethodTotals);
	    response.put("userPaymentDetails", userPaymentSummary);
	    response.put("totalTransactions", allTransactions.size());
	    response.put("totalSales", categoryTotals.values().stream().mapToDouble(Double::doubleValue).sum());
	    return response;
	}

	
	public Map<String, Object> getZReport(Integer userId, LocalDateTime startDate, LocalDateTime endDate) {
        log.info("TransactionServiceBL.getZReport() invoked with userId: {}, startDate: {}, endDate: {}", userId, startDate, endDate);
        List<UserDto> userList = userDao.getUserById(userId);
        if (userList == null || userList.isEmpty()) {
            throw new EntityNotFoundException("User not found with ID: " + userId);
        }

        UserDto reportUser = userList.get(0);
        String reportGeneratedBy = reportUser.getFirstName() + " " + reportUser.getLastName();
        List<TransactionDto> allTransactions = transactionDao.getTransactionByDateRange(startDate, endDate);

        Map<String, Map<String, Object>> dateWiseTotals = new TreeMap<>();
        Double fullyTotalSales = 0.0;

        for (TransactionDto transaction : allTransactions) {
            LocalDate transactionDate = transaction.getDateTime().toLocalDate();
            String dateKey = transactionDate.toString();
            String userName = transaction.getUserDto().getFirstName() + " " + transaction.getUserDto().getLastName();

            dateWiseTotals.computeIfAbsent(dateKey, k -> new HashMap<String, Object>() {{
                put("categoryTotals", new HashMap<String, Double>());
                put("overallPaymentTotals", new HashMap<String, Double>());
                put("userPaymentDetails", new HashMap<String, Map<String, Double>>());
                put("totalSales", 0.0);
                put("totalTransactions", 0);
            }});

            Map<String, Object> dateTotals = dateWiseTotals.get(dateKey);
            dateTotals.put("totalTransactions", (Integer) dateTotals.get("totalTransactions") + 1);

            Map<String, Double> categoryTotals = (Map<String, Double>) dateTotals.get("categoryTotals");
            for (TransactionDetailsListDto detail : transaction.getTransactionDetailsList()) {
                String categoryName = detail.getProductDto().getProductCategoryDto().getProductCategoryName();
                double amount = (detail.getUnitPrice() * detail.getQuantity()) - detail.getDiscount();
                categoryTotals.merge(categoryName, amount, Double::sum);
                dateTotals.put("totalSales", (Double) dateTotals.get("totalSales") + amount);
                fullyTotalSales += amount;
            }

            Map<String, Double> overallPaymentTotals = (Map<String, Double>) dateTotals.get("overallPaymentTotals");
            Map<String, Map<String, Double>> userPaymentDetails = (Map<String, Map<String, Double>>) dateTotals.get("userPaymentDetails");

            for (TransactionPaymentMethodDto payment : transaction.getTransactionPaymentMethod()) {
                String methodName = payment.getPaymentMethodDto().getType();
                Double amount = payment.getAmount();
                overallPaymentTotals.merge(methodName, amount, Double::sum);
                userPaymentDetails.computeIfAbsent(userName, k -> new HashMap<>())
                        .merge(methodName, amount, Double::sum);
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("reportGeneratedBy", reportGeneratedBy);
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("dateWiseTotals", dateWiseTotals);
        response.put("fullyTotalSales", fullyTotalSales);

        return response;
    }

    public Map<String, Object> getLastTransactionInfo() {
        return transactionDao.getLastTransactionInfo();
    }

    public LocalDateTime getFirstTransactionDateTime() {
        return transactionDao.getFirstTransactionDateTime();
    }

    @Transactional
    public void updateGenerateDateTime(Integer transactionId, LocalDateTime generateDateTime) {
        transactionDao.updateGenerateDateTime(transactionId, generateDateTime);
    }
    
    public LocalDateTime getNextTransactionDateTimeAfter(LocalDateTime startDate) {
        return transactionDao.getNextTransactionDateTimeAfter(startDate);
    }
    
    public PaginatedResponseDto getAllPageTransaction(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("TransactionServiceBL.getAllPageTransaction()invoked");
		return transactionDao.getAllPageTransaction(pageNumber, pageSize, searchParams);
	}

}
