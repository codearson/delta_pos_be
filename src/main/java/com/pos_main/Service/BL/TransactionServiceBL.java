package com.pos_main.Service.BL;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.ProductDao;
import com.pos_main.Dao.TransactionDao;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.TransactionDetailsListDto;

import lombok.extern.slf4j.Slf4j;

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
	
	public TransactionDto save(TransactionDto transactionDto, String alertMessage) {
	    log.info("TransactionServiceBL.save() invoked.");
	    
	    for (TransactionDetailsListDto details : transactionDto.getTransactionDetailsList()) {
	        if (details.getProductDto() != null) {
	            Integer productId = details.getProductDto().getId();

	            List<ProductDto> productList = productDao.getProductById(productId);
	            
	            alertMessage = null;

	            if (productList != null && !productList.isEmpty()) {
	                ProductDto productDto = productList.get(0);

	                boolean isCustomCategory = productDto.getProductCategoryDto() != null && 
	                    "Custom".equalsIgnoreCase(productDto.getProductCategoryDto().getProductCategoryName());

	                if (!isCustomCategory) { 
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
	                    log.info("Skipping quantity update for Custom category productId: " + productId);
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
	    	    
	    if (transactionDto.getTransactionDetailsList() != null) {
	        for (TransactionDetailsListDto details : transactionDto.getTransactionDetailsList()) {
	            if (details.getProductDto() != null) {
	                Integer productId = details.getProductDto().getId();
	                
	                List<ProductDto> productList = productDao.getProductById(productId);
	                
	                if (productList != null && !productList.isEmpty()) {
	                    ProductDto productDto = productList.get(0);
	                } else {
	                    log.info("Product not found for productId: " + productId);
	                }
	            } else {
	                log.info("ProductDto is null in TransactionDetailsListDto");
	            }
	        }
	    } else {
	        log.warn("Transaction details list is null for transactionDto: {}", transactionDto.getId());
	    }
	    
	    transactionDto.setDateTime(transactionDto.getDateTime());
	    
	    return transactionDao.updateTransaction(transactionDto);
	}

	public List<TransactionDto> getTransactionByProductId(Integer productId) {
	    log.info("TransactionServiceBL.getTransactionByProductId() invoked with productId: {}", productId);
	    return transactionDao.getTransactionByProductId(productId);
	}

}