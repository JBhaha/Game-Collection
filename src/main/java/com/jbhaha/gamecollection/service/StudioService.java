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
    public Response listStudios(
            @CookieParam("role") String role
    ) {
        int httpStatus;
        List<Studio> studioMap = null;
        if (role == null || role.equals("guest")){
            httpStatus = 403;
        } else {
            httpStatus = 200;
            studioMap = DataHandler.readAllStudios();
        }


        return Response
                .status(httpStatus)
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
            @QueryParam("uuid") String studioUUID,
            @CookieParam("role") String role
    ){
        Studio studio = null;
        int httpStatus;

        if (role == null || role.equals("guest")){
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
        } else {
            httpStatus = 403;
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
            @QueryParam("uuid") String studioUUID,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            if (!DataHandler.deleteStudio(studioUUID)){
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
    public Response insertStudio(
            @Valid @BeanParam Studio newStudio,
            @FormParam("founded") String founded,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            newStudio.setStudioUUID(UUID.randomUUID().toString());
            newStudio.setFoundedUsingString(founded);
            DataHandler.insertStudio(newStudio);
        } else {
            httpStatus = 403;
        }

        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateStudio(
            @Valid @BeanParam Studio changedStudio,
            @FormParam("founded") String founded,
            @CookieParam("role") String role
    ){
        int httpStatus = 200;
        if (role != null && role.equals("admin")){
            Studio studio = DataHandler.readStudioByUUID(changedStudio.getStudioUUID());
            if (studio != null){
                studio.setStudio(changedStudio.getStudio());
                studio.setFoundedUsingString(founded);
                studio.setLocation(changedStudio.getLocation());

                DataHandler.updateStudio();
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