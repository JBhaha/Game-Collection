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
    public Response listFranchises() {

        List<Franchise> franchiseMap = DataHandler.readAllFranchises();

        return Response
                .status(200)
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
            @QueryParam("uuid") String franchiseUUID
    ){
        Franchise franchise = null;
        int httpStatus;

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

        return Response
                .status(httpStatus)
                .entity(franchise)
                .build();
    }

    /**
     * deletes a franchise
     * @param franchiseUUID
     * @return response with httpStatus
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteFranchise(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String franchiseUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteFranchise(franchiseUUID)){
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * adds new franchise
     * @param newFranchise
     * @param studioUUID
     * @return response with httpStatus
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertFranchise(
            @Valid @BeanParam Franchise newFranchise,
            @FormParam("studioUUID") String studioUUID
    ){
        newFranchise.setFranchiseUUID(UUID.randomUUID().toString());
        newFranchise.setStudioUUID(studioUUID);
        DataHandler.insertFranchise(newFranchise);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * changes a franchise
     * @param changedFranchise
     * @return response with httpStatus
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateFranchise(
            @Valid @BeanParam Franchise changedFranchise
    ){
        int httpStatus = 200;
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
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
