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
 * Game model class
 */
public class Game {

    @FormParam("gameUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String gameUUID;

    @FormParam("title")
    @NotEmpty
    @Size(min = 1, max = 40)
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate release;

    /**
     * GameUUID getter
     *
     * @return gameUUID
     */
    public String getGameUUID() {
        return gameUUID;
    }

    /**
     * GameUUID setter
     *
     * @param gameUUID
     */
    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }

    /**
     * Title getter
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title setter
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Release getter
     *
     * @return release
     */
    public LocalDate getRelease() {
        return release;
    }

    /**
     * Release setter
     *
     * @param release
     */
    public void setRelease(LocalDate release) {
        this.release = release;
    }

    /**
     * Release setter using String instead of LocalDate
     *
     * @param release
     */
    public void setReleaseWithString(String release) {
        this.release = LocalDate.parse(release);
    }
}
