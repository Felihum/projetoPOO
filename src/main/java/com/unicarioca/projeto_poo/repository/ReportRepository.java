package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.reports.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {
}
