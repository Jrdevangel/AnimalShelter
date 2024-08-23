package com.example.animalshelter.controller;

import com.example.animalshelter.model.Pet;
import com.example.animalshelter.services.PetService;
import  org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")


public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet savedPet = petService.savePet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        if (!petService.getPetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pet.setId(id);
        Pet updatedPet = petService.savePet(pet);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        if (!petService.getPetById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }



}


