package com.example.animalshelter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.animalshelter.model.Pet;
import com.example.animalshelter.repositories.IPetRepository;
import com.example.animalshelter.services.PetService;

public class PetServiceTest {

    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePet() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");
        pet.setAge(5);
        pet.setBreed("Golden Retriever");
        pet.setGender("Male");

        when(iPetRepository.save(any(Pet.class))).thenReturn(pet);

        Pet result = petService.createPet(pet);

        assertEquals(pet, result);
        verify(iPetRepository, times(1)).save(pet);
    }

    @Test
    public void testGetAllPets() {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");
        pet.setAge(5);
        pet.setBreed("Golden Retriever");
        pet.setGender("Male");

        when(iPetRepository.findAll()).thenReturn(Collections.singletonList(pet));

        List<Pet> result = petService.getAllPets();

        assertEquals(1, result.size());
        assertEquals(pet, result.get(0));
        verify(iPetRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePet() {
        Pet existingPet = new Pet();
        existingPet.setId(1L);
        existingPet.setName("Buddy");
        existingPet.setAge(5);
        existingPet.setBreed("Golden Retriever");
        existingPet.setGender("Male");

        Pet updatedPet = new Pet();
        updatedPet.setName("Max");
        updatedPet.setAge(6);
        updatedPet.setBreed("Labrador");
        updatedPet.setGender("Male");

        when(iPetRepository.existsById(1L)).thenReturn(true);

        
        petService.updatePet(updatedPet, 1L);

        
        updatedPet.setId(1L);
        verify(iPetRepository, times(1)).save(updatedPet);
    }

    @Test
    public void testUpdatePet_NotFound() {
        Pet pet = new Pet();
        pet.setId(1L);

        when(iPetRepository.existsById(1L)).thenReturn(false);

        
        assertThrows(NoSuchElementException.class, () -> {
            petService.updatePet(pet, 1L);
        });

       
        verify(iPetRepository, times(0)).save(pet);
    }

    @Test
    public void testDeletePetById() {
        when(iPetRepository.existsById(1L)).thenReturn(true);

        String result = petService.deletePetById(1L);

        assertEquals("Pet Deleted 1", result);
        verify(iPetRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePetById_NotFound() {
        when(iPetRepository.existsById(1L)).thenReturn(false);

        String result = petService.deletePetById(1L);

        assertEquals("Pet not found with id: 1", result);
        verify(iPetRepository, times(0)).deleteById(1L);
    }
}
