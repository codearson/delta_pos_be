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
	

	public TransactionDto save(TransactionDto transactionDto) {
	    log.info("TransactionServiceBL.save() invoked.");

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
	    transactionDto.setDateTime(LocalDateTime.now());

	    return transactionDao.save(transactionDto);
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

	

}
