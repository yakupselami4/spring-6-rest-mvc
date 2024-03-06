package com.yakupselami.spring6restmvc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36,columnDefinition = "varchar",updatable = false,nullable = false)
    private UUID customerId;


    @NotNull
    @NotBlank
    private String customerName;

    @Version
    private Integer version;

    @NotNull
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
