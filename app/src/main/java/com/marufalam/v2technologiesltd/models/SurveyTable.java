package com.marufalam.v2technologiesltd.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SurveyTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question;
    private String referTo;
    private String type;
    private boolean required;
}
