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

import com.example.animalshelter.model.Donation;
import com.example.animalshelter.services.DonationService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/donations")
    public ResponseEntity<Donation> createDonation(@RequestBody Donation newDonation) {
        Donation donation = donationService.createDonation(newDonation);
        return new ResponseEntity<>(donation, HttpStatus.CREATED);
    }

    @GetMapping("/donations")
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> donations = donationService.getAllDonations();
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donation> updateDonation(@RequestBody Donation donation, @PathVariable long id) {
        donationService.updateDonation(donation, id);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable long id) {
        String response = donationService.deleteDonationById(id);
        HttpStatus status = response.contains("Error") ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }
}
