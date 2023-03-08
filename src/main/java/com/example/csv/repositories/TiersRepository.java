package com.example.csv.repositories;

import com.example.csv.domain.Tiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public interface TiersRepository extends JpaRepository<Tiers,Long>, JpaSpecificationExecutor<Tiers> {
    Page<Tiers> findAll(Pageable pageable);
}
