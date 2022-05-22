package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Franchise;
import com.jbhaha.gamecollection.model.Studio;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("studio")
public class StudioService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listStudios() {

        List<Studio> studioMap = DataHandler.getInstance().readAllStudios();

        return Response
                .status(200)
                .entity(studioMap)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStudio(
            @QueryParam("uuid") String studioUUID
    ){
        Studio studio = DataHandler.getInstance().readStudioByUUID(studioUUID);
        return Response
                .status(200)
                .entity(studio)
                .build();
    }

}
