package com.example.Maxouille.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "roomDataEntityApi",
        version = "v1",
        resource = "roomDataEntity",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Maxouille.example.com",
                ownerName = "backend.myapplication.Maxouille.example.com",
                packagePath = ""
        )
)
public class RoomDataEntityEndpoint {

    private static final Logger logger = Logger.getLogger(RoomDataEntityEndpoint.class.getName());

    /**
     * This method gets the <code>RoomDataEntity</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>RoomDataEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getRoomDataEntity")
    public RoomDataEntity getRoomDataEntity(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getRoomDataEntity method");
        return null;
    }

    /**
     * This inserts a new <code>RoomDataEntity</code> object.
     *
     * @param roomDataEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertRoomDataEntity")
    public RoomDataEntity insertRoomDataEntity(RoomDataEntity roomDataEntity) {
        // TODO: Implement this function
        logger.info("Calling insertRoomDataEntity method");
        return roomDataEntity;
    }
}