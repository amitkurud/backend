package com.amitkurud.backendcommon.aspects.errorhandling;

import java.net.URI;

public class UserNotFoundException extends BadRequestAlertException {
    public UserNotFoundException(String defaultMessage, String entityName, String errorKey) {
        super(defaultMessage, entityName, errorKey);
    }

    public UserNotFoundException(URI type, String defaultMessage, String entityName, String errorKey) {
        super(type, defaultMessage, entityName, errorKey);
    }
}
