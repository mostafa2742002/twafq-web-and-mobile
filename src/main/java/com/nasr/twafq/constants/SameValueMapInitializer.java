package com.nasr.twafq.constants;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasr.twafq.samevalue.entity.SameValueArray;
import com.nasr.twafq.samevalue.service.SameValueArrayService;

import jakarta.annotation.PostConstruct;

@Component
public class SameValueMapInitializer {

    private ArrayList<ArrayList<Integer>> sameValueArray;

    @Autowired
    private SameValueArrayService sameValueArrayService;

    @PostConstruct
    public void init() {
        sameValueArray = new ArrayList<>(17);

        // Initialize the list with empty arrays
        for (int i = 0; i < 17; i++) {
            sameValueArray.add(new ArrayList<>());
        }

        // Populate the arrays with data from MongoDB
        for (SameValueArray array : sameValueArrayService.getAllSameValueArrays()) {
            sameValueArray.get(array.getIndex()).addAll(array.getValues());
        }
    }

    public ArrayList<ArrayList<Integer>> getSameValueArray() {
        return sameValueArray;
    }
}


        // // index 0
        // sameValueِArray.get(0).add(53);
        // // index 1
        // sameValueِArray.get(1).add(1);
        // // index 2
        // sameValueِArray.get(2).add(9);
        // // index 3
        // sameValueِArray.get(3).add(46);
        // // index 4
        // sameValueِArray.get(4).add(8);
        // sameValueِArray.get(4).add(24);
        // // index 5
        // sameValueِArray.get(5).add(52);
        // sameValueِArray.get(5).add(63);
        // // index 6
        // sameValueِArray.get(6).add(47);
        // sameValueِArray.get(6).add(58);
        // // index 7
        // sameValueِArray.get(7).add(2);
        // sameValueِArray.get(7).add(18);
        // // index 8
        // sameValueِArray.get(8).add(51);
        // sameValueِArray.get(8).add(57);
        // sameValueِArray.get(8).add(62);
        // sameValueِArray.get(8).add(69);
        // // index 9
        // sameValueِArray.get(9).add(48);
        // sameValueِArray.get(9).add(54);
        // sameValueِArray.get(9).add(59);
        // sameValueِArray.get(9).add(66);
        // // index 10
        // sameValueِArray.get(10).add(3);
        // sameValueِArray.get(10).add(11);
        // sameValueِArray.get(10).add(19);
        // sameValueِArray.get(10).add(10);
        // sameValueِArray.get(10).add(31);
        // // index 11
        // sameValueِArray.get(11).add(7);
        // sameValueِArray.get(11).add(15);
        // sameValueِArray.get(11).add(23);
        // sameValueِArray.get(11).add(16);
        // sameValueِArray.get(11).add(35);
        // // index 12
        // sameValueِArray.get(12).add(50);
        // sameValueِArray.get(12).add(56);
        // sameValueِArray.get(12).add(61);
        // sameValueِArray.get(12).add(65);
        // sameValueِArray.get(12).add(68);
        // sameValueِArray.get(12).add(71);
        // sameValueِArray.get(12).add(73);
        // // index 13
        // sameValueِArray.get(13).add(49);
        // sameValueِArray.get(13).add(55);
        // sameValueِArray.get(13).add(60);
        // sameValueِArray.get(13).add(64);
        // sameValueِArray.get(13).add(67);
        // sameValueِArray.get(13).add(70);
        // sameValueِArray.get(13).add(72);
        // // index 14
        // sameValueِArray.get(14).add(6);
        // sameValueِArray.get(14).add(14);
        // sameValueِArray.get(14).add(22);
        // sameValueِArray.get(14).add(28);
        // sameValueِArray.get(14).add(34);
        // sameValueِArray.get(14).add(25);
        // sameValueِArray.get(14).add(42);
        // // index 15
        // sameValueِArray.get(15).add(4);
        // sameValueِArray.get(15).add(12);
        // sameValueِArray.get(15).add(20);
        // sameValueِArray.get(15).add(26);
        // sameValueِArray.get(15).add(32);
        // sameValueِArray.get(15).add(17);
        // sameValueِArray.get(15).add(40);
        // // index 16
        // sameValueِArray.get(16).add(5);
        // sameValueِArray.get(16).add(13);
        // sameValueِArray.get(16).add(21);
        // sameValueِArray.get(16).add(27);
        // sameValueِArray.get(16).add(33);
        // sameValueِArray.get(16).add(37);
        // sameValueِArray.get(16).add(41);
        // sameValueِArray.get(16).add(43);
        // sameValueِArray.get(16).add(45);
        // sameValueِArray.get(16).add(29);
        // sameValueِArray.get(16).add(30);
        // sameValueِArray.get(16).add(36);
        // sameValueِArray.get(16).add(38);
        // sameValueِArray.get(16).add(39);