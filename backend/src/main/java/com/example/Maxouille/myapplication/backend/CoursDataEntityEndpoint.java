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
        name = "coursDataEntityApi",
        version = "v1",
        resource = "coursDataEntity",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Maxouille.example.com",
                ownerName = "backend.myapplication.Maxouille.example.com",
                packagePath = ""
        )
)
public class CoursDataEntityEndpoint {

    private static final Logger logger = Logger.getLogger(CoursDataEntityEndpoint.class.getName());

    /**
     * This method gets the <code>CoursDataEntity</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>CoursDataEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getCoursDataEntity")
    public CoursDataEntity getCoursDataEntity(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getCoursDataEntity method");
        return null;
    }

    /**
     * This inserts a new <code>CoursDataEntity</code> object.
     *
     * @param coursDataEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertCoursDataEntity")
    public CoursDataEntity insertCoursDataEntity(CoursDataEntity coursDataEntity) {
        // TODO: Implement this function
        logger.info("Calling insertCoursDataEntity method");
        return coursDataEntity;
    }
}