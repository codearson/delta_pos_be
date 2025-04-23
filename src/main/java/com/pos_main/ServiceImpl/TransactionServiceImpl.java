package com.pos_main.ServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Constants.ApplicationMessageConstants;
import com.pos_main.Dao.TransactionDao;
import com.pos_main.Dto.CategoryTotalsDto;
import com.pos_main.Dto.OverallPaymentTotalsDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SalesDateDetailsDto;
import com.pos_main.Dto.SalesReportDto;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Dto.UserPaymentDetailsDto;
import com.pos_main.Service.SalesReportService;
import com.pos_main.Service.TransactionService;
import com.pos_main.Service.BL.BankingServiceBL;
import com.pos_main.Service.BL.PayoutServiceBL;
import com.pos_main.Service.BL.TransactionServiceBL;
import com.pos_main.Service.Utils.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionServiceImpl.java. Company: www.codearson.com Copyright:
 * Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	TransactionServiceBL transactionServiceBL;
	
	@Autowired
    private SalesReportService salesReportService;
	
	@Autowired
    private TransactionDao transactionDao;
	
	@Autowired
	BankingServiceBL bankingServiceBL;
	
	@Autowired
	PayoutServiceBL payoutServiceBL;

	@Transactional	
	@Override
    public ResponseDto getTransactionByDateRange (LocalDateTime startDate, LocalDateTime endDate) {
        log.info("transactionServiceBL.getTransactionByDateRange () invoked");
        ResponseDto responseDto = null;
        try {
            List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByDateRange(startDate, endDate);
            if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
                log.info("Retrieve Transaction Details by DateRange.");
                responseDto = serviceUtil.getServiceResponse(transactionDtoList);
            } else {
                log.info("Unable to retrieve Transaction by DateRange.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_DATERANGE);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving Transaction by DateRange.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_DATERANGE);
        }
        return responseDto;
    }
	
	@Transactional
	@Override
	public ResponseDto getTransactionByBranchId(Integer branchId) {
		log.info("TransactionServiceImpl.getTransactionByBranchId() invoked with branchId: {}", branchId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByBranchId(branchId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for branchId: {}", branchId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for branchId: {}", branchId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_BRANCH_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by branchId: {}", branchId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_BRANCH_ID);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto getTransactionById(Integer id) {
		log.info("TransactionServiceImpl.getTransactionById() invoked with id: {}", id);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionById(id);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for id: {}", id);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for id: {}", id);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by id: {}", id, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_ID);
		}
		return responseDto;
	}

	public ResponseDto getTransactionByUserId(Integer userId) {
		log.info("TransactionServiceImpl.getTransactionByUserId() invoked with userId: {}", userId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByUserId(userId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for userId: {}", userId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for userId: {}", userId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_USER_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by userId: {}", userId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_USER_ID);
		}
		return responseDto;
	}

	public ResponseDto getTransactionByCustomerId(Integer customerId) {
		log.info("TransactionServiceImpl.getTransactionByCustomerId() invoked with customerId: {}", customerId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByCustomerId(customerId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for customerId: {}", customerId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for customerId: {}", customerId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_CUSTOMER_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by customerId: {}", customerId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_CUSTOMER_ID);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getTransactionByPaymentMethodId(Integer paymentMethodId) {
		log.info("TransactionServiceImpl.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}",
				paymentMethodId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL
					.getTransactionByPaymentMethodId(paymentMethodId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for paymentMethodId: {}", paymentMethodId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for paymentMethodId: {}", paymentMethodId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by paymentMethodId: {}", paymentMethodId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_PAYMENT_METHOD_ID);
		}
		return responseDto;
	}

	@Override
	public ResponseDto save(TransactionDto transactionDto, String alertMessage) {
		log.info("TransactionServiceImpl.save() invoked");
		ResponseDto responseDto = null;
		try {
			TransactionDto savedTransaction = transactionServiceBL.save(transactionDto, alertMessage);
			if (savedTransaction != null) {
				log.info("Transaction details saved.");
				responseDto = serviceUtil.getServiceResponse(savedTransaction);
			} else {
				log.info("Unable to save transaction details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_TRANSACTION_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving transaction details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_TRANSACTION_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateTransaction(TransactionDto transactionDto) {
		log.info("TransactionServiceImpl.update(ReBlocksDto reBlocksDt) invoked");
		ResponseDto responseDto = null;
		try {
			TransactionDto updateTransactionDto = transactionServiceBL.updateTransaction(transactionDto);
			if (updateTransactionDto != null) {
				log.info("transaction Details updated.");
				responseDto = serviceUtil.getServiceResponse(updateTransactionDto);
			} else {
				log.info("Unable to update Transaction details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_TRANSACTION_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while updating Transaction details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_TRANSACTION_DETAILS);
		}
		return responseDto;

	}

	@Transactional
	@Override
	public ResponseDto getAllTransaction() {
		log.info("TransactionServiceImpl.getAllTransaction() invoked");
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getAllTransaction();
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("All transactions retrieved successfully.");
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_TRANSACTION);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving all transactions.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_TRANSACTION);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getTransactionByStatus(Boolean isActive) {
		log.info("TransactionServiceImpl.getTransactionByStatus() invoked with isActive: {}", isActive);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL.getTransactionByStatus(isActive);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for status: {}", isActive);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for status: {}", isActive);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_STATUS);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by status: {}", isActive, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_STATUS);
		}
		return responseDto;
	}
	
	@Transactional
	@Override
	public ResponseDto getTransactionByProductId(Integer productId) {
		log.info("TransactionServiceImpl.getTransactionByProductId() invoked with productId: {}",
				productId);
		ResponseDto responseDto = null;
		try {
			List<TransactionDto> transactionDtoList = transactionServiceBL
					.getTransactionByProductId(productId);
			if (transactionDtoList != null && !transactionDtoList.isEmpty()) {
				log.info("Transactions retrieved successfully for productId: {}", productId);
				responseDto = serviceUtil.getServiceResponse(transactionDtoList);
			} else {
				log.info("No transactions found for productId: {}", productId);
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_PRODUCT_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving transactions by productId: {}", productId, e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_PRODUCT_ID);
		}
		return responseDto;
	}

	@Override
	@Transactional
	public ResponseDto getXReport(Integer userId) {
	    log.info("TransactionServiceImpl.getXReport() invoked with userId: {}", userId);
	    ResponseDto responseDto;
	    try {
	        Map<String, Object> lastTransactionInfo = transactionServiceBL.getLastTransactionInfo();
	        LocalDateTime endDate = LocalDateTime.now();
	        LocalDateTime startDate;

	        if (lastTransactionInfo != null) {
	            if (transactionDao.areAllGenerateDateTimesNull()) {
	                log.info("All generateDateTime values are null, using dateTime of transaction ID 1");
	                startDate = transactionDao.getDateTimeForTransactionIdOne();
	                if (startDate == null) {
	                    log.warn("No transaction found with ID 1, using first transaction dateTime");
	                    startDate = transactionServiceBL.getFirstTransactionDateTime();
	                }
	            } else {
	                log.info("Using the last non-null generateDateTime as startDate");
	                startDate = transactionDao.getLastGenerateDateTime();
	                if (startDate == null) {
	                    log.warn("No non-null generateDateTime found, falling back to first transaction dateTime");
	                    startDate = transactionServiceBL.getFirstTransactionDateTime();
	                }
	            }

	            LocalDateTime nextStartDate = transactionServiceBL.getNextTransactionDateTimeAfter(startDate);
	            if (nextStartDate != null) {
	                log.info("Found next transaction after startDate, new startDate: {}", nextStartDate);
	                startDate = nextStartDate;
	            } else {
	                log.warn("No transaction found after startDate: {}, using original startDate", startDate);
	            }

	            Map<String, Object> xReport = transactionServiceBL.getXReport(userId, startDate, endDate);
	            if (xReport != null) {
	                log.info("X-Report generated successfully.");
	                
	                // Get banking and pay out totals and counts
	                Double bankingTotal = bankingServiceBL.getTotalBanking();
	                Double payoutTotal = payoutServiceBL.getTotalPayout();
	                Integer bankingCount = bankingServiceBL.getBankingCount(startDate, endDate);
	                Integer payoutCount = payoutServiceBL.getPayoutCount(startDate, endDate);
	                
	                // Get cash payment total from payment methods
	                Map<String, Double> paymentTotals = (Map<String, Double>) xReport.get("overallPaymentTotals");
	                Double cashTotal = paymentTotals.getOrDefault("Cash", 0.0);
	                
	                // Get balance amount total
	                Double balanceTotal = (Double) xReport.get("totalBalanceAmount");
	                if (balanceTotal == null) {
	                    balanceTotal = 0.0;
	                }
	                
	                // Calculate difference
	                Double difference = cashTotal;
	                if (bankingTotal != 0.0 || payoutTotal != 0.0 || balanceTotal != 0.0) {
	                    Double totalDeductions = bankingTotal + payoutTotal + balanceTotal;
	                    difference = cashTotal - totalDeductions;
	                }
	                
	                // Add banking, pay out, counts, difference and balance amount to the response
	                xReport.put("bankingTotal", bankingTotal);
	                xReport.put("payoutTotal", payoutTotal);
	                xReport.put("bankingCount", bankingCount);
	                xReport.put("payoutCount", payoutCount);
	                xReport.put("difference", difference);
	                xReport.put("balanceTotal", balanceTotal);
	                
	                responseDto = serviceUtil.getServiceResponse(xReport);
	            } else {
	                log.info("Unable to generate X-Report.");
	                responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_X_REPORT);
	            }
	        } else {
	            log.info("No transactions found to generate X-Report.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_TRANSACTIONS_FOUND);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while generating X-Report: {}", e.getMessage(), e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_X_REPORT);
	    }
	    return responseDto;
	}

	private SalesReportDto transformToSalesReportDtoForXReport(Map<String, Object> xReport) {
	    SalesReportDto dto = new SalesReportDto();
	    dto.setReportGeneratedBy((String) xReport.get("reportGeneratedBy"));
	    dto.setStartDate((LocalDateTime) xReport.get("startDate"));
	    dto.setEndDate((LocalDateTime) xReport.get("endDate"));
	    dto.setFullyTotalSales((Double) xReport.get("totalSales"));
	    dto.setReportType("xReport");

	    // Get banking and pay out totals and counts
	    Double bankingTotal = bankingServiceBL.getTotalBanking();
	    Double payoutTotal = payoutServiceBL.getTotalPayout();
	    
	    // Get counts for the specific date range
	    Integer bankingCount = bankingServiceBL.getBankingCount(dto.getStartDate(), dto.getEndDate());
	    Integer payoutCount = payoutServiceBL.getPayoutCount(dto.getStartDate(), dto.getEndDate());
	    
	    // Set banking and pay out data
	    dto.setBanking(bankingTotal);
	    dto.setPayout(payoutTotal);
	    dto.setBankingCount(bankingCount);
	    dto.setPayoutCount(payoutCount);
	    
	    // Get cash payment total from payment methods
	    Map<String, Double> paymentTotals = (Map<String, Double>) xReport.get("overallPaymentTotals");
	    Double cashTotal = paymentTotals.getOrDefault("Cash", 0.0);
	    
	    // Calculate difference
	    Double totalDeductions = bankingTotal + payoutTotal + (Double) xReport.get("totalBalanceAmount");
	    Double difference = cashTotal - totalDeductions;
	    
	    // If difference is negative, set it to 0
	    if (difference < 0) {
	        difference = 0.0;
	    }
	    
	    dto.setDifference(difference);

	    List<SalesDateDetailsDto> salesDateDetails = new ArrayList<>();
	    SalesDateDetailsDto dateDetailsDto = new SalesDateDetailsDto();
	    dateDetailsDto.setSalesDate(dto.getStartDate().toLocalDate().toString());
	    dateDetailsDto.setTotalTransactions((Integer) xReport.get("totalTransactions"));
	    dateDetailsDto.setTotalSales((Double) xReport.get("totalSales"));

	    Map<String, Double> categoryTotalsMap = (Map<String, Double>) xReport.get("categoryTotals");
	    List<CategoryTotalsDto> categoryTotals = new ArrayList<>();
	    for (Map.Entry<String, Double> category : categoryTotalsMap.entrySet()) {
	        CategoryTotalsDto categoryDto = new CategoryTotalsDto();
	        categoryDto.setCategoryName(category.getKey());
	        categoryDto.setCategoryTotal(category.getValue());
	        categoryTotals.add(categoryDto);
	    }
	    dateDetailsDto.setCategoryTotals(categoryTotals);

	    Map<String, Double> overallPaymentTotalsMap = (Map<String, Double>) xReport.get("overallPaymentTotals");
	    List<OverallPaymentTotalsDto> overallPaymentTotals = new ArrayList<>();
	    for (Map.Entry<String, Double> payment : overallPaymentTotalsMap.entrySet()) {
	        OverallPaymentTotalsDto paymentDto = new OverallPaymentTotalsDto();
	        paymentDto.setPaymentMethod(payment.getKey());
	        paymentDto.setPaymentTotal(payment.getValue());
	        overallPaymentTotals.add(paymentDto);
	    }
	    dateDetailsDto.setOverallPaymentTotals(overallPaymentTotals);

	    List<Map<String, Object>> userPaymentDetails = (List<Map<String, Object>>) xReport.get("userPaymentDetails");
	    List<UserPaymentDetailsDto> userPaymentDetailsList = new ArrayList<>();
	    for (Map<String, Object> userDetail : userPaymentDetails) {
	        String userName = (String) userDetail.get("userName");
	        Map<String, Double> payments = (Map<String, Double>) userDetail.get("payments");
	        for (Map.Entry<String, Double> payment : payments.entrySet()) {
	            UserPaymentDetailsDto userPaymentDto = new UserPaymentDetailsDto();
	            userPaymentDto.setUserName(userName);
	            userPaymentDto.setPaymentMethod(payment.getKey());
	            userPaymentDto.setPaymentTotal(payment.getValue());
	            userPaymentDetailsList.add(userPaymentDto);
	        }
	    }
	    dateDetailsDto.setUserPaymentDetails(userPaymentDetailsList);

	    salesDateDetails.add(dateDetailsDto);
	    dto.setSalesDateDetails(salesDateDetails);

	    return dto;
	}
	
	@Override
	@Transactional
	public ResponseDto getZReport(Integer userId) {
	    log.info("TransactionServiceImpl.getZReport() invoked with userId: {}", userId);
	    ResponseDto responseDto;
	    try {
	        Map<String, Object> lastTransactionInfo = transactionServiceBL.getLastTransactionInfo();
	        LocalDateTime endDate = LocalDateTime.now();
	        LocalDateTime startDate;

	        if (lastTransactionInfo != null) {
	            if (transactionDao.areAllGenerateDateTimesNull()) {
	                log.info("All generateDateTime values are null, using dateTime of transaction ID 1");
	                startDate = transactionDao.getDateTimeForTransactionIdOne();
	                if (startDate == null) {
	                    log.warn("No transaction found with ID 1, using first transaction dateTime");
	                    startDate = transactionServiceBL.getFirstTransactionDateTime();
	                }
	            } else {
	                log.info("Using the last non-null generateDateTime as startDate");
	                startDate = transactionDao.getLastGenerateDateTime();
	                if (startDate == null) {
	                    log.warn("No non-null generateDateTime found, falling back to first transaction dateTime");
	                    startDate = transactionServiceBL.getFirstTransactionDateTime();
	                }
	            }

	            LocalDateTime nextStartDate = transactionServiceBL.getNextTransactionDateTimeAfter(startDate);
	            if (nextStartDate != null) {
	                log.info("Found next transaction after startDate, new startDate: {}", nextStartDate);
	                startDate = nextStartDate;
	            } else {
	                log.warn("No transaction found after startDate: {}, using original startDate", startDate);
	            }

	            Map<String, Object> zReport = transactionServiceBL.getZReport(userId, startDate, endDate);
	            if (zReport != null) {
	                log.info("Z-Report generated successfully.");
	                
	                // Get banking and payout totals and counts
	                Double bankingTotal = bankingServiceBL.getTotalBanking();
	                Double payoutTotal = payoutServiceBL.getTotalPayout();
	                Integer bankingCount = bankingServiceBL.getBankingCount(startDate, endDate);
	                Integer payoutCount = payoutServiceBL.getPayoutCount(startDate, endDate);
	                
	                // Calculate total cash payments from all dates
	                Double totalCash = 0.0;
	                Map<String, Map<String, Object>> dateWiseTotals = (Map<String, Map<String, Object>>) zReport.get("dateWiseTotals");
	                for (Map<String, Object> dateData : dateWiseTotals.values()) {
	                    Map<String, Double> paymentTotals = (Map<String, Double>) dateData.get("overallPaymentTotals");
	                    if (paymentTotals != null) {
	                        totalCash += paymentTotals.getOrDefault("Cash", 0.0);
	                    }
	                }
	                
	                // Get balance amount total
	                Double balanceTotal = (Double) zReport.get("totalBalanceAmount");
	                if (balanceTotal == null) {
	                    balanceTotal = 0.0;
	                }
	                
	                // Calculate difference
	                Double difference = totalCash;
	                if (bankingTotal != 0.0 || payoutTotal != 0.0 || balanceTotal != 0.0) {
	                    Double totalDeductions = bankingTotal + payoutTotal + balanceTotal;
	                    difference = totalCash - totalDeductions;
	                }
	                
	                // Add banking, payout, counts, difference and balance amount to the response
	                zReport.put("bankingTotal", bankingTotal);
	                zReport.put("payoutTotal", payoutTotal);
	                zReport.put("bankingCount", bankingCount);
	                zReport.put("payoutCount", payoutCount);
	                zReport.put("difference", difference);
	                zReport.put("balanceTotal", balanceTotal);
	                
	                // Save to database
	                SalesReportDto salesReportDto = transformToSalesReportDto(zReport);
	                ResponseDto saveResponse = salesReportService.save(salesReportDto);
	                if (saveResponse.getErrorCode() == 0) {
	                    log.info("Z-Report data saved successfully to salesReport table.");
	                } else {
	                    log.warn("Failed to save Z-Report data: {}", saveResponse.getErrorDescription());
	                }

	                transactionServiceBL.updateGenerateDateTime(
	                    (Integer) lastTransactionInfo.get("id"), endDate);
	                responseDto = serviceUtil.getServiceResponse(zReport);
	            } else {
	                log.info("Unable to generate Z-Report.");
	                responseDto = serviceUtil.getErrorServiceResponse(
	                    ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_Y_REPORT);
	            }
	        } else {
	            log.info("No transactions found to generate Z-Report.");
	            responseDto = serviceUtil.getErrorServiceResponse(
	                ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_TRANSACTIONS_FOUND);
	        }
	    } catch (Exception e) {
	        log.error("Exception occurred while generating Z-Report: {}", e.getMessage(), e);
	        responseDto = serviceUtil.getExceptionServiceResponseByProperties(
	            ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_Y_REPORT);
	    }
	    return responseDto;
	}

	private SalesReportDto transformToSalesReportDto(Map<String, Object> zReport) {
	    SalesReportDto dto = new SalesReportDto();
	    dto.setReportGeneratedBy((String) zReport.get("reportGeneratedBy"));
	    dto.setStartDate((LocalDateTime) zReport.get("startDate"));
	    dto.setEndDate((LocalDateTime) zReport.get("endDate"));
	    dto.setFullyTotalSales((Double) zReport.get("fullyTotalSales"));
	    dto.setReportType("zReport");

	    // Get banking and pay out totals and counts
	    Double bankingTotal = bankingServiceBL.getTotalBanking();
	    Double payoutTotal = payoutServiceBL.getTotalPayout();
	    
	    // Get counts for the specific date range
	    Integer bankingCount = bankingServiceBL.getBankingCount(dto.getStartDate(), dto.getEndDate());
	    Integer payoutCount = payoutServiceBL.getPayoutCount(dto.getStartDate(), dto.getEndDate());
	    
	    // Set banking and pay out data
	    dto.setBanking(bankingTotal);
	    dto.setPayout(payoutTotal);
	    dto.setBankingCount(bankingCount);
	    dto.setPayoutCount(payoutCount);
	    
	    // Calculate total cash payments from all dates
	    Double totalCash = 0.0;
	    Map<String, Map<String, Object>> dateWiseTotals = (Map<String, Map<String, Object>>) zReport.get("dateWiseTotals");
	    for (Map<String, Object> dateData : dateWiseTotals.values()) {
	        Map<String, Double> paymentTotals = (Map<String, Double>) dateData.get("overallPaymentTotals");
	        if (paymentTotals != null) {
	            totalCash += paymentTotals.getOrDefault("Cash", 0.0);
	        }
	    }
	    
	    // Get balance amount total
	    Double balanceTotal = (Double) zReport.get("totalBalanceAmount");
	    if (balanceTotal == null) {
	        balanceTotal = 0.0;
	    }
	    
	    // Calculate difference
	    Double difference = totalCash;
	    if (bankingTotal != 0.0 || payoutTotal != 0.0 || balanceTotal != 0.0) {
	        Double totalDeductions = bankingTotal + payoutTotal + balanceTotal;
	        difference = totalCash - totalDeductions;
	    }
	    
	    // If difference is negative, set it to 0
	    if (difference < 0) {
	        difference = 0.0;
	    }
	    
	    dto.setDifference(difference);

	    Map<String, Map<String, Object>> dateWiseTotalsMap = (Map<String, Map<String, Object>>) zReport.get("dateWiseTotals");
	    List<SalesDateDetailsDto> salesDateDetails = new ArrayList<>();

	    for (Map.Entry<String, Map<String, Object>> entry : dateWiseTotalsMap.entrySet()) {
	        String salesDate = entry.getKey();
	        Map<String, Object> details = entry.getValue();

	        SalesDateDetailsDto dateDetailsDto = new SalesDateDetailsDto();
	        dateDetailsDto.setSalesDate(salesDate);
	        dateDetailsDto.setTotalTransactions((Integer) details.get("totalTransactions"));
	        dateDetailsDto.setTotalSales((Double) details.get("totalSales"));

	        Map<String, Double> categoryTotalsMap = (Map<String, Double>) details.get("categoryTotals");
	        List<CategoryTotalsDto> categoryTotals = new ArrayList<>();
	        for (Map.Entry<String, Double> category : categoryTotalsMap.entrySet()) {
	            CategoryTotalsDto categoryDto = new CategoryTotalsDto();
	            categoryDto.setCategoryName(category.getKey());
	            categoryDto.setCategoryTotal(category.getValue());
	            categoryTotals.add(categoryDto);
	        }
	        dateDetailsDto.setCategoryTotals(categoryTotals);

	        Map<String, Double> overallPaymentTotalsMap = (Map<String, Double>) details.get("overallPaymentTotals");
	        List<OverallPaymentTotalsDto> overallPaymentTotals = new ArrayList<>();
	        for (Map.Entry<String, Double> payment : overallPaymentTotalsMap.entrySet()) {
	            OverallPaymentTotalsDto paymentDto = new OverallPaymentTotalsDto();
	            paymentDto.setPaymentMethod(payment.getKey());
	            paymentDto.setPaymentTotal(payment.getValue());
	            overallPaymentTotals.add(paymentDto);
	        }
	        dateDetailsDto.setOverallPaymentTotals(overallPaymentTotals);

	        Map<String, Map<String, Double>> userPaymentDetailsMap = (Map<String, Map<String, Double>>) details.get("userPaymentDetails");
	        List<UserPaymentDetailsDto> userPaymentDetails = new ArrayList<>();
	        for (Map.Entry<String, Map<String, Double>> userEntry : userPaymentDetailsMap.entrySet()) {
	            String userName = userEntry.getKey();
	            Map<String, Double> payments = userEntry.getValue();
	            for (Map.Entry<String, Double> payment : payments.entrySet()) {
	                UserPaymentDetailsDto userPaymentDto = new UserPaymentDetailsDto();
	                userPaymentDto.setUserName(userName);
	                userPaymentDto.setPaymentMethod(payment.getKey());
	                userPaymentDto.setPaymentTotal(payment.getValue());
	                userPaymentDetails.add(userPaymentDto);
	            }
	        }
	        dateDetailsDto.setUserPaymentDetails(userPaymentDetails);

	        salesDateDetails.add(dateDetailsDto);
	    }

	    dto.setSalesDateDetails(salesDateDetails);
	    return dto;
	}

	@Override
	public ResponseDto getAllPageTransaction(int pageNumber, int pageSize, Map<String, String> searchParameters) {
		log.info("TransactionServiceImpl.getAllPageTransaction() invoked");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = transactionServiceBL.getAllPageTransaction(pageNumber, pageSize, searchParameters);
			if (paginatedResponseDto != null) {
				log.info("Retrieve All Transaction Details.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrieve All Transaction details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_TRANSACTION_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Transaction details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_TRANSACTION_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getCashTotal(Integer userId) {
		log.info("TransactionServiceImpl.getCashTotal() invoked with userId: {}", userId);
		ResponseDto responseDto = null;
		try {
			Map<String, Object> lastTransactionInfo = transactionServiceBL.getLastTransactionInfo();
			LocalDateTime endDate = LocalDateTime.now();
			LocalDateTime startDate;

			if (lastTransactionInfo != null) {
				if (transactionDao.areAllGenerateDateTimesNull()) {
					log.info("All generateDateTime values are null, using dateTime of transaction ID 1");
					startDate = transactionDao.getDateTimeForTransactionIdOne();
					if (startDate == null) {
						log.warn("No transaction found with ID 1, using first transaction dateTime");
						startDate = transactionServiceBL.getFirstTransactionDateTime();
					}
				} else {
					log.info("Using the last non-null generateDateTime as startDate");
					startDate = transactionDao.getLastGenerateDateTime();
					if (startDate == null) {
						log.warn("No non-null generateDateTime found, falling back to first transaction dateTime");
						startDate = transactionServiceBL.getFirstTransactionDateTime();
					}
				}

				LocalDateTime nextStartDate = transactionServiceBL.getNextTransactionDateTimeAfter(startDate);
				if (nextStartDate != null) {
					log.info("Found next transaction after startDate, new startDate: {}", nextStartDate);
					startDate = nextStartDate;
				} else {
					log.warn("No transaction found after startDate: {}, using original startDate", startDate);
				}

				Map<String, Object> xReport = transactionServiceBL.getXReport(userId, startDate, endDate);
				if (xReport != null) {
					log.info("Cash total retrieved successfully.");
					
					// Get only cash amount from overallPaymentTotals
					Map<String, Double> paymentTotals = (Map<String, Double>) xReport.get("overallPaymentTotals");
					Double cashTotal = paymentTotals != null ? paymentTotals.getOrDefault("Cash", 0.0) : 0.0;
					
					Map<String, Object> response = new HashMap<>();
					response.put("difference", cashTotal);
					
					responseDto = serviceUtil.getServiceResponse(response);
				} else {
					log.info("Unable to retrieve cash total.");
					responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_TRANSACTION_BY_X_REPORT);
				}
			} else {
				log.info("No transactions found to retrieve cash total.");
				responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.ERR_NO_TRANSACTIONS_FOUND);
			}
		} catch (Exception e) {
			log.error("Exception occurred while retrieving cash total: {}", e.getMessage(), e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
				ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_TRANSACTION_BY_X_REPORT);
		}
		return responseDto;
	}
}
