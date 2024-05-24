package com.altun.techcellhackathon.repository;

import com.altun.techcellhackathon.entity.NetworkPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkPerformanceRepository extends JpaRepository<NetworkPerformance, Long> {
    List<NetworkPerformance> findByVendorName(String vendorName);
}
