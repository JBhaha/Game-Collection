package com.jbhaha.gamecollection.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

/**
 * Studio model class
 */
public class Studio {

    private String studioUUID;
    private String studio;
    private LocalDate founded;
    private String location;

    /**
     * StudioUUID getter
     * @return studioUUID
     */
    public String getStudioUUID() {
        return studioUUID;
    }

    /**
     * StudioUUID setter
     * @param studioUUID
     */
    public void setStudioUUID(String studioUUID) {
        this.studioUUID = studioUUID;
    }

    /**
     * Studio getter
     * @return studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * Studio setter
     * @param studio
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * Founded getter
     * @return founded
     */
    public LocalDate getFounded() {
        return founded;
    }

    /**
     * Founded setter
     * @param founded
     */
    public void setFounded(LinkedHashMap<String, Integer> founded) {
        this.founded = LocalDate.of(founded.get("year"), founded.get("month"), founded.get("day"));
    }

    /**
     * Location getter
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Location setter
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
