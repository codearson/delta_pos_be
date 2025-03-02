package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.TransactionDao;
import com.pos_main.Domain.Branch;
import com.pos_main.Domain.Transaction;
import com.pos_main.Domain.TransactionDetailsList;
import com.pos_main.Domain.TransactionPaymentMethod;
import com.pos_main.Domain.User;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TransactionDetailsListDto;
import com.pos_main.Dto.TransactionDto;
import com.pos_main.Dto.TransactionPaymentMethodDto;
import com.pos_main.Service.TransactionDetailsListService;
import com.pos_main.Service.TransactionPaymentMethodService;
import com.pos_main.Transformer.BranchTransformer;
import com.pos_main.Transformer.CustomerTransfomer;
import com.pos_main.Transformer.ShopDetailsTransformer;
import com.pos_main.Transformer.TransactionDetailsListTransformer;
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
    public TransactionDto save(TransactionDto transactionDto) {
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

        entityManager.flush();

        return transactionTransformer.transform(transaction);
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

            transactionDto.setTransactionDetailsList(transactionDetailsList);
            transactionDto.setTransactionPaymentMethod(transactionPaymentMethodList);

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


}
