package com.pos_main.Transformer;

import org.springframework.stereotype.Component;
import com.pos_main.Domain.PurchaseList;
import com.pos_main.Dto.PurchaseListDto;
import lombok.extern.slf4j.Slf4j;

/**
 * Mar 3, 2025 05:43:22 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 **/

@Slf4j
@Component
public class PurchaseListTransformer implements BaseTransformer<PurchaseList, PurchaseListDto> {

    @Override
    public PurchaseListDto transform(PurchaseList purchaseList) {
        if (purchaseList == null) {
            log.warn("PurchaseList is null in transform");
            return null;
        }
        PurchaseListDto purchaseListDto = new PurchaseListDto();
        purchaseListDto.setId(purchaseList.getId());
        purchaseListDto.setProductName(purchaseList.getProductName());
        purchaseListDto.setBarcode(purchaseList.getBarcode()); // Include barcode
        log.info("Transformed PurchaseList to DTO: id={}, barcode={}", purchaseList.getId(), purchaseList.getBarcode());
        return purchaseListDto; // Return the created DTO
    }

    @Override
    public PurchaseList reverseTransform(PurchaseListDto purchaseListDto) {
        if (purchaseListDto == null) {
            log.warn("PurchaseListDto is null in reverseTransform");
            return null;
        }
        PurchaseList purchaseList = new PurchaseList();
        purchaseList.setId(purchaseListDto.getId());
        purchaseList.setProductName(purchaseListDto.getProductName());
        purchaseList.setBarcode(purchaseListDto.getBarcode()); // Include barcode
        log.info("Transformed DTO to PurchaseList: barcode={}", purchaseListDto.getBarcode());
        return purchaseList; // Return the created entity
    }
}