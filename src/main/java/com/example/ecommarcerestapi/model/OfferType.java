package com.example.ecommarcerestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offer_type")
public class OfferType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_type_id")
    private Long offerTypeId;
    @Column(name = "offer_type_name",nullable = false)
    private String offerTypeName;
    @Column(name = "offer_ram",nullable = false)
    private Integer ram;
    @Column(name = "offer_videoCard")
    private Integer videoCard;
    @Column(name = "offer_size")
    private String offerSize;
    @Column(name = "offer_cpu")
    private String offerCpu;
    @Column(name = "offer_rem")
    private Integer rem;
    @Column(name = "offer_batttery_life")
    private String offerBatteryLife;
    @Column(name = "adapter_voltage")
    private String adapterVoltage;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name = "status", columnDefinition = "integer default 1")
    private Integer status;
}
