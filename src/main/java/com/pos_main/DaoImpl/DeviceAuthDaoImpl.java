package com.pos_main.DaoImpl;

import java.util.List;

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
    
    @Transactional
    @Override
    public List<DeviceAuth> getAllPending() {
        log.info("DeviceAuthDaoImpl.getAllPending() invoked");
        try {
            TypedQuery<DeviceAuth> query = entityManager.createQuery(
                    "SELECT da FROM DeviceAuth da WHERE da.approveStatus = :status", DeviceAuth.class);
            query.setParameter("status", "Pending");
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error retrieving pending DeviceAuth records", e);
            return null;
        }
    }
    
    @Transactional
    @Override
    public List<DeviceAuth> getAllApprovedOrDeclined() {
        log.info("DeviceAuthDaoImpl.getAllApprovedOrDeclined() invoked");
        try {
            TypedQuery<DeviceAuth> query = entityManager.createQuery(
                    "SELECT da FROM DeviceAuth da WHERE da.approveStatus IN (:approved, :declined)", DeviceAuth.class);
            query.setParameter("approved", "Approved");
            query.setParameter("declined", "Declined");
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error retrieving approved or declined DeviceAuth records", e);
            return null;
        }
    }
    
    @Transactional
    @Override
    public DeviceAuth getDeviceAuthByTillName(String tillName) {
        log.info("DeviceAuthDaoImpl.getDeviceAuthByTillName() invoked with tillName: {}", tillName);
        try {
            TypedQuery<DeviceAuth> query = entityManager.createQuery(
                    "SELECT da FROM DeviceAuth da WHERE LOWER(da.tillName) LIKE LOWER(:tillName)", DeviceAuth.class);
            query.setParameter("tillName", "%" + tillName + "%");
            return query.getSingleResult();
        } catch (Exception e) {
            log.error("No DeviceAuth found for tillName: {}", tillName, e);
            return null;
        }
    }
}