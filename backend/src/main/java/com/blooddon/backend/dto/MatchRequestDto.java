package com.blooddon.backend.dto;

public class MatchRequestDto {

    private Long requestId;
    private boolean sameCityOnly;

    public MatchRequestDto() {}

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public boolean isSameCityOnly() { return sameCityOnly; }
    public void setSameCityOnly(boolean sameCityOnly) { this.sameCityOnly = sameCityOnly; }
}
