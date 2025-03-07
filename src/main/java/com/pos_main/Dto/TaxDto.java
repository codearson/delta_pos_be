package com.pos_main.Dto;

import lombok.Data;

@Data
public class TaxDto {
    
    private Integer id;
    private Double taxPercentage; // Changed from Integer to Double
    private Boolean isActive;
    
}
