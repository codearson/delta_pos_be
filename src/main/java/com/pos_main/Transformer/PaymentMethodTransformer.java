package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.PaymentMethod;
import com.pos_main.Dto.PaymentMethodDto;

@Component
public class PaymentMethodTransformer implements BaseTransformer<PaymentMethod, PaymentMethodDto>{

	@Override
	public PaymentMethodDto transform(PaymentMethod paymentMethod) {
		PaymentMethodDto paymentMethodDto= null;
		if (paymentMethod != null) {
			paymentMethodDto = new PaymentMethodDto();
			paymentMethodDto.setId(paymentMethod.getId());
			paymentMethodDto.setType(paymentMethod.getType());
			paymentMethodDto.setIsActive(paymentMethod.getIsActive());
		}
		return paymentMethodDto;
	}

	@Override
	public PaymentMethod reverseTransform(PaymentMethodDto paymentMethodDto) {
		PaymentMethod paymentMethod= null;
		if (paymentMethodDto != null) {
			paymentMethod = new PaymentMethod();
			paymentMethod.setId(paymentMethodDto.getId());
			paymentMethod.setType(paymentMethodDto.getType());
			paymentMethod.setIsActive(paymentMethodDto.getIsActive());
		}
		return paymentMethod;
	}
}
