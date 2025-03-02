package com.pos_main.Service;

import com.pos_main.Dto.PaymentMethodDto;
import com.pos_main.Dto.ResponseDto;

public interface PaymentMethodService {
	
	public ResponseDto save(PaymentMethodDto paymentMethodDto);
}
