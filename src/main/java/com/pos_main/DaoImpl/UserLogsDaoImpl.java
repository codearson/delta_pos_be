package com.pos_main.DaoImpl;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.UserLogsDao;
import com.pos_main.Domain.Stock;
import com.pos_main.Domain.Tax;
import com.pos_main.Domain.UserLogs;
import com.pos_main.Dto.StockDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Dto.UserLogsDto;
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
}
