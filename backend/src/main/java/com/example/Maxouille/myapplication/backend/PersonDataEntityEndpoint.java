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
        name = "personDataEntityApi",
        version = "v1",
        resource = "personDataEntity",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Maxouille.example.com",
                ownerName = "backend.myapplication.Maxouille.example.com",
                packagePath = ""
        )
)
public class PersonDataEntityEndpoint {

    private static final Logger logger = Logger.getLogger(PersonDataEntityEndpoint.class.getName());

    /**
     * This method gets the <code>PersonDataEntity</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>PersonDataEntity</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getPersonDataEntity")
    public PersonDataEntity getPersonDataEntity(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getPersonDataEntity method");
        return null;
    }

    /**
     * This inserts a new <code>PersonDataEntity</code> object.
     *
     * @param personDataEntity The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertPersonDataEntity")
    public PersonDataEntity insertPersonDataEntity(PersonDataEntity personDataEntity) {
        // TODO: Implement this function
        logger.info("Calling insertPersonDataEntity method");
        return personDataEntity;
    }
}