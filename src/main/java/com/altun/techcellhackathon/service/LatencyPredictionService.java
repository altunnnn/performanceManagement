package com.altun.techcellhackathon.service;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class LatencyPredictionService {

    @GetMapping("/predict-latency")
    public double predictLatency(@RequestParam("feature1") double feature1,
                                 @RequestParam("feature2") double feature2) {
        try {

            // Load the saved machine learning model
            File modelFile = ResourceUtils.getFile("classpath:latency_prediction_model.pkl");
            InputStream inputStream = new FileInputStream(modelFile);
            byte[] modelBytes = IOUtils.toByteArray(inputStream);

            double latency = PythonMLWrapper.predictLatency(modelBytes, feature1, feature2);

            return latency;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return a default value or handle the error accordingly
        }
    }
}
