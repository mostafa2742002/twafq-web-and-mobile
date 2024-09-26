package com.nasr.twafq.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nasr.twafq.constants.UserCache;
import com.nasr.twafq.user.dto.UserFilterRequest;
import com.nasr.twafq.user.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilterService {

    private UserCache userCache;

    public List<User> filterUsers(UserFilterRequest request) {
        List<User> users = userCache.getUsers(); // Retrieve the cached users
        List<User> filteredUsers = new ArrayList<>(); // List to store filtered users

        for (User user : users) {
            boolean matches = true;

            // Filter by user ID if provided exculded user
            if (request.getUserId() != null && !request.getUserId().isEmpty()) {
                if (user.getId().equals(request.getUserId())) {
                    matches = false;
                }
            }

            // Filter by nationality
            if (request.getNationality() != null && !request.getNationality().isEmpty()) {
                // Check if any nationality from the request matches the user's nationalities
                if (user.getNationality() == null || user.getNationality().isEmpty() ||
                        request.getNationality().stream().noneMatch(user.getNationality()::contains)) {
                    matches = false;
                }
            }

            // Filter by country of residence
            if (request.getCountryOfResidence() != null && !request.getCountryOfResidence().isEmpty()) {
                if (user.getResidence() == null || user.getResidence().isEmpty() ||
                        request.getCountryOfResidence().stream().noneMatch(user.getResidence()::contains)) {
                    matches = false;
                }
            }

            // Filter by country
            if (request.getCountry() != null && !request.getCountry().isEmpty()) {
                if (user.getCountry() == null || user.getCountry().isEmpty() ||
                        request.getCountry().stream().noneMatch(user.getCountry()::contains)) {
                    matches = false;
                }
            }

            // Filter by marital status
            if (request.getMaritalStatus() != null && !request.getMaritalStatus().isEmpty()) {
                if (user.getFamilyStatus() == null || user.getFamilyStatus().isEmpty() ||
                        request.getMaritalStatus().stream().noneMatch(user.getFamilyStatus()::contains)) {
                    matches = false;
                }
            }

            // Filter by gender
            if (request.getGender() != null && !request.getGender().isEmpty()) {
                if (user.getGender() == null || user.getGender().isEmpty() ||
                        request.getGender().stream().noneMatch(user.getGender()::contains)) {
                    matches = false;
                }
            }

            // Filter by city
            if (request.getCity() != null && !request.getCity().isEmpty()) {
                if (user.getCity() == null || user.getCity().isEmpty() ||
                        request.getCity().stream().noneMatch(user.getCity()::contains)) {
                    matches = false;
                }
            }

            // Filter by religion
            if (request.getReligion() != null && !request.getReligion().isEmpty()) {
                if (user.getReligion() == null || user.getReligion().isEmpty() ||
                        request.getReligion().stream().noneMatch(user.getReligion()::contains)) {
                    matches = false;
                }
            }

            // Filter by doctrine
            if (request.getDoctrine() != null && !request.getDoctrine().isEmpty()) {
                if (user.getDoctrine() == null || user.getDoctrine().isEmpty() ||
                        request.getDoctrine().stream().noneMatch(user.getDoctrine()::contains)) {
                    matches = false;
                }
            }

            // Filter by family status
            if (request.getFamilyStatus() != null && !request.getFamilyStatus().isEmpty()) {
                if (user.getFamilyStatus() == null || user.getFamilyStatus().isEmpty() ||
                        request.getFamilyStatus().stream().noneMatch(user.getFamilyStatus()::contains)) {
                    matches = false;
                }
            }

            // Filter by marriage type
            if (request.getMarriageType() != null && !request.getMarriageType().isEmpty()) {
                if (user.getMarriageType() == null || user.getMarriageType().isEmpty() ||
                        request.getMarriageType().stream().noneMatch(user.getMarriageType()::contains)) {
                    matches = false;
                }
            }

            // Filter by age
            if (request.getMinAge() != null && user.getAge() < request.getMinAge()) {
                matches = false;
            }

            if (request.getMaxAge() != null && user.getAge() > request.getMaxAge()) {
                matches = false;
            }

            // Add user to the filtered list if all conditions match
            if (matches) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers; // Return the list of filtered users
    }

}
