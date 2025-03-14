package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Product;
import com.pos_main.Dto.ProductDto;

/**
 * Feb 13, 2024 10:53:20 PM
 * 
 * @author Lathusan Thurairajah
 **/

@Component
public class ProductTransformer implements BaseTransformer<Product, ProductDto> {

	@Autowired
	ProductCategoryTransfomer productCategoryTransfomer;
	
	@Autowired
	TaxTransformer taxTransformer;

	@Override
	public ProductDto transform(Product product) {
		ProductDto productDto = null;
		if (product != null) {
			productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setBarcode(product.getBarcode());
			productDto.setPricePerUnit(product.getPricePerUnit());
			if (product.getTax() != null) {
				productDto.setTaxDto(taxTransformer.transform(product.getTax()));
			}
			productDto.setCreatedDate(product.getCreatedDate());
			productDto.setIsActive(product.getIsActive());
			if (product.getProductCategory() != null) {
				productDto.setProductCategoryDto(productCategoryTransfomer.transform(product.getProductCategory()));
			}
			productDto.setQuantity(product.getQuantity());
			productDto.setLowStock(product.getLowStock());
			productDto.setPurchasePrice(product.getPurchasePrice());
		}
		return productDto;
	}

	@Override
	public Product reverseTransform(ProductDto productDto) {
		Product product = null;
		if (productDto != null) {
			product = new Product();
			product.setId(productDto.getId());
			product.setName(productDto.getName());
			product.setBarcode(productDto.getBarcode());
			product.setPricePerUnit(productDto.getPricePerUnit());
			if (productDto.getTaxDto() != null) {
				product.setTax(
						taxTransformer.reverseTransform(productDto.getTaxDto()));
			}
			product.setCreatedDate(productDto.getCreatedDate());
			product.setIsActive(productDto.getIsActive());
			if (productDto.getProductCategoryDto() != null) {
				product.setProductCategory(
						productCategoryTransfomer.reverseTransform(productDto.getProductCategoryDto()));
			}
			product.setQuantity(productDto.getQuantity());
			product.setLowStock(productDto.getLowStock());
			product.setPurchasePrice(productDto.getPurchasePrice());
		}
		return product;
	}

}
