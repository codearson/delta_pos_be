package com.pos_main.Controller;

/**
 * Title: SalesReportController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 19 Mar 2025
 * @time 21:38:51
 * @version 1.0
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.SalesReportDto;
import com.pos_main.Service.SalesReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("salesReport")
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseDto save(@RequestBody SalesReportDto salesReportDto) {
        log.info("SalesReportController.save() invoked");
        return salesReportService.save(salesReportDto);
    }
}
