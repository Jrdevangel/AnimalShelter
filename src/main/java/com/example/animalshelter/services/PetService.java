package com.example.animalshelter.services;

import com.example.animalshelter.model.Pet;
import com.example.animalshelter.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PetService {

    private final IPetRepository iPetRepository;

    @Autowired
    public PetService(IPetRepository iPetRepository) {
        this.iPetRepository = iPetRepository;
    }

    public Pet createPet(Pet newPet) {  // Cambiado 'createPets' a 'createPet'
        return iPetRepository.save(newPet);
    }

    public List<Pet> getAllPets() {
        return (List<Pet>) iPetRepository.findAll();
    }

    public void updatePet(Pet pet, long id) {
        if (!iPetRepository.existsById(id)) {
            throw new NoSuchElementException("Pet not found with id: " + id);
        }
        pet.setId(id);
        iPetRepository.save(pet);
    }

    public String deletePetById(long id) {
        if (!iPetRepository.existsById(id)) {
            return "Pet not found with id: " + id;
        }
        iPetRepository.deleteById(id);
        return "Pet Deleted " + id;
    }
}
