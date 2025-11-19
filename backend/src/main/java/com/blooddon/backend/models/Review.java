package com.blooddon.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long donorId;

    @Column(length = 500)
    private String message;

    private int pointsGiven;

    private LocalDateTime createdAt;

    public Review() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // GETTERS & SETTERS

    public Long getId() { return id; }

    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getPointsGiven() { return pointsGiven; }
    public void setPointsGiven(int pointsGiven) { this.pointsGiven = pointsGiven; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
