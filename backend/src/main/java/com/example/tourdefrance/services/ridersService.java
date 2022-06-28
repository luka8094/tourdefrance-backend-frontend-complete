package com.example.tourdefrance.services;

import com.example.tourdefrance.dao.Rider;
import com.example.tourdefrance.exceptions.RiderNotFoundException;
import com.example.tourdefrance.repositories.RidersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ridersService {

    RidersRepository ridersRepository;

    public ridersService(RidersRepository ridersRepository) {
        this.ridersRepository = ridersRepository;
    }


    List<Rider> getAllRiders(){
        return ridersRepository.findAll();
    }

    //Find en bestemt rytter fra 'tabellen'
    Rider getOneRider(long id){
        if(ridersRepository.findById(id).isPresent()){
            return ridersRepository.getById(id);
        }
        return null;
    }

    //Opret en rytter i 'riders' tabellen
    Rider createRider(Rider rider){
        return ridersRepository.save(rider);
    }

    //Opdatere en rytter i 'riders' tabellen
    Rider updateRider(long id, Rider rider){
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
            return null;
        }
        return ridersRepository.getById(id);
    }

    Boolean deleteRider(long id){
        Optional<Rider> findRider = Optional.ofNullable(ridersRepository.findById(id).orElseThrow(RiderNotFoundException::new));
        if(!findRider.isPresent()) {
            System.out.println(id);
            Rider storedRider = ridersRepository.getById(id);
            ridersRepository.delete(storedRider);
        }else{
            return false;
        }
        return true;
    }
}
