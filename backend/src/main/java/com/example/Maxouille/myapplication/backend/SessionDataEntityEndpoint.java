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
        name = "sessionDataEntityApi",
        version = "v1",
        resource = "sessionDataEntity",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Maxouille.example.com",
                ownerName = "backend.myapplication.Maxouille.example.com",
                packagePath = ""
        )
)
public class SessionDataEntityEndpoint {

    private static final Logger logger = Logger.getLogger(SessionDataEntityEndpoint.class.getName());

    /**
     * This method gets the <code>SessionDataEntity</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>SessionDataEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getSessionDataEntity")
    public SessionDataEntity getSessionDataEntity(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getSessionDataEntity method");
        return null;
    }

    /**
     * This inserts a new <code>SessionDataEntity</code> object.
     *
     * @param sessionDataEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertSessionDataEntity")
    public SessionDataEntity insertSessionDataEntity(SessionDataEntity sessionDataEntity) {
        // TODO: Implement this function
        logger.info("Calling insertSessionDataEntity method");
        return sessionDataEntity;
    }
}