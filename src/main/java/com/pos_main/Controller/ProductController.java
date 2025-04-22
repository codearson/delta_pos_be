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
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.ProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.ProductService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Feb 17, 2024 
 * 10:32:16 PM
 * @author Lathusan Thurairajah
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody ProductDto productDto) {
		log.info("ProductController.save() invoked");
		return productService.save(productDto);
	}
	
	@GetMapping("/getAllPage")
	public ResponseDto getAll(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,
			@RequestParam("status") Boolean status) {
		log.info("ProductController.getAll() invoked.");
		return productService.getAll(pageNumber, pageSize, Map.of(), status);
	}
	
	@GetMapping("/getAll")
	public ResponseDto getAllProducts() {
		log.info("ProductController.gellAllProducts() invoked");
		return productService.getAllProducts();
	}
	
	@GetMapping("/getByBarcode")
	public ResponseDto getProductByBarcode(@RequestParam("barcode") String barcode) {
	    log.info("ProductController.getProductByBarcode() invoked with barcode", barcode);
	    return productService.getProductByBarcode(barcode);
	}
	
	@GetMapping("/getByName")
    public ResponseDto getProductByName(@RequestParam("name") String name) {
        log.info("ProductCategoryController.getAllProductCategoryByName() invoked" );
        return productService.getProductByName(name);
    }
	
	@PostMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProduct(@RequestBody ProductDto productDto) {
		log.info("ProductController.updateProduct() invoked");
		return productService.updateProduct(productDto);
	}
	
	@PutMapping("/updateStatus")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto updateProductStatus(@RequestParam("productId") Integer productId, @RequestParam("status") Boolean status) {
		log.info("ProductController.updateProductStatus() invoked.");
		return productService.updateProductStatus(productId, status);
	}
	
	@GetMapping("/getById")
	public ResponseDto getProductById(@RequestParam("id") Integer id) {
	log.info("ProductController.getProductById() invoked with id", id);
    return productService.getProductById(id);
	}

}
