package com.blooddon.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BloodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Requester info
    private String requesterFullName;
    private String requesterPhone;

    // Blood type
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    // Request details
    private String caseDescription;
    private String city;


    private double latitude;
    private double longitude;
    private String locationLabel;

    @Column(length = 500)
    private String motivationMessage;

    // Multi-donor matching
    @ElementCollection
    private List<Long> matchedDonorIds = new ArrayList<>();

    private boolean isCompleted;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BloodRequest() {}

    //  MAPPING
    public static BloodRequest fromDTO(
            com.blooddon.backend.dto.CreateRequestDto dto,
            BloodType bloodType
    ) {
        BloodRequest req = new BloodRequest();
        req.setRequesterFullName(dto.getRequesterFullName());
        req.setRequesterPhone(dto.getRequesterPhone());
        req.setBloodType(bloodType);
        req.setCaseDescription(dto.getCaseDescription());
        req.setCity(dto.getCity());
        req.setLatitude(dto.getLatitude());
        req.setLongitude(dto.getLongitude());
        req.setLocationLabel(dto.getLocationLabel());
        req.setMotivationMessage(dto.getMotivationMessage());
        return req;
    }

    //  TIMESTAMP
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public String getRequesterFullName() { return requesterFullName; }
    public void setRequesterFullName(String requesterFullName) { this.requesterFullName = requesterFullName; }

    public String getRequesterPhone() { return requesterPhone; }
    public void setRequesterPhone(String requesterPhone) { this.requesterPhone = requesterPhone; }

    public BloodType getBloodType() { return bloodType; }
    public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }

    public String getCaseDescription() { return caseDescription; }
    public void setCaseDescription(String caseDescription) { this.caseDescription = caseDescription; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getLocationLabel() { return locationLabel; }
    public void setLocationLabel(String locationLabel) { this.locationLabel = locationLabel; }

    public String getMotivationMessage() { return motivationMessage; }
    public void setMotivationMessage(String motivationMessage) { this.motivationMessage = motivationMessage; }

    public List<Long> getMatchedDonorIds() { return matchedDonorIds; }
    public void setMatchedDonorIds(List<Long> matchedDonorIds) { this.matchedDonorIds = matchedDonorIds; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
