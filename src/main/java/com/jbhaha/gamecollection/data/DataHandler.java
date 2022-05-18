package com.jbhaha.gamecollection.data;

import com.jbhaha.gamecollection.model.Game;
import com.jbhaha.gamecollection.model.Franchise;
import com.jbhaha.gamecollection.model.Studio;
import com.jbhaha.gamecollection.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Game> gameList;
    private List<Franchise> franchiseList;
    private List<Studio> studioList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setGameList(new ArrayList<>());
        readGamesJSON();
        setFranchiseList(new ArrayList<>());
        readFranchiseJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all games
     * @return list of games
     */
    public List<Game> readAllGames() {
        return getGameList();
    }

    /**
     * reads a game by its uuid
     * @param gameUUID
     * @return the game (null=not found)
     */
    public Game readGameByUUID(String gameUUID) {
        Game game = null;
        for (Game entry : getGameList()) {
            if (entry.getGameUUID().equals(gameUUID)) {
                game = entry;
            }
        }
        return game;
    }

    /**
     * reads all franchises
     * @return list of franchises
     */
    public List<Franchise> readAllFranchises() {

        return getFranchiseList();
    }

    /**
     * reads a franchise by its uuid
     * @param franchiseUUID
     * @return the franchise (null=not found)
     */
    public Franchise readPublisherByUUID(String franchiseUUID) {
        Franchise franchise = null;
        for (Franchise entry : getFranchiseList()) {
            if (entry.getFranchiseUUID().equals(franchiseUUID)) {
                franchise = entry;
            }
        }
        return franchise;
    }

    /**
     * reads the games from the JSON-file
     */
    private void readGamesJSON() {
        try {
            String path = Config.getProperty("gamesJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Game[] games = objectMapper.readValue(jsonData, Game[].class);
            for (Game game : games) {
                getGameList().add(game);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the franchises from the JSON-file
     */
    private void readFranchiseJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("franchiseJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Franchise[] franchises = objectMapper.readValue(jsonData, Franchise[].class);
            for (Franchise franchise : franchises) {
                getFranchiseList().add(franchise);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * gets gameList
     *
     * @return value of bookList
     */
    private List<Game> getGameList() {
        return gameList;
    }

    /**
     * sets gameList
     *
     * @param gameList the value to set
     */
    private void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    /**
     * gets franchiseList
     *
     * @return value of franchiseList
     */
    private List<Franchise> getFranchiseList() {
        return franchiseList;
    }

    /**
     * sets franchiseList
     *
     * @param franchiseList the value to set
     */
    private void setFranchiseList(List<Franchise> franchiseList) {
        this.franchiseList = franchiseList;
    }


}