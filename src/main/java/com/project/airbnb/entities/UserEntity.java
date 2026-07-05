package com.project.airbnb.entities;

import com.project.airbnb.enums.Gender;
import com.project.airbnb.enums.Role;
import com.project.airbnb.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false)
  @NotBlank
  private String firstName;

  @NotBlank
  @Column(nullable = false)
  private String lastName;

  @Column(unique = true)
  private String username;

  @Column(nullable = false)
  @NotBlank
  private String password;

  @Column(unique = true, nullable = false)
  @NotBlank
  @Email(message = "Please enter a valid email")
  private String email;

  @Column(nullable = false)
  @Past
  private LocalDate birthDate;

  @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
  private String mobile;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Gender gender;

  @ElementCollection(fetch = FetchType.EAGER) // JPA will create new table
  @Enumerated(EnumType.STRING)
  private Set<Role> role; // (Admin | Tenant | End-User)

  @Enumerated(EnumType.STRING)
  @NotNull
  private UserStatus status = UserStatus.ACTIVE;
}
