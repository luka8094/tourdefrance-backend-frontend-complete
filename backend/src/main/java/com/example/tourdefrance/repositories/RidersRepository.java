package com.example.tourdefrance.repositories;

import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.utilities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RidersRepository extends JpaRepository<Rider, Long> {

    Rider getById(Long id);

    List<Rider> getAllByCountry(String country);

    Rider getRiderByFirstNameAndLastName(String firstName, String lastName);

    void deleteById(long id);

}
