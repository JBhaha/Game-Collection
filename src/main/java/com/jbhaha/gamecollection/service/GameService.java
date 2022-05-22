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

@Path("game")
public class GameService {

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

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readGame(
            @QueryParam("uuid") String gameUUID
    ){
        Game game = DataHandler.getInstance().readGameByUUID(gameUUID);
        return Response
                .status(200)
                .entity(game)
                .build();
    }

}
