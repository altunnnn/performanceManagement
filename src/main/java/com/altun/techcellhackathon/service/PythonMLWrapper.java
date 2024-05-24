package com.altun.techcellhackathon.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class PythonMLWrapper  {
    public static double predictLatency(byte[] modelBytes, double feature1, double feature2, ...) {
        try {
            // Call the Python script using subprocess
            Process process = Runtime.getRuntime().exec("python3 predict_latency.py");

            // Pass model bytes and input features to the Python script
            process.getOutputStream().write(modelBytes);
            process.getOutputStream().write(String.format("%f %f ...", feature1, feature2).getBytes());

            // Read the predicted latency value from the Python script's output
            InputStream inputStream = process.getInputStream();
            String output = new String(inputStream.readAllBytes());
            double latency = Double.parseDouble(output.trim());

            return latency;
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return a default value or handle the error accordingly
        }
    }
}
