package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Franchise;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

}
