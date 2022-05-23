package com.jbhaha.gamecollection.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Studio {

    private String studioUUID;
    private String studio;
    private LocalDate founded;
    private String location;

    public String getStudioUUID() {
        return studioUUID;
    }

    public void setStudioUUID(String studioUUID) {
        this.studioUUID = studioUUID;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LinkedHashMap<String, Integer> founded) {
        this.founded = LocalDate.of(founded.get("year"), founded.get("month"), founded.get("day"));
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
