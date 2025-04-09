package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.NonScanProductDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.NonScanProductService;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: NonScanProductController.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Purushorthaman Murugathas Rathakrishnan
 * @date 9 Apr 2025
 * @time 19:23:44
 * @version 1.0
 **/

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("nonScanProduct")
public class NonScanProductController {

    @Autowired
    NonScanProductService nonScanProductService;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll() {
        log.info("NonScanProductController.getAll() invoked");
        return nonScanProductService.getAll();
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPageNonScanProduct(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        log.info("NonScanProductController.getAllPage() invoked");
        return nonScanProductService.getAllPageNonScanProduct(pageNumber, pageSize);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductController.save() invoked");
        return nonScanProductService.save(nonScanProductDto);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody NonScanProductDto nonScanProductDto) {
        log.info("NonScanProductController.update() invoked");
        return nonScanProductService.update(nonScanProductDto);
    }

    @GetMapping("/getByName")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllByName(@RequestParam("nonScanProduct") String nonScanProduct) {
        log.info("NonScanProductController.getAllByName() invoked");
        return nonScanProductService.getAllByName(nonScanProduct);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        log.info("NonScanProductController.updateStatus() invoked");
        return nonScanProductService.updateNonScanProductStatus(id, status);
    }
}