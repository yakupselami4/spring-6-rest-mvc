package com.yakupselami.spring6restmvc.model;

import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
public class CustomerDTO {
    @NotBlank
    @NotNull
    private String customerName;

    @NotNull
    private UUID customerId;

    @Version
    private Integer version;

    @NotNull
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
