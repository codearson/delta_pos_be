package com.pos_main.DaoImpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.ManagerToggleDao;
import com.pos_main.Domain.ManagerToggle;
import com.pos_main.Dto.ManagerToggleDto;
import com.pos_main.Transformer.ManagerToggleTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: ManagerToggleDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:55:02
 * @version 1.0
 **/

@Slf4j
@Repository
public class ManagerToggleDaoImpl extends BaseDaoImpl<ManagerToggle> implements ManagerToggleDao {
    
    @Autowired
    ManagerToggleTransformer managerToggleTransformer;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    @Override
    @Transactional
    public ManagerToggleDto save(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleDaoImpl.save() invoked.");
        ManagerToggle toggle = managerToggleTransformer.reverseTransform(managerToggleDto);
        ManagerToggle savedToggle = saveOrUpdate(toggle);
        return managerToggleTransformer.transform(savedToggle);
    }
    
    @Override
    @Transactional
    public ManagerToggleDto update(ManagerToggleDto managerToggleDto) {
        log.info("ManagerToggleDaoImpl.update() invoked");
        ManagerToggle toggle = managerToggleTransformer.reverseTransform(managerToggleDto);
        ManagerToggle updatedToggle = saveOrUpdate(toggle);
        return managerToggleTransformer.transform(updatedToggle);
    }
    
    @Override
    @Transactional
    public ManagerToggleDto checkToggleAvailability(Integer id) {
        Criteria criteria = getCurrentSession().createCriteria(ManagerToggle.class, "toggle");
        criteria.add(Restrictions.eq("toggle.id", id));
        ManagerToggle toggle = (ManagerToggle) criteria.uniqueResult();
        return toggle != null ? managerToggleTransformer.transform(toggle) : null;
    }
    
    @Override
    @Transactional
    public List<ManagerToggleDto> getAll() {
        log.info("ManagerToggleDaoImpl.getAll() invoked");
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ManagerToggle> cq = cb.createQuery(ManagerToggle.class);
        Root<ManagerToggle> root = cq.from(ManagerToggle.class);
        
        cq.select(root);
        
        List<ManagerToggle> toggleList = getCurrentSession().createQuery(cq).getResultList();
        return toggleList.stream()
            .map(managerToggleTransformer::transform)
            .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<ManagerToggleDto> getByName(String action) {
        log.info("ManagerToggleDaoImpl.getByName() invoked");
        CriteriaBuilder cb = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<ManagerToggle> cq = cb.createQuery(ManagerToggle.class);
        Root<ManagerToggle> root = cq.from(ManagerToggle.class);
        
        cq.select(root)
            .where(
                cb.and(
                    cb.equal(root.get("action"), action),
                    cb.equal(root.get("isActive"), true)
                )
            );
        
        List<ManagerToggle> toggleList = getCurrentSession().createQuery(cq).getResultList();
        return toggleList.stream()
            .map(managerToggleTransformer::transform)
            .collect(Collectors.toList());
    }
}