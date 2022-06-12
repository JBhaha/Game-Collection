package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;
import com.jbhaha.gamecollection.model.Studio;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

        List<Studio> studioMap = DataHandler.readAllStudios();

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
            studio = DataHandler.readStudioByUUID(studioUUID);
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

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteStudio(
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String studioUUID
    ){
        int httpStatus = 200;
        if (!DataHandler.deleteStudio(studioUUID)){
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
    public Response insertStudio(
            @Valid @BeanParam Studio newStudio,
            @FormParam("founded") String founded
    ){
        newStudio.setStudioUUID(UUID.randomUUID().toString());
        newStudio.setFoundedUsingString(founded);
        DataHandler.insertStudio(newStudio);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStudio(
            @Valid @BeanParam Studio changedStudio
    ){
        int httpStatus = 200;
        Studio studio = DataHandler.readStudioByUUID(changedStudio.getStudioUUID());
        if (studio != null){
            studio.setStudio(changedStudio.getStudio());
            //TODO: Make founded (LocalDate) work
            //studio.setFoundedUsingString(changedStudio.getFounded().toString());
            studio.setLocation(changedStudio.getLocation());


            DataHandler.updateStudio();
        } else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}