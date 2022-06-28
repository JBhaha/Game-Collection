package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Franchise;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * FranchiseService class
 */
@Path("franchise")
public class FranchiseService {

    /**
     * list with all franchises
     * @return response with list of franchises in json format
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFranchises(
            @CookieParam("role") String role
    ) {

        int httpStatus;
        List<Franchise> franchiseMap = null;

        if (role == null || role.equals("guest")){
            httpStatus = 403;
        } else {
            httpStatus = 200;
            franchiseMap = DataHandler.readAllFranchises();
        }

        return Response
                .status(httpStatus)
                .entity(franchiseMap)
                .build();
    }

    /**
     * shows one franchise with the specified UUID
     * @param franchiseUUID
     * @return response with one franchise in json format
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFranchise(
            @QueryParam("uuid") String franchiseUUID,
            @CookieParam("role") String role
    ){
        Franchise franchise = null;
        int httpStatus;

        if (role == null || role.equals("guest")){
            httpStatus = 403;
        } else {
            try {
                franchise = DataHandler.readFranchiseByUUID(franchiseUUID);
                if (franchise == null){
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
                .entity(franchise)
                .build();
    }

    /**
     * deletes one franchise
     * @param franchiseUUID
     * @param role
     * @return response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFranchise(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String franchiseUUID,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            if (!DataHandler.deleteFranchise(franchiseUUID)){
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

    /**
     * creates a new franchise
     * @param newFranchise
     * @param studioUUID
     * @param role
     * @return response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertFranchise(
            @Valid @BeanParam Franchise newFranchise,
            @FormParam("studioUUID") String studioUUID,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            newFranchise.setFranchiseUUID(UUID.randomUUID().toString());
            newFranchise.setStudioUUID(studioUUID);
            DataHandler.insertFranchise(newFranchise);
        } else {
            httpStatus = 403;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * changes an already existing franchise
     * @param changedFranchise
     * @param role
     * @return response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateFranchise(
            @Valid @BeanParam Franchise changedFranchise,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            Franchise franchise = DataHandler.readFranchiseByUUID(changedFranchise.getFranchiseUUID());
            if (franchise != null){
                franchise.setFranchise(changedFranchise.getFranchise());
                franchise.setGenre(changedFranchise.getGenre());
                franchise.setGames(changedFranchise.getGames());
                franchise.setStudio(changedFranchise.getStudio());
                franchise.setGameList(changedFranchise.getGameList());

                DataHandler.updateFranchise();
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
