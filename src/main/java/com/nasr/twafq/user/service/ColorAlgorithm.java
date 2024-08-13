package com.nasr.twafq.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import com.nasr.twafq.constants.HashMapInitializer;
import com.nasr.twafq.constants.SameValueMapInitializer;
import com.nasr.twafq.user.entity.UserAlgorithm;
import com.nasr.twafq.user.entity.UserPersentage;
import com.nasr.twafq.user.repo.UserAlgorithmRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ColorAlgorithm {

    private final SameValueMapInitializer sameValueMapInitializer;
    private final HashMapInitializer hashMapInitializer;
    private final UserAlgorithmRepository userAlgorithmRepository;

    public void calculateColorPresentage(String userId, ArrayList<Integer> userAnswers) {
        List<UserAlgorithm> users = userAlgorithmRepository.findAll();

        // Create a new UserAlgorithm object for the current user
        UserAlgorithm userAlgorithm = new UserAlgorithm();
        userAlgorithm.setUserId(userId);
        userAlgorithm.setAnswer(userAnswers);

        Integer sum = 0;
        Double percentage = 0.0;
        Integer counter = 0;

        for (UserAlgorithm user : users) {
            ArrayList<Integer> savedUserAnswer = user.getAnswer();

            for (Integer i : userAnswers) {
                for (Integer j : savedUserAnswer) {
                    Integer calculatedValue = pairCalculate(i, j);
                    if (calculatedValue != -1) {
                        sum += calculatedValue;
                        counter++;
                    }
                }
            }

            if (counter != 0) {
                percentage = (double) sum / counter;
            }

            // Update the usersLikeMe array
            ArrayList<UserPersentage> usersLikeMe = user.getUsersLikeMe();
            addUserToUsersLikeMe(usersLikeMe, new UserPersentage(userId, percentage));

            user.setUsersLikeMe(usersLikeMe);
            userAlgorithmRepository.save(user);

            // Update the usersLikeMe array for the current user
            ArrayList<UserPersentage> usersLikeMeForUser = userAlgorithm.getUsersLikeMe();
            addUserToUsersLikeMe(usersLikeMeForUser, new UserPersentage(user.getUserId(), percentage));

            userAlgorithm.setUsersLikeMe(usersLikeMeForUser);
        }

        // Save the current user's algorithm data
        userAlgorithmRepository.save(userAlgorithm);
    }

    // Helper method to add a user to the usersLikeMe list
    private void addUserToUsersLikeMe(ArrayList<UserPersentage> usersLikeMe, UserPersentage userPersentage) {
        if (usersLikeMe.size() < 10) {
            usersLikeMe.add(userPersentage);
        } else {
            UserPersentage lastUser = usersLikeMe.get(usersLikeMe.size() - 1);
            if (lastUser.getPresentage() < userPersentage.getPresentage()) {
                usersLikeMe.add(userPersentage);
                usersLikeMe.remove(lastUser);
                usersLikeMe.sort((o1, o2) -> o2.getPresentage().compareTo(o1.getPresentage()));
            }
        }
    }

    // Method to calculate the value for a pair of colors
    public Integer pairCalculate(Integer color1, Integer color2) {

        ArrayList<ArrayList<Integer>> sameValueArray = sameValueMapInitializer.getSameValueArray();
        Map<Pair<Integer, Integer>, Integer> dataMap = hashMapInitializer.getDataMap();

        // The array that contains the same values of color1
        ArrayList<Integer> sameValuesForColor1 = sameValueArray.get(color1);

        // The array that contains the same values of color2
        ArrayList<Integer> sameValuesForColor2 = sameValueArray.get(color2);

        for (Integer sameValue1 : sameValuesForColor1) {
            for (Integer sameValue2 : sameValuesForColor2) {
                Pair<Integer, Integer> pair = Pair.of(sameValue1, sameValue2);
                Integer value = dataMap.get(pair);
                if (value != null) {
                    return value;
                }
            }
        }

        return -1;
    }
}
