package com.jbhaha.gamecollection.model;

import java.util.Vector;

/**
 * Franchise model class
 */
public class Franchise {

    private String franchiseUUID;
    private String franchise;
    private String genre;
    private Integer games;
    private Studio studio;
    private Vector<Game> gameList;

    /**
     * FranchiseUUID getter
     * @return franchiseUUID
     */
    public String getFranchiseUUID() {
        return franchiseUUID;
    }

    /**
     * FranchiseUUID setter
     * @param franchiseUUID
     */
    public void setFranchiseUUID(String franchiseUUID) {
        this.franchiseUUID = franchiseUUID;
    }

    /**
     * Franchise getter
     * @return franchise
     */
    public String getFranchise() {
        return franchise;
    }

    /**
     * Franchise setter
     * @param franchise
     */
    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    /**
     * Genre getter
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Genre setter
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Games getter
     * @return games
     */
    public Integer getGames() {
        return games;
    }

    /**
     * Games setter
     * @param games
     */
    public void setGames(Integer games) {
        this.games = games;
    }

    /**
     * Gamelist getter
     * @return gameList
     */
    public Vector<Game> getGameList() {
        return gameList;
    }

    /**
     * Gamelist setter
     * @param gameList
     */
    public void setGameList(Vector<Game> gameList) {
        this.gameList = gameList;
    }

    /**
     * Studio getter
     * @return studio
     */
    public Studio getStudio() {
        return studio;
    }

    /**
     * Studio setter
     * @param studio
     */
    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
