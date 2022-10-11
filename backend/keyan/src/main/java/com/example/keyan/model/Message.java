package com.example.keyan.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private long pid;
    private long pfaculty;
    private LocalDateTime ptime;
    private String pcontext;
    private String annex;
}
