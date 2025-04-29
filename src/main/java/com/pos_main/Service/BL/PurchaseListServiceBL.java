package com.pos_main.Service.BL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.PurchaseListDao;
import com.pos_main.Domain.PurchaseList;
import com.pos_main.Dao.ProductDao;
import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Transformer.PurchaseListTransformer;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Dto.ProductDto;

import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PurchaseListServiceBL {

    @Autowired
    private PurchaseListDao purchaseListDao;

    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private PurchaseListTransformer purchaseListTransformer;

    public PurchaseListDto savePurchaseList(PurchaseListDto purchaseListDto) {
        log.info("PurchaseListServiceBL.savePurchaseList() invoked with barcode: {}", purchaseListDto.getBarcode());

        if (purchaseListDto == null || purchaseListDto.getBarcode() == null) {
            throw new IllegalArgumentException("purchaseListDto or barcode cannot be null");
        }

        List<ProductDto> productList = productDao.getProductByBarcode(purchaseListDto.getBarcode());

        if (productList == null || productList.isEmpty()) {
            throw new RuntimeException("Product not found for barcode: " + purchaseListDto.getBarcode());
        }

        ProductDto product = productList.get(0);
        purchaseListDto.setProductName(product.getName());

        PurchaseList purchaseList = purchaseListTransformer.reverseTransform(purchaseListDto);
        if (purchaseList == null) {
            log.error("Transformed PurchaseList is null for barcode: {}", purchaseListDto.getBarcode());
            throw new IllegalStateException("Failed to transform PurchaseListDto to PurchaseList");
        }

        PurchaseList savedEntity = purchaseListDao.savePurchaseList(purchaseList);
        return purchaseListTransformer.transform(savedEntity);
    }
    
    public List<PurchaseListDto> getAll() {
		log.info("PurchaseListServiceBL.getAll() invoked");
		return purchaseListDao.getAll();
	}
    
	public PaginatedResponseDto getAllPagePurchaseList(int pageNumber, int pageSize, Map<String, String> searchParams) {
		log.info("PurchaseListServiceBL.getAllPagePurchaseList()invoked");
		return purchaseListDao.getAllPagePurchaseList(pageNumber, pageSize, searchParams);
	}
    
    public boolean deleteAll() {
        log.info("PurchaseListServiceBL.deleteAll() invoked");
        return purchaseListDao.deleteAll();
    }

    public boolean deleteById(Integer id) {
        log.info("PurchaseListServiceBL.deleteById() invoked with id: {}", id);
        return purchaseListDao.deleteById(id);
    }
}
