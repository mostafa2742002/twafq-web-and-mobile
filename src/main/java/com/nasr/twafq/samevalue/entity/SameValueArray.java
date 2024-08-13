package com.nasr.twafq.samevalue.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Document(collection = "same_value_arrays")
public class SameValueArray {

    @Id
    @Schema(hidden = true)
    private String id;
    @Schema(hidden = true)
    private int index;
    private List<Integer> values;

    // Constructors, getters, and setters

    public SameValueArray() {}

    public SameValueArray(int index, List<Integer> values) {
        this.index = index;
        this.values = values;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
