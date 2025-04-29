package com.pos_main.DaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.DeviceAuthDao;
import com.pos_main.Domain.DeviceAuth;
import com.pos_main.Dto.DeviceAuthDto;
import com.pos_main.Transformer.DeviceAuthTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: DeviceAuthDaoImpl.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:40:47
 * @version 1.0
 **/

@Slf4j
@Repository
public class DeviceAuthDaoImpl extends BaseDaoImpl<DeviceAuth> implements DeviceAuthDao {

    @Autowired
    DeviceAuthTransformer deviceAuthTransformer;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public DeviceAuthDto saveDeviceAuth(DeviceAuthDto deviceAuthDto) {
        log.info("DeviceAuthDaoImpl.saveDeviceAuth() invoked.");
        DeviceAuth deviceAuth = deviceAuthTransformer.reverseTransform(deviceAuthDto);
        DeviceAuth savedDeviceAuth = saveOrUpdate(deviceAuth);
        return deviceAuthTransformer.transform(savedDeviceAuth);
    }
    
    @Transactional
    @Override
    public DeviceAuth getDeviceAuthById(Integer id) {
        log.info("DeviceAuthDaoImpl.getDeviceAuthById() invoked with id: {}", id);
        return entityManager.find(DeviceAuth.class, id);
    }
    
    @Transactional
    @Override
    public DeviceAuth getDeviceAuthByTillId(String tillId) {
        log.info("DeviceAuthDaoImpl.getDeviceAuthByTillId() invoked with tillId: {}", tillId);
        try {
            TypedQuery<DeviceAuth> query = entityManager.createQuery(
                    "SELECT da FROM DeviceAuth da WHERE da.tillId = :tillId", DeviceAuth.class);
            query.setParameter("tillId", tillId);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("No DeviceAuth found for tillId: {}", tillId, e);
            return null;
        }
    }
}