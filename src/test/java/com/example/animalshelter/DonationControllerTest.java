package com.example.animalshelter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.animalshelter.controller.DonationController;
import com.example.animalshelter.model.Donation;
import com.example.animalshelter.services.DonationService;

public class DonationControllerTest {

    @Mock
    private DonationService donationService;

    @InjectMocks
    private DonationController donationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateDonation() {
        Donation newDonation = new Donation();
        newDonation.setId(1L);
        given(donationService.createDonation(any(Donation.class))).willReturn(newDonation);

        ResponseEntity<Donation> response = donationController.createDonation(newDonation);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newDonation, response.getBody());
    }

    @Test
    public void testGetAllDonations() {
        List<Donation> donations = new ArrayList<>();
        donations.add(new Donation());
        donations.add(new Donation());
        given(donationService.getAllDonations()).willReturn(donations);

        ResponseEntity<List<Donation>> response = donationController.getAllDonations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donations, response.getBody());
    }

    @Test
    public void testUpdateDonation() {
        Donation donationToUpdate = new Donation();
        donationToUpdate.setId(1L);
        doNothing().when(donationService).updateDonation(any(Donation.class), anyLong());

        ResponseEntity<Donation> response = donationController.updateDonation(donationToUpdate, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(donationToUpdate, response.getBody());
    }

    @Test
    public void testDeleteDonationById() {
        String responseMessage = "Donation deleted successfully";
        given(donationService.deleteDonationById(anyLong())).willReturn(responseMessage);

        ResponseEntity<String> response = donationController.deleteDonationById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());
    }
}
