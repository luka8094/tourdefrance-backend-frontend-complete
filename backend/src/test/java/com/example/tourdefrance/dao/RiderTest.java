package com.example.tourdefrance.dao;

import com.example.tourdefrance.utilities.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RiderTest {

    private Rider testRider;

    @BeforeEach
    void createRider(){
        testRider = new Rider();
    }

    @Test
    void getAndSetId() {
        //AAA
        Long id = 1L;

        testRider.setId(id);

        assertEquals(1L,testRider.getId());
    }


    @Test
    void getAndSetFirstName() {
        //AAA
        String firstNameTest = "Joe";

        testRider.setFirstName(firstNameTest);

        assertEquals(testRider.getFirstName(),"joe");
    }

    @Test
    void getAndSetLastName() {
        //AAA
        String lastNameTest = "lastname";

        testRider.setLastName(lastNameTest);

        assertEquals(testRider.getLastName(),"LASTNAME");
    }

    @Test
    void getAndSetCountry() {
        //AAA
        Country countryTest = Country.valueOf("DENMARK");

        testRider.setCountry(countryTest);

        assertEquals(testRider.getCountry(),Country.DENMARK);
    }

    @Test
    void getAndSetTotalTime() {
        //AAA
        Random randomTimeTest = new Random();
        String randomTimeValue = randomTimeTest.nextInt(2) +":"+ randomTimeTest.nextInt(30)+30 +":"+randomTimeTest.nextInt(60);

        testRider.setTotalTime(randomTimeValue);

        assertEquals(randomTimeValue,testRider.getTotalTime());
    }

    @Test
    void getAndSetMountainPoints() {
        //AAA
        Random randomMountainPtsTest = new Random();
        int randomMountainPtsValue = randomMountainPtsTest.nextInt(1)+9;

        testRider.setMountainPoints(randomMountainPtsValue);

        assertEquals(randomMountainPtsValue,testRider.getMountainPoints());
    }

    @Test
    void getAndSetSprintPoints() {
        //AAA
        Random randomSprintPtsTest = new Random();
        int randomSprintsPtsValue = randomSprintPtsTest.nextInt(1)+9;

        testRider.setSprintPoints(randomSprintsPtsValue);

        assertEquals(randomSprintsPtsValue,testRider.getSprintPoints());
    }


    @Test
    void getAndSetAge() {
        //AAA
        Random randomAgeTest = new Random();
        int randomAgeValue = randomAgeTest.nextInt(20)+20;

        testRider.setAge(randomAgeValue);

        assertEquals(randomAgeValue,testRider.getAge());
    }

    @Test
    void getAndSetTeam() {
        //AAA
        Team teamTest = new Team();
        Long teamId = 1L;

        teamTest.setId(teamId);
        testRider.setTeam(teamTest);

        assertEquals(teamId,testRider.getTeam().getId());
    }
}