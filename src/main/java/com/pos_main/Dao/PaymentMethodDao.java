package com.pos_main.Dao;

import com.pos_main.Domain.PaymentMethod;
import com.pos_main.Dto.PaymentMethodDto;

public interface PaymentMethodDao extends BaseDao<PaymentMethod>{

	PaymentMethodDto save (PaymentMethodDto paymentMethodDto);
}
