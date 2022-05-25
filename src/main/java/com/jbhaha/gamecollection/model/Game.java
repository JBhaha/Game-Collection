package com.jbhaha.gamecollection.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

/**
 * Game model class
 */
public class Game {

    private String gameUUID;
    private String title;
    private LocalDate release;

    /**
     * GameUUID getter
     * @return gameUUID
     */
    public String getGameUUID() {
        return gameUUID;
    }

    /**
     * GameUUID setter
     * @param gameUUID
     */
    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }

    /**
     * Title getter
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Title setter
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Release getter
     * @return release
     */
    public LocalDate getRelease() {
        return release;
    }

    /**
     * Release setter
     * @param release
     */
    public void setRelease(LinkedHashMap<String, Integer> release) {
        this.release = LocalDate.of(release.get("year"), release.get("month"), release.get("day"));
    }
}
