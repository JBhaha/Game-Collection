package com.jbhaha.gamecollection.service;

import com.jbhaha.gamecollection.data.DataHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * test service
 */
@Path("test")
public class TestService {

    /**
     * test
     * @return response with string
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }

    /**
     * restores the json-files
     * @return Response
     */
    @GET
    @Path("restore")
    @Produces(MediaType.TEXT_PLAIN)
    public Response restore() {
        try {
            java.nio.file.Path path = Paths.get(Config.getProperty("gamesJSON"));
            String filename = path.getFileName().toString();
            String folder = path.getParent().toString();

            byte[] gamesJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            FileOutputStream fileOutputStream = new FileOutputStream(Config.getProperty("gamesJSON"));
            fileOutputStream.write(gamesJSON);

            path = Paths.get(Config.getProperty("franchisesJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] franchisesJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("franchisesJSON"));
            fileOutputStream.write(franchisesJSON);

            path = Paths.get(Config.getProperty("studiosJSON"));
            filename = path.getFileName().toString();
            folder = path.getParent().toString();

            byte[] studiosJSON = Files.readAllBytes(Paths.get(folder, "backup", filename));
            fileOutputStream = new FileOutputStream(Config.getProperty("studiosJSON"));
            fileOutputStream.write(studiosJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }

        DataHandler.initLists();
        return Response
                .status(200)
                .entity("Erfolgreich")
                .build();
    }

}

