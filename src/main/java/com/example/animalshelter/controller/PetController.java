package com.example.animalshelter.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.animalshelter.model.Pet;
import com.example.animalshelter.services.PetService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet newPet) {
        Pet pet = petService.createPet(newPet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }

    @GetMapping("/pets")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet, @PathVariable long id) {
        petService.updatePet(pet, id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable long id) {
        String response = petService.deletePetById(id);
        HttpStatus status = response.contains("Error") ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }
}
