package com.nasr.twafq.user.mapping;

import com.nasr.twafq.user.dto.UserDTO;
import com.nasr.twafq.user.entity.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhone(user.getPhone());

        // Additional attributes
        userDTO.setGender(user.getGender());
        userDTO.setNationality(user.getNationality());
        userDTO.setCountryOfResidence(user.getCountryOfResidence());
        userDTO.setCity(user.getCity());
        userDTO.setMarriageType(user.getMarriageType());
        userDTO.setMaritalStatus(user.getMaritalStatus());
        userDTO.setAge(user.getAge());
        userDTO.setNumberOfChildren(user.getNumberOfChildren());
        userDTO.setWeight(user.getWeight());
        userDTO.setHeight(user.getHeight());
        userDTO.setSkinColor(user.getSkinColor());
        userDTO.setBodyType(user.getBodyType());
        userDTO.setJob(user.getJob());
        userDTO.setEducation(user.getEducation());
        userDTO.setFinancialStatus(user.getFinancialStatus());
        userDTO.setMonthlyIncome(user.getMonthlyIncome());
        userDTO.setHealthStatus(user.getHealthStatus());
        userDTO.setReligion(user.getReligion());
        userDTO.setSelfDescription(user.getSelfDescription());
        userDTO.setPartnerPreferences(user.getPartnerPreferences());
        userDTO.setSmoking(user.isSmoking());

        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());

        // Additional attributes
        user.setGender(userDTO.getGender());
        user.setNationality(userDTO.getNationality());
        user.setCountryOfResidence(userDTO.getCountryOfResidence());
        user.setCity(userDTO.getCity());
        user.setMarriageType(userDTO.getMarriageType());
        user.setMaritalStatus(userDTO.getMaritalStatus());
        user.setAge(userDTO.getAge());
        user.setNumberOfChildren(userDTO.getNumberOfChildren());
        user.setWeight(userDTO.getWeight());
        user.setHeight(userDTO.getHeight());
        user.setSkinColor(userDTO.getSkinColor());
        user.setBodyType(userDTO.getBodyType());
        user.setJob(userDTO.getJob());
        user.setEducation(userDTO.getEducation());
        user.setFinancialStatus(userDTO.getFinancialStatus());
        user.setMonthlyIncome(userDTO.getMonthlyIncome());
        user.setHealthStatus(userDTO.getHealthStatus());
        user.setReligion(userDTO.getReligion());
        user.setSelfDescription(userDTO.getSelfDescription());
        user.setPartnerPreferences(userDTO.getPartnerPreferences());
        user.setSmoking(userDTO.isSmoking());

        return user;
    }
}
