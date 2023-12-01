package com.example.parser.domain;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

public class Chapter {



    private int id;
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private List<String> strings = new LinkedList<>();

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Chapter> getSubchapters() {
        return subchapters;
    }

    public void setSubchapters(List<Chapter> subchapters) {
        this.subchapters = subchapters;
    }

    private List<Chapter> subchapters = new LinkedList<>();

    public Chapter (int id, String name, List<String> strings, List<Chapter> subchapters){
        this.id = id;
        this.name = name;
        this.strings = strings;
        this.subchapters = subchapters;
    }
}
