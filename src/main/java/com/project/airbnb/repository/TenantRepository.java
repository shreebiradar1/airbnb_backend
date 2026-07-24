package com.project.airbnb.repository;

import com.project.airbnb.entities.TenantEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {
  Optional<TenantEntity> findByEmail(String email);

  boolean existsByEmail(String email);
}
