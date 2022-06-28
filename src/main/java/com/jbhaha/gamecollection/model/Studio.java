package com.jbhaha.gamecollection.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jbhaha.gamecollection.util.LocalDateDeserializer;
import com.jbhaha.gamecollection.util.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.time.LocalDate;

/**
 * Studio model class
 */
public class Studio {

    @FormParam("studioUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String studioUUID;

    @FormParam("studio")
    @NotEmpty
    @Size(min=1, max=40)
    private String studio;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate founded;

    @FormParam("location")
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z]+, [a-zA-Z]+, [a-zA-Z]+([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))?")
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
    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    /**
     * Founded setter using String
     * @param founded
     */
    public void setFoundedUsingString(String founded){
        this.founded = LocalDate.parse(founded);
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
