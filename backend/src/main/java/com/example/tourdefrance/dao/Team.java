package com.example.tourdefrance.dao;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //Et enkelt team's pågældende navn
    @Column(nullable = false)
    String teamName;

    //List over ryttere (riders) tilhørende det enkelte hold
    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Rider> riders = new ArrayList<>();

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Rider> getRiders() {
        return this.riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders.addAll(riders);
        riders.forEach( rider -> rider.setTeam(this));
    }

    public void addRider(Rider rider){
        this.riders.add(rider);
        rider.setTeam(this);
    }

}
