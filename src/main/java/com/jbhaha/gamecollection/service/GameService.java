package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Game;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
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
    public Response listGames(
            @CookieParam("role") String role
    ) {
        int httpStatus;
        List<Game> gameMap = null;
        if (role == null || role.equals("guest")){
            httpStatus = 403;
        } else {
            httpStatus = 200;
            gameMap = DataHandler.readAllGames();
        }

        return Response
                .status(httpStatus)
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String gameUUID,
            @CookieParam("role") String role
    ){
        Game game = null;
        int httpStatus;

        if (role == null || role.equals("guest")){
            httpStatus = 403;
        } else{
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String gameUUID,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            if (!DataHandler.deleteGame(gameUUID)){
                httpStatus = 410;
            }
        } else {
            httpStatus = 403;
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
            @Valid @BeanParam Game newGame,
            @FormParam("release") String release,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            newGame.setGameUUID(UUID.randomUUID().toString());
            newGame.setReleaseWithString(release);
            DataHandler.insertGame(newGame);
        } else {
            httpStatus = 403;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateGame(
            @Valid @BeanParam Game changedGame,
            @FormParam("release") String release,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            Game game = DataHandler.readGameByUUID(changedGame.getGameUUID());
            if (game != null){
                game.setTitle(changedGame.getTitle());
                game.setReleaseWithString(release);
                DataHandler.updateGame();
            } else{
                httpStatus = 410;
            }
        } else {
            httpStatus = 403;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
