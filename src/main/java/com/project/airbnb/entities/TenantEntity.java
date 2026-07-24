package com.project.airbnb.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tenants")
@Getter
@Setter
public class TenantEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tenantId;

  @Column(nullable = false)
  @NotBlank
  private String firstName;

  @NotBlank
  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  @NotBlank
  private String password;

  @Column(unique = true, nullable = false)
  @NotBlank
  @Email(message = "Please enter a valid email")
  private String email;

  @Column(nullable = false)
  @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
  @NotBlank
  private String mobile;

  @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<HotelEntity> hotels = new ArrayList<>();

  @Transient
  public String getFullName() {
    return firstName + " " + lastName;
  }
}
