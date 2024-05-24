package com.altun.techcellhackathon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "counter")
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "network_performance_id")
    private NetworkPerformance networkPerformance;

    private int totalConnectionAttempts;
    private int successfulConnectionAttempts;
    private int totalEstablishedConnections;
    private int droppedConnections;
    private int handoverAttempts;
    private int successfulHandovers;
    private int droppedCallsDuringHandovers;
    private int totalOperationalTime;
    private int downtime;
    private int totalTransmittedPackets;
    private int corruptedPackets;
    private double packetLossRate;

    private String name;
    private String description;
    private String unit;
    private String frequency;
    private String linkID;

    private int speedMbps;
    private int bandwidthMHz;
    private double frequencyGHz;
    private int wavelengthnm;
    private double latencyms;
    private double jitterms;
    private double packetLossPercent;
    private double snrDb;
    private String modulationScheme;
    private double errorRatePercent;
    private double propagationDelayms;
    private double ber;
}
