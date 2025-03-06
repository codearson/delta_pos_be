package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos_main.Dao.PurchaseListDao;
import com.pos_main.Domain.PurchaseList;
import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Transformer.PurchaseListTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PurchaseListDaoImpl extends BaseDaoImpl<PurchaseList> implements PurchaseListDao {

    @Autowired
    private PurchaseListTransformer purchaseListTransformer;

    @Autowired
    private EntityManager entityManager; // Replace SessionFactory with EntityManager

    @Transactional
    public PurchaseList savePurchaseList(PurchaseList purchaseList) {
        log.info("PurchaseListDaoImpl.savePurchaseList() invoked.");
        if (purchaseList == null) {
            log.error("PurchaseList entity is null");
            throw new IllegalArgumentException("PurchaseList entity cannot be null");
        }
        entityManager.persist(purchaseList);
        return purchaseList;
    }
    
    @Override
	@Transactional
	public List<PurchaseListDto> getAll() {
		log.info("PurchaseListDaoImpl.getAll() invoked");
		Criteria criteria = getCurrentSession().createCriteria(PurchaseList.class, "purchaseList");
		List<PurchaseListDto> purchaseListDtoList = null;
		List<PurchaseList> purchaseListList = (List<PurchaseList>) criteria.list();
		if (purchaseListList != null && !purchaseListList.isEmpty()) {
			purchaseListDtoList = new ArrayList<>();
			for (PurchaseList purchaseList : purchaseListList) {
				purchaseListDtoList.add(purchaseListTransformer.transform(purchaseList));
			}
		}
		return purchaseListDtoList;
	}
    
    @Override
    @Transactional
    public boolean deleteAll() {
        log.info("PurchaseListDaoImpl.deleteAll() invoked");
        try {
            int deletedCount = entityManager.createQuery("DELETE FROM PurchaseList").executeUpdate();
            log.info("Deleted {} PurchaseList records", deletedCount);
            return deletedCount > 0; // Return true if at least one record was deleted
        } catch (Exception e) {
            log.error("Error while deleting all PurchaseList records", e);
            throw e; // Re-throw to be handled by the service layer
        }
}
}