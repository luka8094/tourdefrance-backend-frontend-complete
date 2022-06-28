package com.example.tourdefrance.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

//Klasse til at enkapsle en énkelt rytter fra et hold
@Entity
@Table(name = "riders")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // fornavn : små bogstaver (lower case)
    @Column(nullable = false)
    String firstName;

    // efternavn : KAPITAL BOGSTAVER (upper case)
    @Column(nullable = false)
    String lastName;

    // rytteren's land

    @Column(name="country",nullable = true)
    String country;

    // den enkelte rytter's samlet sprint tid
    String totalTime;

    //Bjergpoint
    @Column(nullable = true)
    int mountainPoints;

    @Column(nullable = true)
    //Sprint point
    int sprintPoints;

    @Column(nullable = true)
    //Rytteren's alder
    int age;

    //Flere ryttere kan knyttes til et hold (team)
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Rider(){ }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        //formartere efternavn (lastname) til lower case for sikkerhedskyld
        this.firstName = firstName.toLowerCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        //formartere efternavn (lastname) til upper case for sikkerhedskyld
        this.lastName = lastName.toUpperCase();
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotalTime() { return totalTime; }

    public void setTotalTime(String totalPoints) {
        this.totalTime = totalPoints;
    }

    public int getMountainPoints() {
        return mountainPoints;
    }

    public void setMountainPoints(int mountainPoints) {
        this.mountainPoints = mountainPoints;
    }

    public int getSprintPoints() {
        return sprintPoints;
    }

    public void setSprintPoints(int sprintPoints) {
        this.sprintPoints = sprintPoints;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
