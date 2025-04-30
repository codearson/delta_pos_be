package com.pos_main.Transformer;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.ManagerToggle;
import com.pos_main.Dto.ManagerToggleDto;;

/**
 * Title: ManagerToggleTransformer.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 4 Apr 2025
 * @time 19:39:24
 * @version 1.0
 **/

@Component
public class ManagerToggleTransformer implements BaseTransformer<ManagerToggle, ManagerToggleDto>{

	@Override
	public ManagerToggleDto transform(ManagerToggle managerToggle) {
		ManagerToggleDto managerToggleDto = null;
		if (managerToggle != null) {
			managerToggleDto = new ManagerToggleDto();
			managerToggleDto.setId(managerToggle.getId());
			managerToggleDto.setAction(managerToggle.getAction());
			managerToggleDto.setIsActive(managerToggle.getIsActive());
			managerToggleDto.setAdminActive(managerToggle.getAdminActive());
		}
		return managerToggleDto;

	}

	
	
	@Override
	public ManagerToggle reverseTransform(ManagerToggleDto managerToggleDto) {
		ManagerToggle managerToggle = null;
		if (managerToggleDto != null) {
			managerToggle = new ManagerToggle();
			managerToggle.setId(managerToggleDto.getId());
			managerToggle.setAction(managerToggleDto.getAction());
			managerToggle.setIsActive(managerToggleDto.getIsActive());
			managerToggle.setAdminActive(managerToggleDto.getAdminActive());
		}
		return managerToggle;
		
	}
}
