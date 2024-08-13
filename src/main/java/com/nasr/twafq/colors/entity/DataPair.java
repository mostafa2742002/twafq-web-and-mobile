package com.nasr.twafq.colors.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data_pairs")
public class DataPair {

    @Id
    private String id;
    private int key1;
    private int key2;
    private int value;

    // Constructors, getters, and setters

    public DataPair() {}

    public DataPair(int key1, int key2, int value) {
        this.key1 = key1;
        this.key2 = key2;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKey1() {
        return key1;
    }

    public void setKey1(int key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
