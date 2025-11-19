package com.blooddon.backend.services;

import com.blooddon.backend.dto.CreateRequestDto;
import com.blooddon.backend.dto.RequestResponseDTO;
import com.blooddon.backend.dto.ReviewDto;
import com.blooddon.backend.mappers.BloodRequestMapper;
import com.blooddon.backend.mappers.ReviewMapper;
import com.blooddon.backend.models.BloodRequest;
import com.blooddon.backend.models.DonorProfile;
import com.blooddon.backend.repositories.BloodRequestRepository;
import com.blooddon.backend.repositories.DonorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;
    private final DonorProfileRepository donorProfileRepository;
    private final DonorService donorService;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository,
                               DonorProfileRepository donorProfileRepository,
                               DonorService donorService) {

        this.bloodRequestRepository = bloodRequestRepository;
        this.donorProfileRepository = donorProfileRepository;
        this.donorService = donorService;
    }

    // ---------------- CREATE REQUEST ----------------
    public RequestResponseDTO createRequest(CreateRequestDto dto) {

        BloodRequest entity = BloodRequestMapper.fromDto(dto);
        BloodRequest saved = bloodRequestRepository.save(entity);

        return BloodRequestMapper.toDto(saved);
    }

    // ---------------- FIND MATCHING DONORS ----------------
    public List<DonorProfile> matchDonors(Long requestId, boolean sameCityOnly) {

        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        return donorService.matchDonors(
                request.getBloodType(),  // converted to string for donorService
                request.getCity(),
                sameCityOnly
        );
    }

    // ---------------- ADD MATCHED DONOR ----------------
    public RequestResponseDTO addMatchedDonor(Long requestId, Long donorId) {

        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // Add donor to list
        if (!request.getMatchedDonorIds().contains(donorId)) {
            request.getMatchedDonorIds().add(donorId);
        }

        // Donor becomes unavailable
        DonorProfile donor = donorProfileRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found"));

        donor.setAvailable(false);
        donorProfileRepository.save(donor);

        BloodRequest saved = bloodRequestRepository.save(request);
        return BloodRequestMapper.toDto(saved);
    }

    // ---------------- ADD REVIEW (THANK YOU MESSAGE + POINTS) ----------------
    public RequestResponseDTO addReview(ReviewDto dto) {

        BloodRequest request = bloodRequestRepository.findById(dto.getRequestId())
                .orElseThrow(() -> new RuntimeException("Request not found"));

        // 🔥 Vérifier si une review existe déjà pour ce donneur
        boolean alreadyReviewed = request.getReviews().stream()
                .anyMatch(r -> r.getDonorId().equals(dto.getDonorId()));

        if (alreadyReviewed) {
            throw new IllegalArgumentException("Review already exists for this donor.");
        }

        // Ajouter une nouvelle review
        request.getReviews().add(ReviewMapper.fromDto(dto));

        BloodRequest saved = bloodRequestRepository.save(request);
        return BloodRequestMapper.toDto(saved);
    }



    // ---------------- COMPLETE REQUEST ----------------
    public RequestResponseDTO completeRequest(Long requestId) {

        BloodRequest request = bloodRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setCompleted(true);

        BloodRequest saved = bloodRequestRepository.save(request);
        return BloodRequestMapper.toDto(saved);
    }

    // ---------------- GET REQUEST BY ID ----------------
    public RequestResponseDTO getRequest(Long id) {
        BloodRequest req = bloodRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        return BloodRequestMapper.toDto(req);
    }
}
