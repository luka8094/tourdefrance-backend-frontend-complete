package com.example.tourdefrance.controllers;


import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.dao.Team;
import com.example.tourdefrance.exceptions.RiderNotFoundException;
import com.example.tourdefrance.exceptions.TeamNotFoundException;
import com.example.tourdefrance.repositories.RidersRepository;
import com.example.tourdefrance.repositories.TeamsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


//REST controller til håndtering af cykelhold ('teams')
@RestController
//@CrossOrigin(origins = {"http://localhost:5000","http://localhost:3000"})
@CrossOrigin(origins = "*")
@RequestMapping("/api/teams")
public class TeamsRestController {

    TeamsRepository teamsRepository;
    RidersRepository ridersRepository;

    public TeamsRestController(TeamsRepository teamsRepository, RidersRepository ridersRepository) {
        this.teamsRepository = teamsRepository;
        this.ridersRepository = ridersRepository;
    }

    //Constructor injection af teamsRepository


    //Find alle cykelhold i 'teams' tabellen
    @GetMapping
    ResponseEntity<List<Team>> getAllTeams(){
        List<Team> allTeams = teamsRepository.findAll();

        return new ResponseEntity<>(allTeams, HttpStatus.OK);
    }

    //Find ét bestemt cykelhold i 'teams' tabellen
    @GetMapping("/{id}")
    ResponseEntity<Team> getOneTeam(@PathVariable("id") Long id){
        Optional<Team> findTeam = Optional.ofNullable(teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new));

        if(teamsRepository.findById(id).isPresent()){
            Team oneTeam = teamsRepository.getById(id);
            return new ResponseEntity<>(oneTeam, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Få fat i én bestemt rytter fra et cykelhold
    @GetMapping("/{team_id}/rider/{rider_id}")
    ResponseEntity<Rider> getRider(@PathVariable("team_id") long team_id,
                                   @PathVariable("rider_id") long rider_id){
        Optional<Team> findTeam = Optional.of(teamsRepository.findById(team_id)).orElseThrow(TeamNotFoundException::new);
        Optional<Rider> findRider = Optional.of(ridersRepository.findById(rider_id)).orElseThrow(RiderNotFoundException::new);

        if(findTeam.isPresent() && findRider.isPresent()){
            Team getTeam = teamsRepository.getById(team_id);
            List<Rider> getRiders = getTeam.getRiders();
            for (Rider rider: getRiders) {
                if(rider.getId() == rider_id) return new ResponseEntity<>(rider,HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Opret et nyt cykelhold (team)
    @PostMapping
    ResponseEntity<Team> creatTeam(@RequestBody Team team){
        System.out.println(team);
        if(team!=null) {
            Team newTeam = new Team();
            newTeam.setTeamName(team.getTeamName());
            teamsRepository.save(newTeam);
            return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{team_id}/rider/{rider_id}")
    ResponseEntity<Rider> updateRider(@PathVariable("team_id") long team_id,
                                      @PathVariable("rider_id") long rider_id,
                                      @RequestBody Rider rider) {
        Optional<Team> findTeam = Optional.of(teamsRepository.findById(team_id)).orElseThrow(TeamNotFoundException::new);
        Optional<Rider> deleteRider = Optional.of(ridersRepository.findById(rider_id)).orElseThrow(RiderNotFoundException::new);
        if (findTeam.isPresent() && deleteRider.isPresent()) {
            Team getTeam = teamsRepository.getById(team_id);
            Rider storedRider = ridersRepository.getById(rider_id);

            storedRider.setFirstName(rider.getFirstName());
            storedRider.setLastName(rider.getLastName());
            storedRider.setCountry(rider.getCountry());
            storedRider.setTotalTime(rider.getTotalTime());
            storedRider.setMountainPoints(rider.getMountainPoints());
            storedRider.setSprintPoints(rider.getSprintPoints());
            storedRider.setAge(rider.getAge());

            getTeam.getRiders().forEach( teamRider -> { if(Objects.equals(teamRider.getId(), storedRider.getId())){
                    teamRider.setFirstName(rider.getFirstName());
                    teamRider.setLastName(rider.getLastName());
                    teamRider.setCountry(rider.getCountry());
                    teamRider.setTotalTime(rider.getTotalTime());
                    teamRider.setMountainPoints(rider.getMountainPoints());
                    teamRider.setSprintPoints(rider.getSprintPoints());
                    teamRider.setAge(rider.getAge());
            }
            });

            ridersRepository.save(storedRider);
            teamsRepository.save(getTeam);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Slet et bestemt hold
    @DeleteMapping("/{id}")
    ResponseEntity<Team> deleteTeam(@PathVariable("id") long id){
        Optional<Team> findTeam = Optional.ofNullable(teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new));

        if(findTeam.isPresent()) {
            System.out.println(id);
            Rider storedRider = ridersRepository.getById(id);
            ridersRepository.delete(storedRider);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Slet én bestemt rytter fra et eksisterende cykelhold
    @DeleteMapping("/{team_id}/rider/{rider_id}")
    ResponseEntity<Rider> deleteRider(@PathVariable("team_id") long team_id,
                                      @PathVariable("rider_id") long rider_id){
        Optional<Team> findTeam = Optional.of(teamsRepository.findById(team_id)).orElseThrow(TeamNotFoundException::new);
        Optional<Rider> deleteRider = Optional.of(ridersRepository.findById(rider_id)).orElseThrow(RiderNotFoundException::new);

        if(findTeam.isPresent() && deleteRider.isPresent()){
            Team getTeam = teamsRepository.getById(team_id);
            Rider getRider = ridersRepository.getById(rider_id);
            getTeam.getRiders().removeIf( rider -> Objects.equals(rider.getId(), getRider.getId()));
            ridersRepository.delete(getRider);
            teamsRepository.save(getTeam);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
