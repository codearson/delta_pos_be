package com.pos_main.DaoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.TransactionDao;
import com.pos_main.Domain.Banking;
import com.pos_main.Domain.Branch;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.Transaction;
import com.pos_main.Domain.TransactionDetailsList;
import com.pos_main.Domain.TransactionEmployee;
import com.pos_main.Domain.TransactionPaymentMethod;
import com.pos_main.Domain.User;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDetailsListDto;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Dto.TransactionEmployeeDto;
import com.pos_main.Dto.TransactionPaymentMethodDto;
import com.pos_main.Service.TransactionDetailsListService;
import com.pos_main.Service.TransactionEmployeeService;
import com.pos_main.Service.TransactionPaymentMethodService;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.BranchTransformer;
import com.pos_main.Transformer.CustomerTransfomer;
import com.pos_main.Transformer.ShopDetailsTransformer;
import com.pos_main.Transformer.TransactionDetailsListTransformer;
import com.pos_main.Transformer.TransactionEmployeeTransformer;
import com.pos_main.Transformer.TransactionPaymentMethodTransformer;
import com.pos_main.Transformer.TransactionTransformer;
import com.pos_main.Transformer.UserTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: TransactionDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 11, 2025
 * @version 1.0
 **/

@Slf4j
@Repository
public class TransactionDaoImpl extends BaseDaoImpl<Transaction> implements TransactionDao {
	
	@Autowired
    TransactionTransformer transactionTransformer;

    @Autowired
    TransactionDetailsListTransformer transactionDetailsListTransformer;

    @Autowired
    TransactionPaymentMethodTransformer transactionPaymentMethodTransformer;
    
    @Autowired
    TransactionEmployeeTransformer transactionEmployeeTransformer;
    
    @Autowired
    BranchTransformer branchTransformer;
    
    @Autowired
    ShopDetailsTransformer shopDetailsTransformer;
    
    @Autowired
    UserTransfomer userTransformer;
    
    @Autowired
    CustomerTransfomer customerTransformer;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    TransactionDetailsListService transactionDetailsListService;
    
    @Autowired
    TransactionPaymentMethodService transactionPaymentMethodService;
    
    @Autowired
    TransactionEmployeeService transationEmployeeService;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    @Override
	@Transactional
	public List<TransactionDto> getTransactionByDateRange (LocalDateTime startDate, LocalDateTime endDate) {
		log.info("TransactionDaoImpl.getTransactionByDateRange() invoked");

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        cq.select(root).where(
            cb.between(root.get("dateTime"), startDate, endDate)
        );

        List<Transaction> transactionList = entityManager.createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            ResponseDto transactionDetailsListResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList = 
                transactionDetailsListResponse.getResponseDto() instanceof List<?> 
                ? (List<TransactionDetailsListDto>) transactionDetailsListResponse.getResponseDto() 
                : new ArrayList<>();
            
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethodList = 
                paymentMethodResponse.getResponseDto() instanceof List<?>
                ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                : new ArrayList<>();
            
            transactionPaymentMethodList.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionByBranchId(Integer branchId) {
        log.info("TransactionDaoImpl.getTransactionByBranchId() invoked with branchId: {}", branchId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        Join<Transaction, Branch> branchJoin = root.join("branch");
        cq.select(root).where(cb.equal(branchJoin.get("id"), branchId));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            ResponseDto transactionDetailsListResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList = 
                transactionDetailsListResponse.getResponseDto() instanceof List<?> 
                ? (List<TransactionDetailsListDto>) transactionDetailsListResponse.getResponseDto() 
                : new ArrayList<>();
            
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethodList = 
                paymentMethodResponse.getResponseDto() instanceof List<?>
                ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                : new ArrayList<>();
            
            transactionPaymentMethodList.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionById(Integer id) {
        log.info("TransactionDaoImpl.getTransactionById() invoked with id: {}", id);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        cq.select(root).where(cb.equal(root.get("id"), id));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            // Hide password in userDto inside TransactionDto
            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            // Fetch and process transaction details
            ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList =
                transactionDetailsResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionDetailsList
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Fetch and process payment methods
            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethod =
                paymentMethodResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionPaymentMethod
            transactionPaymentMethod.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Set the updated lists
            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethod);

            return transactionDto;
        }).collect(Collectors.toList());
    }

    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionByUserId(Integer userId) {
        log.info("TransactionDaoImpl.getTransactionByUserId() invoked with userId: {}", userId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        Join<Transaction, User> userJoin = root.join("user");
        cq.select(root).where(cb.equal(userJoin.get("id"), userId));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList =
                transactionDetailsResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto() 
                    : new ArrayList<>();

            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethod =
                paymentMethodResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto() 
                    : new ArrayList<>();

            transactionPaymentMethod.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethod);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionByCustomerId(Integer customerId) {
        log.info("TransactionDaoImpl.getTransactionByCustomerId() invoked with customerId: {}", customerId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        Join<Transaction, Customer> customerJoin = root.join("customer");
        cq.select(root).where(cb.equal(customerJoin.get("id"), customerId));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            // Hide password in userDto inside TransactionDto
            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            // Fetch and process transaction details
            ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList =
                transactionDetailsResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionDetailsList
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Fetch and process payment methods
            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethod =
                paymentMethodResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionPaymentMethod
            transactionPaymentMethod.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Set the updated lists
            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethod);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionByPaymentMethodId(Integer paymentMethodId) {
        log.info("TransactionDaoImpl.getTransactionByPaymentMethodId() invoked with paymentMethodId: {}", paymentMethodId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        Join<Transaction, TransactionPaymentMethod> paymentMethodJoin = root.join("transactionPaymentMethod");

        cq.select(root).where(cb.equal(paymentMethodJoin.get("paymentMethod").get("id"), paymentMethodId));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            ResponseDto transactionDetailsListResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList = 
                transactionDetailsListResponse.getResponseDto() instanceof List<?> 
                ? (List<TransactionDetailsListDto>) transactionDetailsListResponse.getResponseDto() 
                : new ArrayList<>();
            
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethodList = 
                paymentMethodResponse.getResponseDto() instanceof List<?>
                ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                : new ArrayList<>();
            
            transactionPaymentMethodList.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);

            return transactionDto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TransactionDto save(TransactionDto transactionDto, String alertMessage) {
        log.info("TransactionDaoImpl.save() invoked.");

        Transaction transaction = transactionTransformer.reverseTransform(transactionDto);
        entityManager.persist(transaction);
        entityManager.flush();

        for (TransactionDetailsListDto detailsDto : transactionDto.getTransactionDetailsList()) {
            TransactionDetailsList details = transactionDetailsListTransformer.reverseTransform(detailsDto);
            details.setTransaction(transaction);
            entityManager.persist(details);
        }

        for (TransactionPaymentMethodDto paymentDto : transactionDto.getTransactionPaymentMethod()) {
            TransactionPaymentMethod payment = transactionPaymentMethodTransformer.reverseTransform(paymentDto);
            payment.setTransaction(transaction);
            entityManager.persist(payment);
        }
        
        for (TransactionEmployeeDto employeeDto : transactionDto.getTransactionEmployee()) {
            TransactionEmployee employee = transactionEmployeeTransformer.reverseTransform(employeeDto);
            employee.setTransaction(transaction);
            entityManager.persist(employee);
        }

        entityManager.flush();
        
        TransactionDto dto = transactionTransformer.transform(transaction);
        dto.setNotification(alertMessage);

        return dto;
    }
    
    @Override
    @Transactional
    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        log.info("TransactionDaoImpl.update() invoked.");

        Transaction transaction = entityManager.find(Transaction.class, transactionDto.getId());
        
        if (transaction == null) {
            throw new EntityNotFoundException("Transaction not found with ID: " + transactionDto.getId());
        }

        transaction.setTotalAmount(transactionDto.getTotalAmount());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setIsActive(transactionDto.getIsActive());
        
        if (transactionDto.getBranchDto() != null) {
            transaction.setBranch(branchTransformer.reverseTransform(transactionDto.getBranchDto()));
        }
        if (transactionDto.getShopDetailsDto() != null) {
            transaction.setShopDetails(shopDetailsTransformer.reverseTransform(transactionDto.getShopDetailsDto()));
        }
        if (transactionDto.getUserDto() != null) {
            transaction.setUser(userTransformer.reverseTransform(transactionDto.getUserDto()));
        }
        if (transactionDto.getCustomerDto() != null) {
            transaction.setCustomer(customerTransformer.reverseTransform(transactionDto.getCustomerDto()));
        }

        entityManager.flush();

        for (TransactionDetailsListDto detailsDto : transactionDto.getTransactionDetailsList()) {
            TransactionDetailsList details = transactionDetailsListTransformer.reverseTransform(detailsDto);
            details.setTransaction(transaction);
            
            if (details.getId() == null) {
                entityManager.persist(details);
            } else {
                entityManager.merge(details);
            }
        }

        for (TransactionPaymentMethodDto paymentDto : transactionDto.getTransactionPaymentMethod()) {
            TransactionPaymentMethod payment = transactionPaymentMethodTransformer.reverseTransform(paymentDto);
            payment.setTransaction(transaction);
            
            if (payment.getId() == null) {
                entityManager.persist(payment);
            } else {
                entityManager.merge(payment);
            }
        }

        entityManager.flush();

        return transactionTransformer.transform(transaction);
    }

    @Override
    @Transactional
    public List<TransactionDto> getAllTransaction() {
        log.info("TransactionDaoImpl.getAllTransaction() invoked");

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        cq.select(root);

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto != null && transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            
            ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList =
                transactionDetailsResponse.getResponseDto() instanceof List<?>
                    ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto()
                    : new ArrayList<>();

            for (TransactionDetailsListDto details : transactionDetailsList) {
                if (details != null && details.getTransactionDto() != null 
                    && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            }

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethodList =
                paymentMethodResponse.getResponseDto() instanceof List<?>
                    ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                    : new ArrayList<>();
            
            ResponseDto employeeResponse = transationEmployeeService.getByTransactionId(transaction.getId());
            List<TransactionEmployeeDto> transactionEmployeeList =
                employeeResponse.getResponseDto() instanceof List<?>
                    ? (List<TransactionEmployeeDto>) employeeResponse.getResponseDto()
                    : new ArrayList<>();

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);
            transactionDto.setTransactionEmployee(transactionEmployeeList);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TransactionDto> getTransactionByStatus(Boolean isActive) {
        log.info("TransactionDaoImpl.getTransactionById() invoked with status: {}", isActive);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        cq.select(root).where(cb.equal(root.get("isActive"), isActive));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            // Hide password in userDto inside TransactionDto
            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            // Fetch and process transaction details
            ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList =
                transactionDetailsResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionDetailsList
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Fetch and process payment methods
            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethod =
                paymentMethodResponse.getResponseDto() instanceof List<?> 
                    ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto() 
                    : new ArrayList<>();

            // Hide password in userDto inside transactionPaymentMethod
            transactionPaymentMethod.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            // Set the updated lists
            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethod);

            return transactionDto;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<TransactionDto> getTransactionByProductId(Integer productId) {
        log.info("TransactionDaoImpl.getTransactionByProductId() invoked with productId: {}", productId);

        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
        Root<Transaction> root = cq.from(Transaction.class);

        Join<Transaction, TransactionDetailsList> detailsListJoin = root.join("transactionDetailsList");

        cq.select(root).where(cb.equal(detailsListJoin.get("product").get("id"), productId));

        List<Transaction> transactionList = getCurrentSession().createQuery(cq).getResultList();

        return transactionList.stream().map(transaction -> {
            TransactionDto transactionDto = transactionTransformer.transform(transaction);

            if (transactionDto.getUserDto() != null) {
                transactionDto.getUserDto().setPassword(null);
            }

            ResponseDto transactionDetailsListResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
            List<TransactionDetailsListDto> transactionDetailsList = 
                transactionDetailsListResponse.getResponseDto() instanceof List<?> 
                ? (List<TransactionDetailsListDto>) transactionDetailsListResponse.getResponseDto() 
                : new ArrayList<>();
            
            transactionDetailsList.forEach(details -> {
                if (details.getTransactionDto() != null && details.getTransactionDto().getUserDto() != null) {
                    details.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
            List<TransactionPaymentMethodDto> transactionPaymentMethodList = 
                paymentMethodResponse.getResponseDto() instanceof List<?>
                ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                : new ArrayList<>();
            
            transactionPaymentMethodList.forEach(payment -> {
                if (payment.getTransactionDto() != null && payment.getTransactionDto().getUserDto() != null) {
                    payment.getTransactionDto().getUserDto().setPassword(null);
                }
            });

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);

            return transactionDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    public Map<String, Object> getLastTransactionInfo() {
        log.info("TransactionDaoImpl.getLastTransactionInfo() invoked");
        Transaction lastTransaction = entityManager.createQuery(
                "SELECT t FROM Transaction t ORDER BY t.id DESC", Transaction.class)
                .setMaxResults(1)
                .getSingleResult();

        if (lastTransaction != null) {
            Map<String, Object> result = new HashMap<>();
            result.put("id", lastTransaction.getId());
            result.put("generateDateTime", lastTransaction.getGenerateDateTime());
            return result;
        }
        return null;
    }

    @Override
    public LocalDateTime getFirstTransactionDateTime() {
        log.info("TransactionDaoImpl.getFirstTransactionDateTime() invoked");
        Transaction firstTransaction = entityManager.createQuery(
                "SELECT t FROM Transaction t ORDER BY t.id ASC", Transaction.class)
                .setMaxResults(1)
                .getSingleResult();
        return firstTransaction != null ? firstTransaction.getDateTime() : null;
    }

    @Override
    public boolean areAllGenerateDateTimesNull() {
        log.info("TransactionDaoImpl.areAllGenerateDateTimesNull() invoked");
        Long count = entityManager.createQuery(
                "SELECT COUNT(t) FROM Transaction t WHERE t.generateDateTime IS NOT NULL", Long.class)
                .getSingleResult();
        return count == 0;
    }

    @Override
    public LocalDateTime getDateTimeForTransactionIdOne() {
        log.info("TransactionDaoImpl.getDateTimeForTransactionIdOne() invoked");
        Transaction transaction = entityManager.find(Transaction.class, 1);
        return transaction != null ? transaction.getDateTime() : null;
    }

    @Override
    public LocalDateTime getLastGenerateDateTime() {
        log.info("TransactionDaoImpl.getLastGenerateDateTime() invoked");
        Transaction lastTransaction = entityManager.createQuery(
                "SELECT t FROM Transaction t WHERE t.generateDateTime IS NOT NULL ORDER BY t.generateDateTime DESC", Transaction.class)
                .setMaxResults(1)
                .getSingleResult();
        return lastTransaction != null ? lastTransaction.getGenerateDateTime() : null;
    }

    @Override
    @Transactional
    public void updateGenerateDateTime(Integer transactionId, LocalDateTime generateDateTime) {
        log.info("TransactionDaoImpl.updateGenerateDateTime() invoked for transactionId: {}", transactionId);
        
        Transaction transaction = entityManager.find(Transaction.class, transactionId);
        if (transaction != null) {
            transaction.setGenerateDateTime(generateDateTime);
            entityManager.merge(transaction);
        } else {
            log.warn("Transaction with ID {} not found", transactionId);
            throw new EntityNotFoundException("Transaction not found with ID: " + transactionId);
        }

        Banking lastBanking = entityManager.createQuery(
                "SELECT b FROM Banking b ORDER BY b.id DESC", Banking.class)
                .setMaxResults(1)
                .getSingleResult();
        
        if (lastBanking != null) {
            lastBanking.setGeneratedDateTime(generateDateTime);
            entityManager.merge(lastBanking);
        } else {
            log.warn("No Banking records found to update generatedDateTime");
        }
    }
    
    @Override
    public LocalDateTime getNextTransactionDateTimeAfter(LocalDateTime startDate) {
        log.info("TransactionDaoImpl.getNextTransactionDateTimeAfter() invoked with startDate: {}", startDate);
        Transaction nextTransaction = entityManager.createQuery(
                "SELECT t FROM Transaction t WHERE t.dateTime > :startDate ORDER BY t.dateTime ASC", Transaction.class)
                .setParameter("startDate", startDate)
                .setMaxResults(1)
                .getSingleResult();
        return nextTransaction != null ? nextTransaction.getDateTime() : null;
    }

    @Override
    @Transactional
    public PaginatedResponseDto getAllPageTransaction(int pageNumber, int pageSize, Map<String, String> searchParams) {
        log.info("TransactionDaoImpl.getAllPageTransaction() invoked");
        
        PaginatedResponseDto paginatedResponseDto = null;
        List<Transaction> allTransactionList = null;
        
        // Count total transactions
        String countString = "SELECT COUNT(*) FROM transaction";
        int count = jdbcTemplate.queryForObject(countString, Integer.class);

        if (pageSize == 0) {
            pageSize = count;
        }

        // Fetch paginated transaction list
        Criteria criteria = getCurrentSession().createCriteria(Transaction.class, "transaction");
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult((pageNumber - 1) * pageSize);
        criteria.setMaxResults(pageSize);
        
        allTransactionList = criteria.list();

        if (allTransactionList != null && !allTransactionList.isEmpty()) {
            // Transform and enrich transactions with additional data
            List<TransactionDto> transactionDtoList = allTransactionList.stream().map(transaction -> {
                TransactionDto transactionDto = transactionTransformer.transform(transaction);

                if (transactionDto != null && transactionDto.getUserDto() != null) {
                    transactionDto.getUserDto().setPassword(null); // Mask password for security
                }

                // Fetch transaction details
                ResponseDto transactionDetailsResponse = transactionDetailsListService.getByTransactionId(transaction.getId());
                List<TransactionDetailsListDto> transactionDetailsList =
                    transactionDetailsResponse.getResponseDto() instanceof List<?>
                        ? (List<TransactionDetailsListDto>) transactionDetailsResponse.getResponseDto()
                        : new ArrayList<>();

                for (TransactionDetailsListDto details : transactionDetailsList) {
                    if (details != null && details.getTransactionDto() != null 
                        && details.getTransactionDto().getUserDto() != null) {
                        details.getTransactionDto().getUserDto().setPassword(null);
                    }
                }

                // Fetch payment methods
                ResponseDto paymentMethodResponse = transactionPaymentMethodService.getByTransactionId(transaction.getId());
                List<TransactionPaymentMethodDto> transactionPaymentMethodList =
                    paymentMethodResponse.getResponseDto() instanceof List<?>
                        ? (List<TransactionPaymentMethodDto>) paymentMethodResponse.getResponseDto()
                        : new ArrayList<>();
                
                // Fetch employee user
                ResponseDto employeeResponse = transationEmployeeService.getByTransactionId(transaction.getId());
                List<TransactionEmployeeDto> transactionEmployeeList =
                    employeeResponse.getResponseDto() instanceof List<?>
                        ? (List<TransactionEmployeeDto>) employeeResponse.getResponseDto()
                        : new ArrayList<>();

                // Set the additional details
                transactionDto.setTransactionDetailsList(transactionDetailsList);
                transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);
                transactionDto.setTransactionEmployee(transactionEmployeeList);

                return transactionDto;
            }).collect(Collectors.toList());

            // Map the transformed data to the paginated response
            paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(transactionDtoList, pageNumber, pageSize, count);
            paginatedResponseDto.setPayload(transactionDtoList);
        }
        
        return paginatedResponseDto;
    }


}
