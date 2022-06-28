package com.example.tourdefrance.dto;

import com.example.tourdefrance.dao.Rider;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOTeam {

    int teamNumber;
    List<Rider> riders;

    public DTOTeam(int teamNumber) {
        this.teamNumber = teamNumber;
        riders = new ArrayList<>();
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void addRider(Rider rider){
        riders.add(rider);
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }

}
