package com.nasr.twafq.user.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.nasr.twafq.user.dto.UserFilterRequest;
import com.nasr.twafq.user.entity.User;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<User> findFilteredUsers(UserFilterRequest filterRequest) {
        Criteria criteria = new Criteria();
        List<Criteria> criteriaList = new ArrayList<>();

        // Add conditions to the criteria list based on filterRequest
        if (filterRequest.getNationality() != null && !filterRequest.getNationality().isEmpty()) {
            criteriaList.add(Criteria.where("nationality").in(filterRequest.getNationality()));
        }

        if (filterRequest.getCountryOfResidence() != null && !filterRequest.getCountryOfResidence().isEmpty()) {
            criteriaList.add(Criteria.where("countryOfResidence").in(filterRequest.getCountryOfResidence()));
        }

        if (filterRequest.getMaritalStatus() != null && !filterRequest.getMaritalStatus().isEmpty()) {
            criteriaList.add(Criteria.where("maritalStatus").in(filterRequest.getMaritalStatus()));
        }

        if (filterRequest.getGender() != null && !filterRequest.getGender().isEmpty()) {
            criteriaList.add(Criteria.where("gender").in(filterRequest.getGender()));
        }

        if (filterRequest.getCity() != null && !filterRequest.getCity().isEmpty()) {
            criteriaList.add(Criteria.where("city").in(filterRequest.getCity()));
        }

        if (filterRequest.getReligion() != null && !filterRequest.getReligion().isEmpty()) {
            criteriaList.add(Criteria.where("religion").in(filterRequest.getReligion()));
        }

        if (filterRequest.getFamilyStatus() != null && !filterRequest.getFamilyStatus().isEmpty()) {
            criteriaList.add(Criteria.where("familyStatus").in(filterRequest.getFamilyStatus()));
        }

        if (filterRequest.getMarriageType() != null && !filterRequest.getMarriageType().isEmpty()) {
            criteriaList.add(Criteria.where("marriageType").in(filterRequest.getMarriageType()));
        }

        if (filterRequest.getMinAge() != null) {
            criteriaList.add(Criteria.where("age").gte(filterRequest.getMinAge()));
        }

        if (filterRequest.getMaxAge() != null) {
            criteriaList.add(Criteria.where("age").lte(filterRequest.getMaxAge()));
        }

        // Combine all criteria into one, if there are any criteria to apply
        if (!criteriaList.isEmpty()) {
            criteria.andOperator(criteriaList.toArray(new Criteria[0]));
        } else {
            // If no criteria are specified, match all documents
            criteria = new Criteria(); // Empty criteria matches all documents
        }

        Query query = new Query(criteria);
        Pageable pageable = PageRequest.of(filterRequest.getPage(), filterRequest.getSize());
        query.with(pageable);

        List<User> users = mongoTemplate.find(query, User.class);
        long count = mongoTemplate.count(query, User.class);

        return new PageImpl<>(users, pageable, count);
    }
}
