package com.project.airbnb.entities;

import com.project.airbnb.entities.helper.HotelDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "hotels")
@Getter
@Setter
public class HotelEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long hotelId;

  @Column(name = "name", nullable = false)
  @NotBlank
  private String hotelName;

  @Embedded private HotelDetails details;

  private Boolean active = true;

  @CreationTimestamp private LocalDateTime createdAt;

  @UpdateTimestamp private LocalDateTime updatedAt;

  //   Tenant details
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "tenant_id", nullable = false)
  private TenantEntity tenant;

  @OneToMany(mappedBy = "hotel")
  private List<RoomEntity> rooms = new ArrayList<>();
}
