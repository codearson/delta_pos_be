package com.pos_main.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos_main.Domain.Branch;
import com.pos_main.Dto.BranchDto;

/**
 * Title: BranchTransformer.java. Company: www.codearson.com Copyright: Copyright
 * (c) 2018.
 * 
 * @author Lathusan Thurairajah
 * @date Dec 17, 2021.
 * @version 1.0
 **/

@Component
public class BranchTransformer implements BaseTransformer<Branch, BranchDto> {
	
	@Autowired
	CountryTransformer countryTransformer;
	
	@Autowired
	ShopDetailsTransformer shopDetailsTransformer;
	
	@Override
	public BranchDto transform(Branch branch) {
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = new BranchDto();
			branchDto.setId(branch.getId());
			branchDto.setBranchName(branch.getBranchName());
			branchDto.setAddress(branch.getAddress());
			branchDto.setContactNumber(branch.getContactNumber());
			branchDto.setEmailAddress(branch.getEmailAddress());
			branchDto.setBranchCode(branch.getBranchCode());
			branchDto.setIsActive(branch.getIsActive());
			if (branch.getCountry() != null) {
				branchDto.setCountryDto(countryTransformer.transform(branch.getCountry()));
			}
			if (branch.getShopDetails() != null) {
				branchDto.setShopDetailsDto(shopDetailsTransformer.transform(branch.getShopDetails()));
			}
		}
		return branchDto;
	}

	@Override
	public Branch reverseTransform(BranchDto branchDto) {
		Branch branch = null;
		if (branchDto != null) {
			branch = new Branch();
			branch.setId(branchDto.getId());
			branch.setBranchName(branchDto.getBranchName());
			branch.setAddress(branchDto.getAddress());
			branch.setContactNumber(branchDto.getContactNumber());
			branch.setEmailAddress(branchDto.getEmailAddress());
			branch.setBranchCode(branchDto.getBranchCode());
			branch.setIsActive(branchDto.getIsActive());
			if (branchDto.getCountryDto() != null) {
				branch.setCountry(
						countryTransformer.reverseTransform(branchDto.getCountryDto()));
			}
			if (branchDto.getShopDetailsDto() != null) {
				branch.setShopDetails(
						shopDetailsTransformer.reverseTransform(branchDto.getShopDetailsDto()));
			}
		}
		return branch;
	}

}
