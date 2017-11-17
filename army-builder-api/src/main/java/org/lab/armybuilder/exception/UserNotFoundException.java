package org.lab.armybuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="User not found")
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {}
