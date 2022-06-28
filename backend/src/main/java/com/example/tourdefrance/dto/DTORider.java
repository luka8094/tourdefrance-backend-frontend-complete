package com.example.tourdefrance.dto;

import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.dao.Team;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
//Data Transfer Objekt (DTO) til Rider instanser
public class DTORider {

    Team team;

    public DTORider() {}

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
