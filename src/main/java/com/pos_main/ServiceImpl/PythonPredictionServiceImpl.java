package com.pos_main.ServiceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.pos_main.Service.PythonPredictionService;

/**
 * Title: PythonPredictionServiceImpl.java. Company: www.codearson.com | Copyright: Copyright (C) 2025.
 * @author Lathusan Thurairajah.
 * @date Mar 30, 2025.
 * @version 1.0
 **/

@Service
public class PythonPredictionServiceImpl implements PythonPredictionService{
	
	// Python script location
    private static final String PYTHON_SCRIPT_PATH = "com/pos_main/python/predict_sales.py";

    public String predictSales(String inputData) {
        try {
            // Prepare the command to run the Python script
            ProcessBuilder processBuilder = new ProcessBuilder("python", PYTHON_SCRIPT_PATH, inputData);
            processBuilder.redirectErrorStream(true);

            // Run the Python script and capture the output
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // Wait for the process to finish and get the result
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return result.toString();  // Return the Python script result (predictions)
            } else {
                throw new RuntimeException("Python script failed to execute properly");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error executing Python script", e);
        }
    }

}


