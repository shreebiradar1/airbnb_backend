package com.project.airbnb.entities;

import com.project.airbnb.enums.RoomStatus;
import com.project.airbnb.enums.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "rooms",
    uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "roomNumber"}))
public class RoomEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Enumerated(EnumType.STRING)
  private RoomType roomType;

  @Column(nullable = false)
  private BigDecimal basePrice; // As per one night

  @Column(nullable = false)
  private Long totalCount;

  @Enumerated(EnumType.STRING)
  private RoomStatus roomStatus;

  private Long capacity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "hotel_id", nullable = false)
  private HotelEntity hotel;
}
