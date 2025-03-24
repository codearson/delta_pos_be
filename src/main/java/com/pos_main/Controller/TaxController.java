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

import com.pos_main.Dto.ResponseDto;
import com.pos_main.Dto.TaxDto;
import com.pos_main.Service.TaxService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("tax")
public class TaxController {
	
	@Autowired
	TaxService taxService;

	@PostMapping("/save")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	public ResponseDto save(@RequestBody TaxDto taxDto) {
		log.info("TaxController.save() invoked");
		return taxService.save(taxDto);
	}
	
	@PutMapping("/update")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody TaxDto taxDto) {
        log.info("TaxController.update() invoked");
        return taxService.update(taxDto);
    }

    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateTaxStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        log.info("TaxController.updateTaxStatus() invoked");
        return taxService.updateTaxStatus(id, status);
    }
	
	@GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getTaxByPercentage(@RequestParam("taxPercentage") Integer taxPercentage) {
        log.info("TaxController.getTaxByPercentage() invoked");
        return taxService.getTaxByName(taxPercentage);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAll() {
        log.info("TaxController.getAll() invoked");
        return taxService.getAll();
    }
}
