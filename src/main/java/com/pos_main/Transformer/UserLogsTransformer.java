package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.UserLogs;
import com.pos_main.Dto.UserLogsDto;

@Component
public class UserLogsTransformer implements BaseTransformer<UserLogs, UserLogsDto> {

	@Autowired
	UserTransfomer userTransfomer;
	
	@Override
	public UserLogsDto transform(UserLogs userLogs) {
		UserLogsDto userLogsDto = null;
		if (userLogs != null) {
			userLogsDto = new UserLogsDto();
			userLogsDto.setId(userLogs.getId());
			if (userLogs.getUser() != null) {
				userLogsDto.setUserDto(userTransfomer.transform(userLogs.getUser()));
			}
			userLogsDto.setLogIn(userLogs.getLogIn());
			userLogsDto.setLogOut(userLogs.getLogOut());
			userLogsDto.setSignOff(userLogs.getSignOff());
			userLogsDto.setDescription(userLogs.getDescription());
		}
		return userLogsDto;
	}
	
	@Override
	public UserLogs reverseTransform(UserLogsDto userLogsDto) {
		UserLogs userLogs = null;
		if (userLogsDto != null) {
			userLogs = new UserLogs();
			userLogs.setId(userLogsDto.getId());
			if (userLogsDto.getUserDto() != null) {
				userLogs.setUser(
						userTransfomer.reverseTransform(userLogsDto.getUserDto()));
			}
			userLogs.setLogIn(userLogsDto.getLogIn());
			userLogs.setLogOut(userLogsDto.getLogOut());
			userLogs.setSignOff(userLogsDto.getSignOff());
			userLogs.setDescription(userLogsDto.getDescription());

		}
		return userLogs;
	}
}



