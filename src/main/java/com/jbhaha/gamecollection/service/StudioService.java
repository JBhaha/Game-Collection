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

/**
 * StudioService class
 */
@Path("studio")
public class StudioService {

    /**
     * list with all studios
     * @return response with list of studios in json format
     */
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

    /**
     * shows one studio with the specified UUID
     * @param studioUUID
     * @return response with one studio in json format
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readStudio(
            @QueryParam("uuid") String studioUUID
    ){
        Studio studio = null;
        int httpStatus;

        try {
            studio = DataHandler.getInstance().readStudioByUUID(studioUUID);
            if (studio == null){
                httpStatus = 404;
            } else {
                httpStatus = 200;
            }
        } catch (IllegalArgumentException argEx){
            httpStatus = 400;
        }

        return Response
                .status(httpStatus)
                .entity(studio)
                .build();
    }

}
