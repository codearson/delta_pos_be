package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.StockDto;
import com.pos_main.Service.StockService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: StockController.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 26 Jan 2025
 * @time 22:34:38
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("stock")
public class StockController {

	@Autowired
	StockService stockService;

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto save(@RequestBody StockDto stockDto) {
		log.info("StockController.save() invoked");
		return stockService.save(stockDto);
	}

	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getAllStock() {
		log.info("StockController.gellAllStock() invoked");
		return stockService.getAllStock();
	}

	@PostMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateStock(@RequestBody StockDto stockDto) {
		log.info("StockController.updateStock() invoked");
		return stockService.updateStock(stockDto);
	}

	@PutMapping("/updateStatus")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto updateStockStatus(@RequestParam("stockId") Integer stockId,
			@RequestParam("status") Boolean status) {
		log.info("StockController.updateStockStatus() invoked.");
		return stockService.updateStockStatus(stockId, status);
	}
	
	@GetMapping("/getById")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getStockById(@RequestParam("id") Integer id) {
	    log.info("StockController.getStockById() invoked with id", id);
	    return stockService.getStockById(id);
	}
	
	@GetMapping("/getByProductCategoryId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getStockByProductCategoryId(@RequestParam("productCategoryId") Integer productCategoryId) {
	    log.info("StockController.getByProductCategoryId() invoked with productCategoryId: {}", productCategoryId);
	    return stockService.getStockByProductCategoryId(productCategoryId);
	}
	
	@GetMapping("/getByProductId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseDto getStockByProductId(@RequestParam("productId") Integer productId) {
	    log.info("StockController.getByProductId() invoked with productId: {}", productId);
	    return stockService.getStockByProductId(productId);
	}
	
	@GetMapping("/getByQuantityRange")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto getStockByQuantityRange(@RequestParam("minQuantity") Integer minQuantity, @RequestParam("maxQuantity") Integer maxQuantity) {
        log.info("StockController.getStockByQuantityRange() invoked" );
        return stockService.getStockByQuantityRange(minQuantity,maxQuantity);
    }
}
