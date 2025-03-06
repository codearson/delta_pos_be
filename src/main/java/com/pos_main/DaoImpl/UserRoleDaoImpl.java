package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.UserRoleDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.UserRole;
import com.pos_main.Dto.CustomerDto;
import com.pos_main.Dto.UserRoleDto;
import com.pos_main.Transformer.UserRoleTransfomer;

import lombok.extern.slf4j.Slf4j;

/**
 * Feb 5, 2024 1:40:18 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Slf4j
@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {

	@Autowired
	UserRoleTransfomer userRoleTransfomer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserRoleDto saveUserRole(UserRoleDto userRoleDto) {
		log.info("UserRoleDaoImpl.saveUserRole() invoked.");
		UserRole userRole = userRoleTransfomer.reverseTransform(userRoleDto);
		UserRole saveUserRole = saveOrUpdate(userRole);
		return userRoleTransfomer.transform(saveUserRole);
	}
	
	@Override
	@Transactional
	public List<UserRoleDto> getAllUserRole() {
		log.info("UserRoleDaoImpl.getAllUserRole() invoked");
		Criteria criteria = getCurrentSession().createCriteria(UserRole.class, "userRole");
	 	criteria.add(Restrictions.eq("isActive", true));
		List<UserRoleDto> userRoleDtoList = null;
		List<UserRole> userRoleList = (List<UserRole>) criteria.list();
		if (userRoleList != null && !userRoleList.isEmpty()) {
			userRoleDtoList = new ArrayList<>();
			for (UserRole userRole : userRoleList) {
				userRoleDtoList.add(userRoleTransfomer.transform(userRole));
			}
		}
		return userRoleDtoList;
	}

}
