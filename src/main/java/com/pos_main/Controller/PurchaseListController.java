package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PurchaseListService;
import lombok.extern.slf4j.Slf4j;

/**
 * Mar 3, 2025 06:00:00 PM
 * 
 * @author Niven
 **/

@Slf4j
@RestController
@RequestMapping("/purchaseList")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto savePurchaseList(
            @RequestParam("barcode") String barcode) {
        log.info("PurchaseListController.savePurchaseList() invoked with barcode: {}", barcode);

        PurchaseListDto purchaseListDto = new PurchaseListDto();
        purchaseListDto.setBarcode(barcode);

        return purchaseListService.savePurchaseList(purchaseListDto);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAll() {
		log.info("PurchaseListController.getAll() invoked");
		return purchaseListService.getAll();
	}
    
    @DeleteMapping("/deleteAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto deleteAll() {
        log.info("PurchaseListController.deleteAll() invoked");
        return purchaseListService.deleteAll();
    }
}
