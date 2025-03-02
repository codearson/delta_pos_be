package com.pos_main.DaoImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.CustomerDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.Product;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.CustomerTransfomer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {
	
	@Autowired
	CustomerTransfomer customerTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Transactional
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		log.info("CustomerDaoImpl.saveCustomer() invoked.");
		customerDto.setCreatedDate(LocalDateTime.now());
		Customer customer = customerTransformer.reverseTransform(customerDto);
		Customer saveCustomer = saveOrUpdate(customer);
		return customerTransformer.transform(saveCustomer);
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllCustomer(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("CustomerDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Customer> allCustomerList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM customer";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Customer.class, "customer");
	 	criteria.add(Restrictions.eq("isActive", true));
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allCustomerList = criteria.list();
		recordCount = allCustomerList.size();
		if (allCustomerList != null && !allCustomerList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allCustomerList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allCustomerList.stream().map(Invoice -> {
				return customerTransformer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}

	@Override
    public List<CustomerDto> getCustomerBySearch(String firstName, String lastName) {
        org.hibernate.Session session = null;
        List<CustomerDto> customerDtoList = null;
        try {
        	session = sessionFactory.openSession();

            Criteria criteria = session.createCriteria(Customer.class, "customer");
    	 	criteria.add(Restrictions.eq("isActive", true));

            if (firstName != null && !firstName.isEmpty()) {
                criteria.add(Restrictions.eq("customer.firstName", firstName));
            }
            if (lastName != null && !lastName.isEmpty()) {
                criteria.add(Restrictions.eq("customer.lastName", lastName));
            }

            List<Customer> customerList = criteria.list();

            if (customerList != null && !customerList.isEmpty()) {
                customerDtoList = new ArrayList<>();
                for (Customer customer : customerList) {
                    customerDtoList.add(customerTransformer.transform(customer));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log this or handle with custom exception handling
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return customerDtoList;
    }
	
	@Override
    public List<CustomerDto> getCustomerById(Integer id) {
        org.hibernate.Session session = null;
        List<CustomerDto> customerDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Customer.class, "customer");
            criteria.add(Restrictions.eq("isActive", true));
            criteria.add(Restrictions.eq("customer.id", id));
            List<Customer> customerList = criteria.list();

            if (customerList != null && !customerList.isEmpty()) {
            	customerDtoList = new ArrayList<>();
                for (Customer customer : customerList) {
                	customerDtoList.add(customerTransformer.transform(customer));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return customerDtoList;
    }

}
