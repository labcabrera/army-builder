package org.lab.armybuilder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Password is Invalid")
@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException {}
