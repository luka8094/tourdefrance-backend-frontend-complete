package com.example.tourdefrance.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exception til at håndtere en ikke-eksisterende cykelrytter i databasen
@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class RiderNotFoundException extends RuntimeException{

    public RiderNotFoundException() {
        super("Resource: Rider was not found.");
    }
}
