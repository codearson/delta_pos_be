package com.pos_main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.pos_main.Service.PythonPredictionService;

import org.json.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * Title: AIController.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Mar 30, 2025.
 * @version 1.0
 **/

@RestController
@RequestMapping("/aiSales")
public class AISalesController {

    @Autowired
    private PythonPredictionService pythonPredictionService;

    @PostMapping("/predict")
    public ResponseEntity<String> getPrediction(@RequestBody String inputData) {
        String prediction = pythonPredictionService.predictSales(inputData);
        return ResponseEntity.ok(prediction);
    }
}



