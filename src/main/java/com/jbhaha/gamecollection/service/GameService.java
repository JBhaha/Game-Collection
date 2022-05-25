package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * GameService class
 */
@Path("game")
public class GameService {

    /**
     * lists all games
     * @return response with list of games in json format
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGames() {

        List<Game> gameMap = DataHandler.readAllGames();

        return Response
                .status(200)
                .entity(gameMap)
                .build();
    }

    /**
     * shows one game with the specified UUID
     * @param gameUUID
     * @return response with one game in json format
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGame(
            @QueryParam("uuid") String gameUUID
    ){
        Game game = null;
        int httpStatus;

        try {
            game = DataHandler.readGameByUUID(gameUUID);
            if (game == null){
                httpStatus = 404;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx){
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .entity(game)
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteGame(
            @QueryParam("uuid") String gameUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteGame(gameUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertBook(
            @FormParam("title") String title,
            @FormParam("release") String release
    ){
        Game game = new Game();
        game.setGameUUID(UUID.randomUUID().toString());
        game.setTitle(title);
        game.setRelease(release);

        DataHandler.insertGame(game);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGame(
            @FormParam("gameUUID") String gameUUID,
            @FormParam("title") String title,
            @FormParam("release") String release
    ){
        int httpStatus = 200;
        Game game = DataHandler.readGameByUUID(gameUUID);
        if (game != null){
            game.setTitle(title);
            game.setRelease(release);


            DataHandler.updateGame();
        } else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
