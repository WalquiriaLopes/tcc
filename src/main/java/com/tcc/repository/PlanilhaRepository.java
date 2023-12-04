package com.tcc.repository;

import com.tcc.entities.PlanilhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanilhaRepository extends JpaRepository<PlanilhaEntity, Long> {
}
