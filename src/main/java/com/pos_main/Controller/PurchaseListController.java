package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.PurchaseListDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PurchaseListService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Mar 3, 2025 06:00:00 PM
 * 
 * @author Niven
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/purchaseList")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;

    @PostMapping("/save")
    public ResponseDto savePurchaseList(
            @RequestParam("barcode") String barcode) {
        log.info("PurchaseListController.savePurchaseList() invoked with barcode: {}", barcode);

        PurchaseListDto purchaseListDto = new PurchaseListDto();
        purchaseListDto.setBarcode(barcode);

        return purchaseListService.savePurchaseList(purchaseListDto);
    }
    
    @GetMapping("/getAll")
	public ResponseDto getAll() {
		log.info("PurchaseListController.getAll() invoked");
		return purchaseListService.getAll();
	}
    
	@GetMapping("/getAllPage")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto getAllPagePurchaseList(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			WebRequest webRequest) {
		log.info("PurchaseListController.getAllPage() invoked.");
		return purchaseListService.getAllPagePurchaseList(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
	}
    
    @DeleteMapping("/deleteAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto deleteAll() {
        log.info("PurchaseListController.deleteAll() invoked");
        return purchaseListService.deleteAll();
    }
    
    @DeleteMapping("/deleteById")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto deleteById(@RequestParam("id") Integer id) {
        log.info("PurchaseListController.deleteById() invoked with id: {}", id);
        return purchaseListService.deleteById(id);
    }
}
