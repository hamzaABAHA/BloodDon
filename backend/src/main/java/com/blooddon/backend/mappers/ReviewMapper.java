package com.blooddon.backend.mappers;

import com.blooddon.backend.dto.ReviewDto;
import com.blooddon.backend.models.Review;

public class ReviewMapper {

    public static Review fromDto(ReviewDto dto) {
        Review review = new Review();
        review.setDonorId(dto.getDonorId());
        review.setMessage(dto.getMessage());
        review.setPointsGiven(dto.getPointsGiven());
        return review;
    }

}
