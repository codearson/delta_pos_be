package com.pos_main.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.pos_main.Dao.CountryDao;
import com.pos_main.Domain.Country;
import com.pos_main.Dto.CountryDto;
import com.pos_main.Dto.PaginatedResponseDto;
import com.pos_main.Service.Utils.HttpReqRespUtils;
import com.pos_main.Transformer.CountryTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * Mar 7, 2025 01:52:31 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

@Slf4j
@Repository
public class CountryDaoImpl extends BaseDaoImpl<Country> implements CountryDao {

	@Autowired
	CountryTransformer countryTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public CountryDto save(CountryDto countryDto) {
		log.info("CountryDaoImpl.save() invoked.");
		Country country = countryTransformer.reverseTransform(countryDto);
		Country saveCountry = saveOrUpdate(country);
		return countryTransformer.transform(saveCountry);
	}
	
	@Override
	@Transactional
	public List<CountryDto> getAll() {
		log.info("CountryDaoImpl.getAll() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Country.class, "country");
		criteria.addOrder(Order.asc("id"));
		List<CountryDto> countryDtoList = null;
		List<Country> countryList = (List<Country>) criteria.list();
		if (countryList != null && !countryList.isEmpty()) {
			countryDtoList = new ArrayList<>();
			for (Country country : countryList) {
				countryDtoList.add(countryTransformer.transform(country));
			}
		}
		return countryDtoList;
	}
	
	@Override
	@Transactional
	public PaginatedResponseDto getAllPage(int pageNumber, int pageSize) {
		log.info("CountryDaoImpl.getAllPage() invoked");
		PaginatedResponseDto paginatedResponseDto = null;
		List<Country> allCountryList = null;
		int recordCount = 0;
		String countString = "SELECT COUNT(*) FROM country";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (pageSize == 0) {
			pageSize = count;
		}

		Criteria criteria = getCurrentSession().createCriteria(Country.class, "country");
		
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		allCountryList = criteria.list();
		recordCount = allCountryList.size();
		if (allCountryList != null && !allCountryList.isEmpty()) {
			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(allCountryList, pageNumber, pageSize, count);
			paginatedResponseDto.setPayload(allCountryList.stream().map(country -> {
				return countryTransformer.transform(country);
			}).collect(Collectors.toList()));
		}
		return paginatedResponseDto;
	}
}
