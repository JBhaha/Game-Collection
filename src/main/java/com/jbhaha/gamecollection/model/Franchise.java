package com.jbhaha.gamecollection.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jbhaha.gamecollection.data.DataHandler;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Franchise model class
 */
public class Franchise {

    @FormParam("franchiseUUID")
    @Pattern(regexp= "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
    @Range(min=1, max=20)
    private Integer games;

    @JsonIgnore
    private Studio studio;

    @JsonIgnore
    private Vector<Game> gameList = new Vector<>();


    public void setStudioUUID(String studioUUID){
        setStudio(new Studio());
        Studio studio = DataHandler.readStudioByUUID(studioUUID);
        this.studio = studio;
    }

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
    public void setGameUUIDList(ArrayList<String> gameUUIDs) {
        ListIterator<String> iterator = gameUUIDs.listIterator();
        while (iterator.hasNext()){
            gameList.add(DataHandler.readGameByUUID(iterator.next()));
        }
    }

    public Vector<String> getGameUUIDList(){
        Vector<String> gameUUIDList = new Vector<>();
        ListIterator<Game> iterator = gameList.listIterator();
        while (iterator.hasNext()){
            String gameUUID = iterator.next().getGameUUID();
            gameUUIDList.add(gameUUID);
        }
        return gameUUIDList;
    }

    /**public void setGameUUIDListWithList(Vector<String> gameUUIDList){
        Vector<String> arrayList = new Vector<>();
        arrayList.addAll(gameUUIDList);
        setGameListUsingUUIDs(arrayList);
    }*/

    public String getStudioUUID(){
        if (getStudio()== null) return null;
        return getStudio().getStudioUUID();
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
