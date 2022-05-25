package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Game;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

        List<Game> gameMap = DataHandler.getInstance().readAllGames();

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
            game = DataHandler.getInstance().readGameByUUID(gameUUID);
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

}
