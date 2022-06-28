package com.example.tourdefrance.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team testTeam;

    @BeforeEach
    void initializeTeam(){
        testTeam = new Team();
    }

    @Test
    void getAndSetId() {
        //AAA
        Long teamId = 1L;

        testTeam.setId(teamId);

        assertEquals(teamId,testTeam.getId());
    }

    @Test
    void getAndSetTeamName() {
        //AAA
        String teamNameTest = "BicycleTeam";

        testTeam.setTeamName(teamNameTest);

        assertEquals(teamNameTest,testTeam.getTeamName());
    }

    @Test
    void getAndSetRiders() {
        List<Rider> riderListTest = new ArrayList<>();
        Rider testRiderOne = new Rider();
        Rider testRiderTwo = new Rider();

        testRiderOne.setFirstName("One");
        testRiderTwo.setFirstName("Two");

        testRiderOne.setTeam(testTeam);
        testRiderTwo.setTeam(testTeam);

        riderListTest.add(testRiderOne);
        riderListTest.add(testRiderTwo);

        testTeam.setRiders(riderListTest);

        assertEquals(2,testTeam.getRiders().size());
        assertEquals("one",testTeam.getRiders().get(0).getFirstName());
        assertEquals("two",testTeam.getRiders().get(1).getFirstName());
    }

    @Test
    void addAndGetRider() {
        Rider newRiderTest = new Rider();

        newRiderTest.setFirstName("Added");
        testTeam.addRider(newRiderTest);

        assertEquals(1,testTeam.getRiders().size());
        assertEquals("added",testTeam.getRiders().get(0).getFirstName());
    }
}