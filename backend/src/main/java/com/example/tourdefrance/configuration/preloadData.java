package com.example.tourdefrance.configuration;

import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.dao.Team;
import com.example.tourdefrance.utilities.Country;
import com.example.tourdefrance.repositories.RidersRepository;
import com.example.tourdefrance.repositories.TeamsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.tourdefrance.utilities.NumberGenerators.*;

//Klasse til at fylde data ude med indledende data
@Configuration
public class preloadData implements CommandLineRunner {

    TeamsRepository teamsRepository;
    RidersRepository ridersRepository;

    public preloadData(TeamsRepository teamsRepository, RidersRepository ridersRepository) {
        this.teamsRepository = teamsRepository;
        this.ridersRepository = ridersRepository;
    }

    @Override
    public void run(String ...args) throws Exception{

        //'tourDeFranceData.txt' filens indhold er bygget efter kilde reference til de givne cykel teams fra: https://www.procyclingstats.com/race/tour-de-france/2022/startlist
        //Kilde reference til opsætningen inspireret ved : https://stackoverflow.com/questions/10257981/read-text-file-into-an-array
        BufferedReader preparedData = new BufferedReader(new FileReader(Paths.get("src/main/resources/static","tourDeFranceDataCSV.txt").toString()));
        String temporaryString = "";
        List<Team> teamsList = new ArrayList<>();
        int counter = 0;
        while((temporaryString = preparedData.readLine()) != null) {
            if (temporaryString.contains("0")) {
                Team team = new Team();
                team.setTeamName(temporaryString.replace("0", "").replace(";", "").trim());
                System.out.println(team.getTeamName());
                //teamsRepository.save(team);
                teamsList.add(team);
                counter+=1;
            }
            if (!temporaryString.contains("0")) {
                Team team = teamsList.get(counter-1);
                String[] newRiderData = temporaryString.split(";");
                Rider newRider = new Rider();
                newRider.setLastName(newRiderData[0]);
                newRider.setFirstName(newRiderData[1].trim());
                newRider.setCountry(Country.valueOf(newRiderData[2]).getCountryAbbrivation());
                newRider.setTotalTime(randomTotalTimeGenerator());
                newRider.setMountainPoints(randomMountainPointsGenerator());
                newRider.setSprintPoints(randomSprintPointsGenerator());
                newRider.setAge(randomAgeGenerator());
                System.out.println(newRider.getTotalTime());
                System.out.println(newRider.getFirstName() + " " + newRider.getLastName() + " " + newRider.getCountry());
                team.addRider(newRider);
            }
        }
        teamsRepository.saveAll(teamsList);
        while((temporaryString = preparedData.readLine()) != null) {
            if (!temporaryString.contains("0")) {
                String[] newRiderData = temporaryString.split(";");
                Rider newRider = new Rider();
                newRider.setLastName(newRiderData[0]);
                newRider.setFirstName(newRiderData[1].trim());
                newRider.setCountry(Country.valueOf(newRiderData[2]).getCountryAbbrivation());
                newRider.setTotalTime(randomTotalTimeGenerator());
                newRider.setMountainPoints(randomMountainPointsGenerator());
                newRider.setSprintPoints(randomSprintPointsGenerator());
                newRider.setAge(randomAgeGenerator());
                System.out.println(newRider.getTotalTime());
                System.out.println(newRider.getFirstName() + " " + newRider.getLastName() + " " + newRider.getCountry());
                ridersRepository.save(newRider);
            }
        }
        preparedData.close();
    }

}
