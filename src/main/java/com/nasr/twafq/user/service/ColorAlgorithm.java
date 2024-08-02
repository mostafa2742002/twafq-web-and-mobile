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

        if (users == null || users.isEmpty()) {
            UserAlgorithm userAlgorithm = new UserAlgorithm();
            userAlgorithm.setUserId(userId);
            userAlgorithm.setAnswer(userAnswers);
            userAlgorithmRepository.save(userAlgorithm);
            return;
        }

        UserAlgorithm userAlgorithm = new UserAlgorithm();
        userAlgorithm.setUserId(userId);
        userAlgorithm.setAnswer(userAnswers);

        Integer sum = 0;
        Double presentage = 0.0;
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
                presentage = (double) sum / counter;
            }

            // now we need to get the userslikeme array and if it less that 10 users we will
            // add this user to the array
            // if it more than 10 we will check if the presentage is more than the last user
            // in the array we will add this user to the array
            // and remove the last user in the array then sort the array by the presentage
            // value
            // and we will do that to the user come from the parameter and save it in the
            // last step

            ArrayList<UserPersentage> usersLikeMe = user.getUsersLikeMe();
            if (usersLikeMe.size() < 10) {
                usersLikeMe.add(new UserPersentage(userId, presentage));
            } else {
                UserPersentage lastUser = usersLikeMe.get(usersLikeMe.size() - 1);
                if (lastUser.getPresentage() < presentage) {
                    usersLikeMe.add(new UserPersentage(userId, presentage));
                    usersLikeMe.remove(lastUser);
                    usersLikeMe.sort((o1, o2) -> o1.getPresentage().compareTo(o2.getPresentage()));
                }
            }

            user.setUsersLikeMe(usersLikeMe);
            userAlgorithmRepository.save(user);

            // we will do the same for the userslike me to the userAlgorithm object

            ArrayList<UserPersentage> usersLikeMeForUser = userAlgorithm.getUsersLikeMe();
            if (usersLikeMeForUser.size() < 10) {
                usersLikeMeForUser.add(new UserPersentage(userId, presentage));
            } else {
                UserPersentage lastUser = usersLikeMeForUser.get(usersLikeMeForUser.size() - 1);
                if (lastUser.getPresentage() < presentage) {
                    usersLikeMeForUser.add(new UserPersentage(userId, presentage));
                    usersLikeMeForUser.remove(lastUser);
                    usersLikeMeForUser.sort((o1, o2) -> o1.getPresentage().compareTo(o2.getPresentage()));
                }
            }

            userAlgorithm.setUsersLikeMe(usersLikeMeForUser);
        }

        userAlgorithmRepository.save(userAlgorithm);

    }

    public Integer pairCalculate(Integer color1, Integer color2) {

        ArrayList<ArrayList<Integer>> sameValueArray = sameValueMapInitializer.getSameValueِArray();
        Map<Pair<Integer, Integer>, Integer> dataMap = hashMapInitializer.getDataMap();

        // the array that contains the same values of the color1
        ArrayList<Integer> sameValuesForColor1 = sameValueArray.get(color1);

        // the array that contains the same values of the color2
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
