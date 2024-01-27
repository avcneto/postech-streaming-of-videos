package com.postechvideostreaming.videostreaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceAlreadyUpdatedException extends ResponseStatusException {
    public ResourceAlreadyUpdatedException(String message) {
        super(HttpStatus.CONFLICT, message, null);
    }
}
