package com.jbhaha.gamecollection.model;

import java.util.Vector;

public class Franchise {

    private String franchiseUUID;
    private String franchise;
    private String genre;
    private Integer games;
    private Studio studio;
    private Vector<Game> gameList;

    public String getFranchiseUUID() {
        return franchiseUUID;
    }

    public void setFranchiseUUID(String franchiseUUID) {
        this.franchiseUUID = franchiseUUID;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getGames() {
        return games;
    }

    public void setGames(Integer games) {
        this.games = games;
    }

    public Vector<Game> getGameList() {
        return gameList;
    }

    public void setGameList(Vector<Game> gameList) {
        this.gameList = gameList;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
