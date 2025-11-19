package com.blooddon.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RequestResponseDTO {

    private Long id;
    private String requesterFullName;
    private String requesterPhone;
    private String bloodType;
    private String caseDescription;
    private String city;

    private double latitude;
    private double longitude;
    private String locationLabel;

    private List<ReviewDto> reviews;

    private List<Long> matchedDonorIds;
    private boolean completed;

    private LocalDateTime createdAt;

    public RequestResponseDTO() {}

    // GETTERS & SETTERS

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequesterFullName() { return requesterFullName; }
    public void setRequesterFullName(String requesterFullName) { this.requesterFullName = requesterFullName; }

    public String getRequesterPhone() { return requesterPhone; }
    public void setRequesterPhone(String requesterPhone) { this.requesterPhone = requesterPhone; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

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

    public List<Long> getMatchedDonorIds() { return matchedDonorIds; }
    public void setMatchedDonorIds(List<Long> matchedDonorIds) { this.matchedDonorIds = matchedDonorIds; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

}
