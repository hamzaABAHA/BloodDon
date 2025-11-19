package com.blooddon.backend.mappers;

import com.blooddon.backend.dto.CreateRequestDto;
import com.blooddon.backend.dto.RequestResponseDTO;
import com.blooddon.backend.dto.ReviewDto;
import com.blooddon.backend.models.BloodRequest;
import com.blooddon.backend.models.BloodType;

import java.util.List;

public class BloodRequestMapper {

    public static BloodRequest fromDto(CreateRequestDto dto) {
        BloodRequest req = new BloodRequest();
        req.setRequesterFullName(dto.getRequesterFullName());
        req.setRequesterPhone(dto.getRequesterPhone());
        req.setBloodType(dto.getBloodType()); // DTO uses ENUM → DIRECT
        req.setCaseDescription(dto.getCaseDescription());
        req.setCity(dto.getCity());
        req.setLatitude(dto.getLatitude());
        req.setLongitude(dto.getLongitude());
        req.setLocationLabel(dto.getLocationLabel());
        req.setMotivationMessage(dto.getMotivationMessage());
        return req;
    }

    public static RequestResponseDTO toDto(BloodRequest entity) {
        RequestResponseDTO dto = new RequestResponseDTO();

        dto.setId(entity.getId());
        dto.setRequesterFullName(entity.getRequesterFullName());
        dto.setRequesterPhone(entity.getRequesterPhone());
        dto.setBloodType(entity.getBloodType().name());
        dto.setCaseDescription(entity.getCaseDescription());
        dto.setCity(entity.getCity());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setLocationLabel(entity.getLocationLabel());
        dto.setMatchedDonorIds(entity.getMatchedDonorIds());
        dto.setCompleted(entity.isCompleted());
        dto.setCreatedAt(entity.getCreatedAt());

        List<ReviewDto> reviewDtos = entity.getReviews().stream()
                .map(review -> {
                    ReviewDto r = new ReviewDto();
                    r.setRequestId(entity.getId());
                    r.setDonorId(review.getDonorId());
                    r.setMessage(review.getMessage());
                    r.setPointsGiven(review.getPointsGiven());
                    return r;
                })
                .toList();

        dto.setReviews(reviewDtos);

        return dto;
    }

}
