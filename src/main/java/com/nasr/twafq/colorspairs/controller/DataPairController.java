package com.nasr.twafq.colorspairs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nasr.twafq.colorspairs.entity.DataPair;
import com.nasr.twafq.colorspairs.service.DataPairService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/datapairs")
public class DataPairController {

    @Autowired
    private DataPairService dataPairService;

    @GetMapping
    public List<DataPair> getAllDataPairs() {
        return dataPairService.getAllDataPairs();
    }

    @GetMapping("/{key1}/{key2}")
    public ResponseEntity<DataPair> getDataPair(@PathVariable int key1, @PathVariable int key2) {
        Optional<DataPair> dataPair = dataPairService.getDataPair(key1, key2);
        return dataPair.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DataPair addDataPair(@RequestBody DataPair dataPair) {
        return dataPairService.addDataPair(dataPair);
    }

    @PutMapping("/{id}")
    public DataPair updateDataPair(@PathVariable String id, @RequestBody DataPair dataPair) {
        return dataPairService.updateDataPair(id, dataPair);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDataPair(@PathVariable String id) {
        dataPairService.deleteDataPair(id);
        return ResponseEntity.noContent().build();
    }
}
