package com.blooddon.backend.dto;

public class ReviewDto {

    private Long requestId;
    private Long donorId;
    private String message;
    private int pointsGiven;

    public ReviewDto() {}

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public Long getDonorId() { return donorId; }
    public void setDonorId(Long donorId) { this.donorId = donorId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getPointsGiven() { return pointsGiven; }
    public void setPointsGiven(int pointsGiven) { this.pointsGiven = pointsGiven; }
}
