package com.blooddon.backend.mappers;

import com.blooddon.backend.dto.RegisterRequesterRequest;
import com.blooddon.backend.models.RequesterProfile;
import com.blooddon.backend.models.User;

public class RequesterMapper {

    public static User toUser(RegisterRequesterRequest dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // should be hashed later
        user.setRole(User.Role.REQUESTER);
        return user;
    }

    public static RequesterProfile toProfile(RegisterRequesterRequest dto, User savedUser) {
        RequesterProfile profile = new RequesterProfile();
        profile.setUser(savedUser);
        profile.setFullName(dto.getFullName());
        profile.setOrganizationName(dto.getOrganizationName());
        profile.setCity(dto.getCity());
        profile.setPhoneNumber(dto.getPhone());
        return profile;
    }
}
