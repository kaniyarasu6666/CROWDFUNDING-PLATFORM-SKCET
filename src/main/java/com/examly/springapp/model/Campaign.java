package com.examly.springapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    @NotBlank(message = "Title is required")
    private String title;

    @Column(length = 500, nullable = false)
    @Size(min = 20, max = 500, message = "Description must be between 20 and 500 characters")
    @NotBlank(message = "Description is required")
    private String description;

    @DecimalMin(value = "100.00", message = "Goal amount must be at least 100")
    private BigDecimal goalAmount;

    private BigDecimal currentAmount;

    @Future(message = "Deadline must be a future date")
    private LocalDate deadline;

    @Column(nullable = false)
    @NotBlank(message = "Category is required")
    private String category;

    @Column(nullable = false)
    @NotBlank(message = "Creator name is required")
    private String creatorName;

    @Enumerated(EnumType.STRING)
    private CampaignStatus status;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Donation> donations;
}