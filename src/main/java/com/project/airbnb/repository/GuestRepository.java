package com.project.airbnb.repository;

import com.project.airbnb.entities.GuestEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
  List<GuestEntity> findByUser_Username(String username);
}
