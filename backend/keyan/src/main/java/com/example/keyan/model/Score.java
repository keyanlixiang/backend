package com.example.keyan.model;

import lombok.Data;

@Data
public class Score {
    private long sno;
    private long cno;
    private double score;
    private String grade;
    private String term;
}
