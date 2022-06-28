package com.example.tourdefrance.utilities;

import java.util.Random;

public class NumberGenerators {

    public static String randomTotalTimeGenerator(){
        Random timeRandomizer = new Random();
        return timeRandomizer.nextInt(2)+":"+ (timeRandomizer.nextInt(30)+30)+":"+ (timeRandomizer.nextInt(60));
    }

    //Hjælpemetode til at generere tilfældig alder på en rytter
    //kilde reference til rytterne's gennemsnitlige alder: https://www.procyclingstats.com/race/tour-de-france/2020/gc/startlist/riders-per-age
    public static int randomAgeGenerator(){
        Random ageRandomizer = new Random();
        return ageRandomizer.nextInt(20)+20;
    }

    //Hjælpemetode til at generere et tilfældigt antal 'bjergpoint' per rytter
    public static int randomMountainPointsGenerator(){
        Random mPointsRandomizer = new Random();
        return mPointsRandomizer.nextInt(1)+9;
    }

    //Hjælpemetode til at generere et tilfældigt antal 'spurtpoint' per rytter
    public static int randomSprintPointsGenerator(){
        Random sPointsRandomizer = new Random();
        return sPointsRandomizer.nextInt(1)+9;
    }
}
