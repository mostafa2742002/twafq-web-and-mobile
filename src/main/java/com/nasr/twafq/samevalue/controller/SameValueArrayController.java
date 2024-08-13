package com.nasr.twafq.samevalue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasr.twafq.samevalue.entity.SameValueArray;
import com.nasr.twafq.samevalue.service.SameValueArrayService;

@RestController
@RequestMapping("/api/samevaluearrays")
public class SameValueArrayController {

    @Autowired
    private SameValueArrayService sameValueArrayService;

    @GetMapping
    public List<SameValueArray> getAllSameValueArrays() {
        return sameValueArrayService.getAllSameValueArrays();
    }

    @GetMapping("/{index}")
    public ResponseEntity<SameValueArray> getSameValueArray(@PathVariable int index) {
        Optional<SameValueArray> sameValueArray = sameValueArrayService.getSameValueArray(index);
        return sameValueArray.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SameValueArray addSameValueArray(@RequestBody SameValueArray sameValueArray) {
        return sameValueArrayService.addSameValueArray(sameValueArray);
    }

    @PutMapping("/{id}")
    public SameValueArray updateSameValueArray(@PathVariable String id, @RequestBody SameValueArray sameValueArray) {
        return sameValueArrayService.updateSameValueArray(id, sameValueArray);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSameValueArray(@PathVariable String id) {
        sameValueArrayService.deleteSameValueArray(id);
        return ResponseEntity.noContent().build();
    }
}
