package com.example.keyan.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Message {
    private long pid;
    private long pfaculty;
    private Timestamp ptime;
    private String pcontext;
    private String annex;
}
