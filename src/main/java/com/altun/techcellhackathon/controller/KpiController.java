package com.altun.techcellhackathon.controller;

import com.altun.techcellhackathon.entity.NetworkPerformance;
import com.altun.techcellhackathon.service.KpiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kpis")
@RequiredArgsConstructor
public class KpiController {

    private final KpiService kpiService;

    @GetMapping("/performance/vendor")
    public List<NetworkPerformance> getPerformanceDataByVendor(@RequestParam String vendorName) {
        return kpiService.getPerformanceDataByVendor(vendorName);
    }

    @GetMapping("/accessibility")
    public double calculateAccessibility() {
        return kpiService.calculateAccessibility();
    }

    @GetMapping("/retainability")
    public double calculateRetainability() {
        return kpiService.calculateRetainability();
    }

    @GetMapping("/mobility")
    public double calculateMobility() {
        return kpiService.calculateMobility();
    }

    @GetMapping("/availability")
    public double calculateAvailability() {
        return kpiService.calculateAvailability();
    }

    @GetMapping("/integrity")
    public double calculateIntegrity() {
        return kpiService.calculateIntegrity();
    }
}
