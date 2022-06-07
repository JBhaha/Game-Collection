package com.jbhaha.gamecollection.model;

import com.jbhaha.gamecollection.data.DataHandler;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Franchise model class
 */
public class Franchise {

    private String franchiseUUID;

    @FormParam("franchise")
    @NotEmpty
    @Size(min=1, max=40)
    private String franchise;

    @FormParam("genre")
    @NotEmpty
    @Size(min=1, max=40)
    private String genre;

    @FormParam("games")
    @NotEmpty
    @Size(min=1, max=20)
    private Integer games;

    @FormParam("studio")
    @NotEmpty
    private Studio studio;

    @FormParam("gameList")
    @NotEmpty
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
     * gameList getter
     * @return gameList
     */
    public Vector<Game> getGameList() {
        return gameList;
    }

    /**
     * gameList setter
     * @param gameList
     */
    public void setGameList(Vector<Game> gameList) {
        this.gameList = gameList;
    }

    /**
     * gameList setter (using UUIDs)
     * @param gameUUIDs
     */
    public void setGameListUsingUUIDs(Vector<String> gameUUIDs) {
        ListIterator<String> iterator = gameUUIDs.listIterator();
        while (iterator.hasNext()){
            gameList.add(DataHandler.readGameByUUID(iterator.next()));
        }
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

    /**
     * Studio setter (using UUID)
     * @param studioUUID
     */
    public void setStudioUsingUUID(String studioUUID) {
        this.studio = DataHandler.readStudioByUUID(studioUUID);
    }


}
