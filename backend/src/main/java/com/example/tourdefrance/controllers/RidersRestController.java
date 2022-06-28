package com.example.tourdefrance.controllers;


import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.exceptions.RiderNotFoundException;
import com.example.tourdefrance.repositories.RidersRepository;
import com.example.tourdefrance.utilities.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//REST controller til håndtering af ryttere ('riders')
@RestController
//@CrossOrigin(origins = {"http://localhost:5000","http://localhost:3000"})
@CrossOrigin(origins = {"*","http://localhost:5000"})
@RequestMapping("/api/riders")
public class RidersRestController {

    RidersRepository ridersRepository;

    //Constructor injection af ridersRepository
    public RidersRestController(RidersRepository ridersRepository) {
        this.ridersRepository = ridersRepository;
    }

    //Find alle ryttere i 'riders' tabellen
    @GetMapping
    ResponseEntity<List<Rider>> getAllRiders(){
        List<Rider> allRiders = ridersRepository.findAll();
        return new ResponseEntity<>(allRiders, HttpStatus.OK);
    }

    //Find en bestemt rytter fra 'tabellen'
    @GetMapping("/{id}")
    ResponseEntity<Rider> getOneRider(@PathVariable("id") long id){
        if(ridersRepository.findById(id).isPresent()){
            Rider foundRider = ridersRepository.getById(id);
            return new ResponseEntity<>(foundRider, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/country/{countryx}")
    ResponseEntity<List<Rider>> getAllRidersByCountry(@PathVariable("countryx") String country) {
        String c = Country.valueOf(country).getCountryAbbrivation();
        List<Rider> riderList = ridersRepository.getAllByCountry(c);
        return new ResponseEntity<>(riderList, HttpStatus.OK);
    }

    //Opret en rytter i 'riders' tabellen
    @PostMapping
    ResponseEntity<Rider> createRider(@RequestBody Rider rider){
        ridersRepository.save(rider);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Opdatere en rytter i 'riders' tabellen
    @PutMapping("/{id}")
    ResponseEntity<Rider> updateRider(@PathVariable("id") long id, @RequestBody Rider rider){
        // lambda udtrykket/kaldet kan alternativt skrives som () -> new RiderNotFoundException()
        Optional<Rider> findRider = Optional.ofNullable(ridersRepository.findById(id).orElseThrow(RiderNotFoundException::new));
        if(!findRider.isPresent()){
            Rider storedRider = ridersRepository.getById(id);
            storedRider.setFirstName(rider.getFirstName());
            storedRider.setLastName(rider.getLastName());
            storedRider.setCountry(rider.getCountry());
            storedRider.setTotalTime(rider.getTotalTime());
            ridersRepository.save(storedRider);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Slet en rytter i 'riders' tabellen
    @DeleteMapping("/{id}")
    ResponseEntity<Rider> deleteRider(@PathVariable("id") long id){
        Optional<Rider> findRider = Optional.ofNullable(ridersRepository.findById(id).orElseThrow(RiderNotFoundException::new));
        if(!findRider.isPresent()) {
            System.out.println(id);
            Rider storedRider = ridersRepository.getById(id);
            ridersRepository.delete(storedRider);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
