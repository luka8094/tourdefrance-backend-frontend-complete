package com.example.tourdefrance.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Exception til at håndtere et ikke-eksisterende cykelhold i databasen
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException{

    public TeamNotFoundException() {
        super("Resource: Team was not found.");
    }
}
