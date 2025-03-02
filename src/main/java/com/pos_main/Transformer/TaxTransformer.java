package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.Tax;
import com.pos_main.Dto.TaxDto;


@Component
public class TaxTransformer implements BaseTransformer<Tax, TaxDto>{

	@Override
	public TaxDto transform(Tax tax) {
		TaxDto taxDto= null;
		if (tax != null) {
			taxDto = new TaxDto();
			taxDto.setId(tax.getId());
			taxDto.setTaxPersentage(tax.getTaxPersentage());
			taxDto.setIsActive(tax.getIsActive());
		}
		return taxDto;
	}

	@Override
	public Tax reverseTransform(TaxDto taxDto) {
		Tax tax= null;
		if (taxDto != null) {
			tax = new Tax();
			tax.setId(taxDto.getId());
			tax.setTaxPersentage(taxDto.getTaxPersentage());
			tax.setIsActive(taxDto.getIsActive());
		}
		return tax;
	}
}
