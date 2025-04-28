package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos_main.Dto.CountryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.CountryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Mar 7, 2025 01:00:50 PM
 * 
 * @author Nivethanan Sivagnanasuntharam
 */

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ResponseDto> save(@RequestBody CountryDto countryDto) {
        log.info("CountryController.save() invoked with country: {}", countryDto.getCountryName());
        ResponseDto response = countryService.save(countryDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ResponseDto> getAll() {
        log.info("CountryController.getAll() invoked");
        ResponseDto response = countryService.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ResponseDto> getAllPage(
            @RequestParam("pageNumber") int pageNumber, 
            @RequestParam("pageSize") int pageSize) {
        log.info("CountryController.getAllPage() invoked");
        ResponseDto response = countryService.getAllPage(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }
}