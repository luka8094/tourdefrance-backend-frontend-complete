package com.example.tourdefrance.services;

import com.example.tourdefrance.dao.Team;
import com.example.tourdefrance.exceptions.TeamNotFoundException;
import com.example.tourdefrance.repositories.TeamsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class teamsService {

    TeamsRepository teamsRepository;

    public teamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    List<Team> getAllTeams(){
        return teamsRepository.findAll();
    }

    //Find ét bestemt cykelhold i 'teams' tabellen
    Team getOneTeam(Long id){
        Optional<Team> findTeam = Optional.ofNullable(teamsRepository.findById(id).orElseThrow(TeamNotFoundException::new));
        if(teamsRepository.findById(id).isPresent()){
            return teamsRepository.getById(id);
        }
        return null;
    }

}
