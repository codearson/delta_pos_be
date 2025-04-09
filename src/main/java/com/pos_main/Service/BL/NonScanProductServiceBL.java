package com.pos_main.Service.BL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos_main.Dao.NonScanProductDao;
import com.pos_main.Dto.NonScanProductDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: NonScanProductServiceBL.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:24:30
 * @version 1.0
 **/

@Slf4j
@Service
public class NonScanProductServiceBL {

    @Autowired
    NonScanProductDao nonScanProductDao;

    public List<NonScanProductDto> getAll() {
        log.info("NonScanProductServiceBL.getAll() invoked");
        return nonScanProductDao.getAll();
    }

    public List<NonScanProductDto> getAllPageNonScanProduct(int pageNumber, int pageSize) {
        log.info("NonScanProductServiceBL.getAllPageNonScanProduct() invoked");
        return nonScanProductDao.getAllPageNonScanProduct(pageNumber, pageSize);
    }

    public NonScanProductDto save(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductServiceBL.save() invoked");
        return nonScanProductDao.save(nonScanProductDto);
    }

    public NonScanProductDto update(NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductServiceBL.update() invoked");
        return nonScanProductDao.update(nonScanProductDto);
    }

    public List<NonScanProductDto> getAllByName(String nonScanProduct) {
        log.info("NonScanProductServiceBL.getAllByName() invoked");
        return nonScanProductDao.getAllByName(nonScanProduct);
    }

    public NonScanProductDto updateNonScanProductStatus(Integer id, Boolean status) {
        log.info("NonScanProductServiceBL.updateNonScanProductStatus() invoked");
        NonScanProductDto nonScanProductDto = nonScanProductDao.checkAvailability(id);
        if (nonScanProductDto != null) {
            nonScanProductDto.setIsActive(status);
            return nonScanProductDao.update(nonScanProductDto);
        }
        return null;
    }
}