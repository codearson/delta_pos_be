package com.pos_main.DaoImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.UserLogsDao;
import com.pos_main.Domain.Customer;
import com.pos_main.Domain.Stock;
import com.pos_main.Domain.Tax;
import com.pos_main.Domain.UserLogs;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.StockDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Dto.UserLogsDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.UserLogsTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserLogsDaoImpl extends BaseDaoImpl<UserLogs> implements UserLogsDao{

	@Autowired
	UserLogsTransformer userLogsTransformer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public UserLogsDto login(UserLogsDto userLogsDto) {
    	log.info("UserLogsImpl.login() invoked.");
        UserLogs userLogs = userLogsTransformer.reverseTransform(userLogsDto);
        getCurrentSession().saveOrUpdate(userLogs);
        return userLogsTransformer.transform(userLogs);
    }
    
    @Override
	@Transactional
	public UserLogsDto save(UserLogsDto userLogsDto) {
		log.info("UserLogsImpl.save() invoked.");
		UserLogs userLogs = userLogsTransformer.reverseTransform(userLogsDto);
		UserLogs saveuserLogs = saveOrUpdate(userLogs);
		return userLogsTransformer.transform(saveuserLogs);
	}
    
	@Override
	@Transactional
	public PaginatedResponseDto getAllPageUserLogs(int pageNumber, int pageSize, Boolean status, Map<String, String> searchParams) {
		log.info("UserLogsImpl.getAll()invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<UserLogs> allUserLogsList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM user_logs";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(UserLogs.class, "userLogs");
		
		// Add status filter if provided
		if (status != null) {
			criteria.add(Restrictions.eq("signOff", status));
		}
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allUserLogsList = criteria.list();
		recordCount = allUserLogsList.size();
		if (allUserLogsList != null && !allUserLogsList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allUserLogsList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allUserLogsList.stream().map(userLogs -> {
				return userLogsTransformer.transform(userLogs);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
}
