package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pos_main.Domain.Shifts;
import com.pos_main.Dto.ShiftsDto;

@Component
public class ShiftsTransformer implements BaseTransformer<Shifts, ShiftsDto>{

	
	@Autowired
	UserTransfomer userTransformer;
	
	@Override
	public ShiftsDto transform(Shifts shifts) {
		ShiftsDto shiftsDto = null;
		if (shifts != null) {
			shiftsDto = new ShiftsDto();
			shiftsDto.setId(shifts.getId());
			shiftsDto.setDate(shifts.getDate());
			shiftsDto.setStartTime(shifts.getStartTime());
			shiftsDto.setEndTime(shifts.getEndTime());
			if (shifts.getUser() != null) {
				shiftsDto.setUserDto(userTransformer.transform(shifts.getUser()));
			}
			if (shifts.getManager() != null) {
				shiftsDto.setManagerDto(userTransformer.transform(shifts.getManager()));
			}
			shiftsDto.setStatus(shifts.getStatus());
			shiftsDto.setIsActive(shifts.getIsActive());
		}
		return shiftsDto;
	}
	
	@Override
	public Shifts reverseTransform(ShiftsDto shiftsDto) {
		Shifts shifts = null;
		if (shiftsDto != null) {
			shifts = new Shifts();
			shifts.setId(shiftsDto.getId());
			shifts.setDate(shiftsDto.getDate());
			shifts.setStartTime(shiftsDto.getStartTime());
			shifts.setEndTime(shiftsDto.getEndTime());
			if (shiftsDto.getUserDto() != null) {
				shifts.setUser(
						userTransformer.reverseTransform(shiftsDto.getUserDto()));
			}
			if (shiftsDto.getManagerDto() != null) {
				shifts.setManager(userTransformer.reverseTransform(shiftsDto.getManagerDto()));
			}
			shifts.setStatus(shiftsDto.getStatus());
			shifts.setIsActive(shiftsDto.getIsActive());
		}
		return shifts;
	}
	
}
