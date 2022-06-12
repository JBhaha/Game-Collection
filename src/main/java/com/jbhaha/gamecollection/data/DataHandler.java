package com.jbhaha.gamecollection.data;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jbhaha.gamecollection.model.Game;
import com.jbhaha.gamecollection.model.Franchise;
import com.jbhaha.gamecollection.model.Studio;
import com.jbhaha.gamecollection.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static DataHandler instance;
    private static List<Game> gameList;
    private static List<Franchise> franchiseList;
    private static List<Studio> studioList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setGameList(null);
        DataHandler.setFranchiseList(null);
        DataHandler.setStudioList(null);
    }

    /**
     * reads all games
     *
     * @return list of games
     */
    public static List<Game> readAllGames() {
        return getGameList();
    }

    /**
     * reads a game by its uuid
     *
     * @param gameUUID
     * @return the game (null=not found)
     */
    public static Game readGameByUUID(String gameUUID) {
        Game game = null;
        for (Game entry : getGameList()) {
            if (entry.getGameUUID().equals(gameUUID)) {
                game = entry;
            }
        }
        return game;
    }

    /**
     * inserts a new game into the gameList
     *
     * @param game the game to be saved
     */
    public static void insertGame(Game game) {
        getGameList().add(game);
        writeGameJSON();
    }

    /**
     * updates the gameList
     */
    public static void updateGame() {
        writeGameJSON();
    }

    /**
     * deletes a game identified by the gameUUID
     * @param gameUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteGame(String gameUUID) {
        Game game = readGameByUUID(gameUUID);
        if (game != null) {
            getGameList().remove(game);
            writeGameJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all franchises
     *
     * @return list of franchises
     */
    public static List<Franchise> readAllFranchises() {

        return getFranchiseList();
    }

    /**
     * reads a franchise by its uuid
     *
     * @param franchiseUUID
     * @return the franchise (null=not found)
     */
    public static Franchise readFranchiseByUUID(String franchiseUUID) {
        Franchise franchise = null;
        for (Franchise entry : getFranchiseList()) {
            if (entry.getFranchiseUUID().equals(franchiseUUID)) {
                franchise = entry;
            }
        }
        return franchise;
    }

    /**
     * inserts a new franchise into the franchiseList
     *
     * @param franchise the franchise to be saved
     */
    public static void insertFranchise(Franchise franchise) {
        getFranchiseList().add(franchise);
        writeFranchisesJSON();
    }

    /**
     * updates the franchiseList
     */
    public static void updateFranchise() {
        writeFranchisesJSON();
    }

    /**
     * deletes a franchise identified by the franchiseUUID
     * @param franchiseUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteFranchise(String franchiseUUID) {
        Franchise franchise = readFranchiseByUUID(franchiseUUID);
        if (franchise != null) {
            getFranchiseList().remove(franchise);
            writeFranchisesJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all studios
     *
     * @return list of studios
     */
    public static List<Studio> readAllStudios() {
        return getStudioList();
    }

    /**
     * reads a studio by its uuid
     *
     * @param studioUUID
     * @return the studio (null=not found)
     */
    public static Studio readStudioByUUID(String studioUUID) {
        Studio studio = null;
        for (Studio entry : getStudioList()) {
            if (entry.getStudioUUID().equals(studioUUID)) {
                studio = entry;
            }
        }
        return studio;
    }

    /**
     * inserts a new studio into the studioList
     *
     * @param studio the studio to be saved
     */
    public static void insertStudio(Studio studio) {
        getStudioList().add(studio);
        writeStudioJSON();
    }

    /**
     * updates the studioList
     */
    public static void updateStudio() {
        writeStudioJSON();
    }

    /**
     * deletes a studio identified by the studioUUID
     * @param studioUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteStudio(String studioUUID) {
        Studio studio = readStudioByUUID(studioUUID);
        if (studio != null) {
            getStudioList().remove(studio);
            writeStudioJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the games from the JSON-file
     */
    private static void readGamesJSON() {
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
     * writes the gameList to the JSON-file
     */
    private static void writeGameJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String gamePath = Config.getProperty("gamesJSON");
        try {
            fileOutputStream = new FileOutputStream(gamePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getGameList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the franchises from the JSON-file
     */
    private static void readFranchisesJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("franchisesJSON")
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
     * writes the franchiseList to the JSON-file
     */
    private static void writeFranchisesJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String franchisePath = Config.getProperty("franchisesJSON");
        try {
            fileOutputStream = new FileOutputStream(franchisePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getFranchiseList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the studios from the JSON-file
     */
    private static void readStudiosJSON() {
        try {
            String path = Config.getProperty("studiosJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Studio[] studios = objectMapper.readValue(jsonData, Studio[].class);
            for (Studio studio : studios) {
                getStudioList().add(studio);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the studioList to the JSON-file
     */
    private static void writeStudioJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String studioPath = Config.getProperty("studiosJSON");
        try {
            fileOutputStream = new FileOutputStream(studioPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getStudioList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets gameList
     *
     * @return value of gameList
     */
    private static List<Game> getGameList() {
        if (gameList == null) {
            setGameList(new ArrayList<>());
            readGamesJSON();
        }
        return gameList;
    }

    /**
     * sets gameList
     *
     * @param gameList the value to set
     */
    private static void setGameList(List<Game> gameList) {
        DataHandler.gameList = gameList;
    }

    /**
     * gets franchiseList
     *
     * @return value of franchiseList
     */
    private static List<Franchise> getFranchiseList() {
        if (franchiseList == null) {
            setFranchiseList(new ArrayList<>());
            readFranchisesJSON();
        }
        return franchiseList;
    }

    /**
     * sets franchiseList
     *
     * @param franchiseList the value to set
     */
    private static void setFranchiseList(List<Franchise> franchiseList) {
        DataHandler.franchiseList = franchiseList;
    }

    public static List<Studio> getStudioList() {
        if (studioList == null) {
            setStudioList(new ArrayList<>());
            readStudiosJSON();
        }
        return studioList;
    }

    public static void setStudioList(List<Studio> studioList) {
        DataHandler.studioList = studioList;
    }
}