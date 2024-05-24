package com.altun.techcellhackathon.service;

import com.altun.techcellhackathon.entity.Counter;
import com.altun.techcellhackathon.entity.NetworkPerformance;
import com.altun.techcellhackathon.repository.CounterRepository;
import com.altun.techcellhackathon.repository.NetworkPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/*
The KpiService class plays a pivotal role in the telecommunications network's performance management system. It's responsible for computing key performance indicators (KPIs) essential for assessing network efficiency and quality.
This service retrieves vendor-specific performance data, enabling detailed analysis and comparison across vendors. It calculates crucial KPIs like accessibility, retainability, mobility, availability, and integrity.
Accessibility KPI measures the ratio of successful connection attempts to total attempts, indicating user connectivity ease. Retainability KPI assesses the ratio of maintained connections to total established ones, crucial for dropout analysis. Mobility KPI reflects successful handovers during user transitions, essential for network mobility assessment.
Availability KPI measures operational time against total possible time, factoring in downtimes. Integrity KPI evaluates data transmission quality by assessing corrupted packets and packet loss rates.
These KPIs offer insights for monitoring, diagnosing, and enhancing telecommunications network performance, ensuring optimal user experience and network reliability.

 */

@Service
@RequiredArgsConstructor
public class KpiService {

    private final NetworkPerformanceRepository networkPerformanceRepository;
    private final CounterRepository counterRepository;

    public List<NetworkPerformance> getPerformanceDataByVendor(String vendorName) {
        return networkPerformanceRepository.findByVendorName(vendorName);
    }

    public List<Counter> getLatencyDataForLastMonth() {
        LocalDate currentDate = LocalDate.now();

        LocalDate lastMonthStartDate = currentDate.minus(Period.ofMonths(1)).withDayOfMonth(1);

        LocalDate lastMonthEndDate = currentDate.minus(Period.ofMonths(1)).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        return counterRepository.findByDateBetween(lastMonthStartDate, lastMonthEndDate);
    }

    public double calculateAccessibility() {
        List<Counter> performances = counterRepository.findAll();
        double totalAccessibility = 0;
        for (Counter performance : performances) {
            totalAccessibility += (double) performance.getSuccessfulConnectionAttempts() / performance.getTotalConnectionAttempts();
        }
        return totalAccessibility / performances.size();
    }

    public double calculateRetainability() {
        List<Counter> performances = counterRepository.findAll();
        double totalRetainability = 0;
        for (Counter performance : performances) {
            totalRetainability += (double) (performance.getTotalEstablishedConnections() - performance.getDroppedConnections()) / performance.getTotalEstablishedConnections();
        }
        return totalRetainability / performances.size();
    }

    public double calculateMobility() {
        List<Counter> performances = counterRepository.findAll();
        double totalMobility = 0;
        for (Counter performance : performances) {
            totalMobility += (double) performance.getSuccessfulHandovers() / performance.getHandoverAttempts();
        }
        return totalMobility / performances.size();
    }

    public double calculateAvailability() {
        List<Counter> performances = counterRepository.findAll();
        double totalAvailability = 0;
        for (Counter performance : performances) {
            totalAvailability += (double) (performance.getTotalOperationalTime() - performance.getDowntime()) / performance.getTotalOperationalTime();
        }
        return totalAvailability / performances.size();
    }

    public double calculateIntegrity() {
        List<Counter> performances = counterRepository.findAll();
        double totalIntegrity = 0;
        for (Counter performance : performances) {
            double corruptionRate = (double) performance.getCorruptedPackets() / performance.getTotalTransmittedPackets();
            totalIntegrity += 1 - (corruptionRate + performance.getPacketLossRate());
        }
        return totalIntegrity / performances.size();
    }
}