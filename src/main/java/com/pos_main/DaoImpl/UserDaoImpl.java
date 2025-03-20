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

import com.pos_main.Dao.UserDao;
import com.pos_main.Domain.User;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.UserDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.UserTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 11:22:09 AM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Autowired
	UserTransfomer userTransfomer;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	@Transactional
	public UserDto saveUser(UserDto userDto) {
		log.info("UserDaoImpl.saveUser() invoked.");
		userDto.setCreatedDate(LocalDateTime.now());
		User user = userTransfomer.reverseTransform(userDto);
		User saveUser = saveOrUpdate(user);
		return userTransfomer.transform(saveUser);
	}

	@Override
	@Transactional
	public User loadByUsername(String username) {

		Criteria criteria = getCurrentSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("user.emailAddress", username));
		User user=(User)criteria.uniqueResult();
		return (User) criteria.uniqueResult();

	}

	@Override
	@Transactional
	public User findByByEmail(String email) {
		Criteria criteria = getCurrentSession().createCriteria(User.class, "user")
				.add(Restrictions.eq("user.emailAddress", email));
		User user=(User)criteria.uniqueResult();
		return (User) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public PaginatedResponseDto getAll(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("UserDaoImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<User> allUserList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM user";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);
		if (pageSize == 0) {
			pageSize = count;
		}
		Criteria criteria = getCurrentSession().createCriteria(User.class, "user");
//		criteria.add(Restrictions.eq("isActive", true));
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allUserList = criteria.list();
		recordCount = allUserList.size();
		if (allUserList != null && !allUserList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allUserList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allUserList.stream().map(Invoice -> {
				return userTransfomer.transform(Invoice);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
	
	@Override
    public List<UserDto> getUserByName(String firstName, String lastName) {
        org.hibernate.Session session = null;
        List<UserDto> userDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class, "user");
            criteria.add(Restrictions.eq("isActive", true));

            if (firstName != null && !firstName.isEmpty()) {
                criteria.add(Restrictions.eq("user.firstName", firstName));
            }
            if (lastName != null && !lastName.isEmpty()) {
                criteria.add(Restrictions.eq("user.lastName", lastName));
            }

            List<User> userList = criteria.list();

            if (userList != null && !userList.isEmpty()) {
                userDtoList = new ArrayList<>();
                for (User user : userList) {
                    userDtoList.add(userTransfomer.transform(user));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log this or handle with custom exception handling
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return userDtoList;
    }
	
	@Override
    public List<UserDto> getUserById(Integer id) {
        org.hibernate.Session session = null;
        List<UserDto> userDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class, "user");
            criteria.add(Restrictions.eq("user.id", id));
            criteria.add(Restrictions.eq("isActive", true));
            
            List<User> userList = criteria.list();

            if (userList != null && !userList.isEmpty()) {
                userDtoList = new ArrayList<>();
                for (User user : userList) {
                    userDtoList.add(userTransfomer.transform(user));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return userDtoList;
    }
	
	@Override
	public List<UserDto> getUserByRole(String userRole) {
	    org.hibernate.Session session = null;
	    List<UserDto> userDtoList = null;
	    try {
	        session = sessionFactory.openSession();
	        Criteria criteria = session.createCriteria(User.class, "user");

	        criteria.createAlias("user.userRole", "role"); // Join with UserRole table

	        criteria.add(Restrictions.eq("role.userRole", userRole));
	        criteria.add(Restrictions.eq("isActive", true));

	        List<User> userList = criteria.list();

	        if (userList != null && !userList.isEmpty()) {
	            userDtoList = new ArrayList<>();
	            for (User user : userList) {
	                userDtoList.add(userTransfomer.transform(user));
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace(); 
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	    return userDtoList;
	}
	
	@Override
	@Transactional
	public UserDto update(UserDto userDto) {
	    log.info("UserDaoImpl.update() invoked.");
	    User user = userTransfomer.reverseTransform(userDto);
	    User updateUserDetails = saveOrUpdate(user);
	    user.setModifiedDate(LocalDateTime.now());
	    return userTransfomer.transform(updateUserDetails);
	}
	
	@Override
	@Transactional
	public UserDto updateStatus(UserDto userDto) {
	    log.info("UserDaoImpl.update() invoked.");
	    User user = userTransfomer.reverseTransform(userDto);
	    //user.setIsActive(true);
	    return userTransfomer.transform(user);
	}

	@Override
	@Transactional
	public UserDto checkUserAvailability(Integer userId) {
		Criteria criteria = getCurrentSession().createCriteria(User.class, "user");
		criteria.add(Restrictions.eq("user.id", userId));
		User user = (User) criteria.uniqueResult();
		UserDto userDto = null;
		if (user != null) {
			userDto = userTransfomer.transform(user);
		}
		return userDto;
	}
	
	@Override
    public List<UserDto> getUserByEmailAddress(String emailAddress) {
        org.hibernate.Session session = null;
        List<UserDto> userDtoList = null;
        try {
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class, "user");
            criteria.add(Restrictions.eq("user.emailAddress", emailAddress));
            criteria.add(Restrictions.eq("isActive", true));
            
            List<User> userList = criteria.list();

            if (userList != null && !userList.isEmpty()) {
                userDtoList = new ArrayList<>();
                for (User user : userList) {
                    userDtoList.add(userTransfomer.transform(user));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return userDtoList;
    }


}
