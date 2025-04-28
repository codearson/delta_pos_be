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
import org.springframework.web.context.request.WebRequest;

import com.pos_main.Dto.PayoutCategoryDto;
import com.pos_main.Dto.ResponseDto;
import com.pos_main.Service.PayoutCategoryService;
import com.pos_main.Service.Utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("payoutCategory")
public class PayoutCategoryController {
    
    @Autowired
    PayoutCategoryService payoutCategoryService;
    
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto save(@RequestBody PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryController.save() invoked");
        return payoutCategoryService.save(payoutCategoryDto);
    }
    
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto update(@RequestBody PayoutCategoryDto payoutCategoryDto) {
        log.info("PayoutCategoryController.update() invoked");
        return payoutCategoryService.updatePayoutCategory(payoutCategoryDto);
    }
    
    @PutMapping("/updateStatus")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Boolean status) {
        log.info("PayoutCategoryController.updateStatus() invoked with id: {}, status: {}", id, status);
        return payoutCategoryService.updatePayoutCategoryStatus(id, status);
    }
    
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPayoutCategory() {
        log.info("PayoutCategoryController.getAllPayoutCategory() invoked");
        return payoutCategoryService.getAllPayoutCategory();
    }
    
    @GetMapping("/getAllPage")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllPagePayoutCategory(@RequestParam("pageNumber") int pageNumber, 
            @RequestParam("pageSize") int pageSize,
            @RequestParam("status") Boolean status,
            WebRequest webRequest) {
        log.info("PayoutCategoryController.getAllPagePayoutCategory() invoked");
        return payoutCategoryService.getAllPagePayoutCategory(pageNumber, pageSize, status, 
                HttpReqRespUtils.getSearchParameters(webRequest));
    }
    
    @GetMapping("/getByName")
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseDto getAllByName(@RequestParam("payoutCategory") String payoutCategory) {
        log.info("PayoutCategoryController.getAllPayoutCategoryByName() invoked" );
        return payoutCategoryService.getAllByName(payoutCategory);
}
}