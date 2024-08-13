package com.nasr.twafq.samevalue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasr.twafq.color.entity.Color;
import com.nasr.twafq.samevalue.entity.SameValueArray;
import com.nasr.twafq.samevalue.repo.SameValueArrayRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SameValueArrayService {

    @Autowired
    private SameValueArrayRepository sameValueArrayRepository;

    public List<SameValueArray> getAllSameValueArrays() {
        return sameValueArrayRepository.findAll();
    }

    public Optional<SameValueArray> getSameValueArray(int index) {
        return sameValueArrayRepository.findByIndex(index);
    }

    public SameValueArray addSameValueArray(SameValueArray sameValueArray) {
        // Automatically assign the next available index
        Integer nextIndex = getNextAvailableIndex();
        sameValueArray.setIndex(nextIndex);
        return sameValueArrayRepository.save(sameValueArray);
    }

    public SameValueArray updateSameValueArray(String id, SameValueArray sameValueArray) {
        sameValueArray.setId(id);
        return sameValueArrayRepository.save(sameValueArray);
    }

    public void deleteSameValueArray(String id) {
        sameValueArrayRepository.deleteById(id);
    }

    private Integer getNextAvailableIndex() {
        List<SameValueArray> colors = sameValueArrayRepository.findAll();
        return colors.size();
    }
}
