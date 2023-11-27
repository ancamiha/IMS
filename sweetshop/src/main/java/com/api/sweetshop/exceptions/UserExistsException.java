package com.api.sweetshop.exceptions;

public class UserExistsException extends BakingException {
    private static final long serialVersionUID = -8368249553360028667L;

    public UserExistsException(String message) {
        super(message);
    }
}
