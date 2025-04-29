package com.pos_main.Transformer;

import org.springframework.stereotype.Component;

import com.pos_main.Domain.DeviceAuth;
import com.pos_main.Dto.DeviceAuthDto;

/**
 * Title: DeviceAuthTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 29 Apr 2025
 * @time 19:36:19
 * @version 1.0
 **/

@Component
public class DeviceAuthTransformer implements BaseTransformer<DeviceAuth, DeviceAuthDto>{
	
	@Override
	public DeviceAuthDto transform(DeviceAuth deviceAuth) {
		DeviceAuthDto deviceAuthDto = null;
		if (deviceAuth != null) {
			deviceAuthDto = new DeviceAuthDto();
			deviceAuthDto.setId(deviceAuth.getId());
			deviceAuthDto.setTillName(deviceAuth.getTillName());
			deviceAuthDto.setTillId(deviceAuth.getTillId());
			deviceAuthDto.setApproveStatus(deviceAuth.getApproveStatus());
			deviceAuthDto.setLoginStatus(deviceAuth.getLoginStatus());
			deviceAuthDto.setIsActive(deviceAuth.getIsActive());
		}
		return deviceAuthDto;
	}

	@Override
	public DeviceAuth reverseTransform(DeviceAuthDto deviceAuthDto) {
		DeviceAuth deviceAuth = null;
		if (deviceAuthDto != null) {
			deviceAuth = new DeviceAuth();
			deviceAuth.setId(deviceAuthDto.getId());
			deviceAuth.setTillName(deviceAuthDto.getTillName());
			deviceAuth.setTillId(deviceAuthDto.getTillId());
			deviceAuth.setLoginStatus(deviceAuthDto.getLoginStatus());
			deviceAuth.setApproveStatus(deviceAuthDto.getApproveStatus());
			deviceAuth.setIsActive(deviceAuthDto.getIsActive());
		}
		return deviceAuth;
	}

}
