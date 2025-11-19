package com.blooddon.backend.controllers;

import com.blooddon.backend.dto.CreateRequestDto;
import com.blooddon.backend.dto.MatchRequestDto;
import com.blooddon.backend.dto.RequestResponseDTO;
import com.blooddon.backend.dto.ReviewDto;
import com.blooddon.backend.models.DonorProfile;
import com.blooddon.backend.services.BloodRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requester")
public class RequestController {

    private final BloodRequestService bloodRequestService;

    public RequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    // ---------------- CREATE REQUEST ----------------
    @PostMapping("/create")
    public RequestResponseDTO createRequest(@RequestBody CreateRequestDto dto) {
        return bloodRequestService.createRequest(dto);
    }

    // ---------------- MATCH DONORS ----------------
    @GetMapping("/match/{requestId}")
    public List<DonorProfile> matchDonors(
            @PathVariable Long requestId,
            @RequestParam(defaultValue = "false") boolean sameCityOnly
    ) {
        return bloodRequestService.matchDonors(requestId, sameCityOnly);
    }

    // ---------------- ADD MATCHED DONOR ----------------
    @PostMapping("/add-donor")
    public RequestResponseDTO addMatchedDonor(
            @RequestParam Long requestId,
            @RequestParam Long donorId
    ) {
        return bloodRequestService.addMatchedDonor(requestId, donorId);
    }

    // ---------------- ADD REVIEW ----------------
    @PostMapping("/review")
    public RequestResponseDTO addReview(@RequestBody ReviewDto dto) {
        return bloodRequestService.addReview(dto);
    }

    // ---------------- COMPLETE REQUEST ----------------
    @PostMapping("/complete/{id}")
    public RequestResponseDTO completeRequest(@PathVariable Long id) {
        return bloodRequestService.completeRequest(id);
    }

    // ---------------- GET REQUEST BY ID ----------------
    @GetMapping("/{id}")
    public RequestResponseDTO getRequest(@PathVariable Long id) {
        return bloodRequestService.getRequest(id);
    }
}
