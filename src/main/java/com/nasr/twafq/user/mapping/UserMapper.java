// package com.nasr.twafq.user.mapping;

// import com.nasr.twafq.user.dto.UserDTO;
// import com.nasr.twafq.user.entity.User;

// public class UserMapper {

//     public static UserDTO toUserDTO(User user) {
//         UserDTO userDTO = new UserDTO();
//         userDTO.setEmail(user.getEmail());
//         userDTO.setPassword(user.getPassword());
//         userDTO.setConfirmPassword(""); // Assuming it's not stored and needs to be handled separately
//         userDTO.setUsername(user.getName());
//         userDTO.setFirstName(user.getFirstName());
//         userDTO.setLastName(user.getLastName());
//         userDTO.setPhone(user.getPhone());
//         userDTO.setAge(user.getAge());
//         userDTO.setWeight(user.getWeight());
//         userDTO.setHeight(user.getHeight());

//         // Set lists as arrays or multiple strings
//         userDTO.setGender(user.getGender());
//         userDTO.setSkinColor(user.getSkinColor());
//         userDTO.setShape(user.getBodyType()); // Mapping bodyType to shape
//         userDTO.setHealth(user.getHealthStatus());
//         userDTO.setNationality(user.getNationality());
//         userDTO.setCountry(user.getCountryOfResidence()); // Assuming country maps to countryOfResidence
//         userDTO.setCity(user.getCity());
//         userDTO.setResidence(user.getCountryOfResidence()); // Assuming residence and countryOfResidence are the same
//         userDTO.setFamilyStatus(user.getMaritalStatus()); // Assuming maritalStatus maps to familyStatus
//         userDTO.setMarriageType(user.getMarriageType());
//         userDTO.setChildren(user.getNumberOfChildren());
//         userDTO.setEducationLevel(user.getEducation());
//         userDTO.setWork(user.getJob());
//         userDTO.setFinancialStatus(user.getFinancialStatus());
//         userDTO.setReligion(user.getReligion());
//         userDTO.setDoctrine(""); // Assuming doctrine isn't in the User entity and needs to be handled separately
//         userDTO.setReligiousCommitment(""); // Assuming this is not in the current User entity
//         userDTO.setSmoking(user.isSmoking() ? "Yes" : "No"); // Assuming smoking is now handled as a string
//         userDTO.setAlcoholDrugs(""); // Assuming this needs to be handled separately
//         userDTO.setSelfDescription(user.getSelfDescription());
//         userDTO.setPartnerDescription(user.getPartnerPreferences());
//         userDTO.setIsChecked(false); // Assuming it's not stored in User entity

//         return userDTO;
//     }

//     public static User toUser(UserDTO userDTO) {
//         User user = new User();
//         user.setEmail(userDTO.getEmail());
//         user.setPassword(userDTO.getPassword());
//         user.setName(userDTO.getUsername());
//         user.setFirstName(userDTO.getFirstName());
//         user.setLastName(userDTO.getLastName());
//         user.setPhone(userDTO.getPhone());
//         user.setAge(userDTO.getAge());
//         user.setWeight(userDTO.getWeight());
//         user.setHeight(userDTO.getHeight());

//         // Set lists as single or multiple values
//         user.setGender(userDTO.getGender());
//         user.setSkinColor(userDTO.getSkinColor());
//         user.setBodyType(userDTO.getShape()); // Mapping shape to bodyType
//         user.setHealthStatus(userDTO.getHealth());
//         user.setNationality(userDTO.getNationality());
//         user.setCountryOfResidence(userDTO.getCountry());
//         user.setCity(userDTO.getCity());
//         user.setCountryOfResidence(userDTO.getResidence());
//         user.setMaritalStatus(userDTO.getFamilyStatus());
//         user.setMarriageType(userDTO.getMarriageType());
//         user.setNumberOfChildren(userDTO.getChildren());
//         user.setEducation(userDTO.getEducationLevel());
//         user.setJob(userDTO.getWork());
//         user.setFinancialStatus(userDTO.getFinancialStatus());
//         user.setReligion(userDTO.getReligion());
//         user.setSelfDescription(userDTO.getSelfDescription());
//         user.setPartnerPreferences(userDTO.getPartnerDescription());
//         user.setSmoking(userDTO.getSmoking().equalsIgnoreCase("Yes"));

//         return user;
//     }
// }
