package com.example.tourdefrance.repositories;

import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Long> {

    Team getById(Long id);

    Team getByTeamName(String teamName);

}
