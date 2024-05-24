package com.altun.techcellhackathon.repository;

import com.altun.techcellhackathon.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CounterRepository extends JpaRepository<Counter,Long> {
    List<Counter> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
