package com.nasr.twafq.colorspairs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nasr.twafq.colorspairs.entity.DataPair;
import com.nasr.twafq.colorspairs.repo.DataPairRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataPairService {

    @Autowired
    private DataPairRepository dataPairRepository;

    public List<DataPair> getAllDataPairs() {
        return dataPairRepository.findAll();
    }

    public Optional<DataPair> getDataPair(int key1, int key2) {
        return dataPairRepository.findByKey1AndKey2(key1, key2);
    }

    public DataPair addDataPair(DataPair dataPair) {
        return dataPairRepository.save(dataPair);
    }

    public DataPair updateDataPair(String id, DataPair dataPair) {
        dataPair.setId(id);
        return dataPairRepository.save(dataPair);
    }

    public void deleteDataPair(String id) {
        dataPairRepository.deleteById(id);
    }

    public List<DataPair> addAllDataPairs(List<DataPair> dataPairs) {
        return dataPairRepository.saveAll(dataPairs);
    }

}
