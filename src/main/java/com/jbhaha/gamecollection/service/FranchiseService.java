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

@Path("franchise")
public class FranchiseService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFranchises() {

        List<Franchise> franchiseMap = DataHandler.getInstance().readAllFranchises();

        return Response
                .status(200)
                .entity(franchiseMap)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readFranchise(
            @QueryParam("uuid") String franchiseUUID
    ){
        Franchise franchise = DataHandler.getInstance().readFranchiseByUUID(franchiseUUID);
        return Response
                .status(200)
                .entity(franchise)
                .build();
    }

}
