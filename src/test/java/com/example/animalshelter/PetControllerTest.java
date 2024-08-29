package com.example.animalshelter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.example.animalshelter.controller.PetController;
import com.example.animalshelter.model.Pet;
import com.example.animalshelter.services.PetService;

public class PetControllerTest {

    @InjectMocks
    private PetController petController;

    @Mock
    private PetService petService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePet() {
        Pet pet = new Pet(); // Crea y configura tu objeto Pet
        when(petService.createPet(any(Pet.class))).thenReturn(pet);

        ResponseEntity<Pet> response = petController.createPet(pet);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).createPet(any(Pet.class));
    }

    @Test
    public void testGetAllPets() {
        List<Pet> pets = Collections.singletonList(new Pet()); // Crea y configura tu lista de pets
        when(petService.getAllPets()).thenReturn(pets);

        ResponseEntity<List<Pet>> response = petController.getAllPets();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pets, response.getBody());
        verify(petService, times(1)).getAllPets();
    }

    @Test
    public void testUpdatePet() {
        Pet pet = new Pet(); // Crea y configura tu objeto Pet
        doNothing().when(petService).updatePet(any(Pet.class), anyLong());

        ResponseEntity<Pet> response = petController.updatePet(pet, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).updatePet(any(Pet.class), anyLong());
    }

    @Test
    public void testUpdatePetNotFound() {
        Pet pet = new Pet(); // Crea y configura tu objeto Pet
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(petService).updatePet(any(Pet.class),
                anyLong());

        ResponseStatusException thrown = null;
        try {
            petController.updatePet(pet, 1L);
        } catch (ResponseStatusException ex) {
            thrown = ex;
        }

        assertNotNull(thrown);
        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode());
    }

    @Test
    public void testDeletePetById() {
        String responseMessage = "Pet deleted successfully";
        when(petService.deletePetById(anyLong())).thenReturn(responseMessage);

        ResponseEntity<String> response = petController.deletePetById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());
        verify(petService, times(1)).deletePetById(anyLong());
    }

    @Test
    public void testDeletePetByIdError() {
        String responseMessage = "Error deleting pet";
        when(petService.deletePetById(anyLong())).thenReturn(responseMessage);

        ResponseEntity<String> response = petController.deletePetById(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());
    }
}
