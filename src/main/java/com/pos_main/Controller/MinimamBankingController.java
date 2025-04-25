package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.MinimamBankingDto;
import com.pos_main.Service.MinimamBankingService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: MinimamBankingController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 24 Apr 2025
 * @time 23:02:31
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("minimamBanking")
public class MinimamBankingController {
    
    @Autowired
    MinimamBankingService minimamBankingService;
    
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingController.save() invoked");
        return minimamBankingService.save(minimamBankingDto);
    }
    
    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody MinimamBankingDto minimamBankingDto) {
        log.info("MinimamBankingController.update() invoked");
        return minimamBankingService.update(minimamBankingDto);
    }
    
    @GetMapping("/getAll")
    public ResponseDto getAll() {
        log.info("MinimamBankingController.getAll() invoked");
        return minimamBankingService.getAll();
    }
}
