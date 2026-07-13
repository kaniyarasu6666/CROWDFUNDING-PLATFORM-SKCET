package com.examly.springapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "Goal Amount", example = "10000")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "1.00", message = "Amount must be greater than 0")
    @Schema(description = "Donation Amount", example = "500")
    private BigDecimal amount;

    @NotBlank(message = "Donor name is required")
    private String donorName;

    private String message;

    private LocalDateTime donatedAt;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @JsonBackReference
    private Campaign campaign;
}