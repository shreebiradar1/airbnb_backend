package com.project.airbnb.entities.helper;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HotelDetails {

  @Column(nullable = false)
  @NotBlank
  private String city;

  @Column(nullable = false)
  @NotBlank
  private String state;

  @Column(name = "address")
  private String hotelAddress;

  @Column(nullable = false)
  @NotBlank
  @Email
  private String contactEmail;

  @Column(nullable = false)
  @NotBlank
  private String contactPhone;
}
