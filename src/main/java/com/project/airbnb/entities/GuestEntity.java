package com.project.airbnb.entities;

import com.project.airbnb.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "guests")
public class GuestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long guestId;

  private String name;

  private Long age;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Gender gender;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  //  @ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "booking_id", nullable = false)
  //  private BookingEntity booking;

}
