package com.jbhaha.gamecollection.model;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Game {

    private String gameUUID;
    private String title;
    private LocalDate release;

    public String getGameUUID() {
        return gameUUID;
    }

    public void setGameUUID(String gameUUID) {
        this.gameUUID = gameUUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LinkedHashMap<String, Integer> release) {
        this.release = LocalDate.of(release.get("year"), release.get("month"), release.get("day"));
    }
}
