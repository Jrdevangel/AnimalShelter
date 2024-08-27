package com.example.animalshelter.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.animalshelter.model.Donation;
import com.example.animalshelter.repositories.IDonationRepository;

@Service
public class DonationService {

    private final IDonationRepository donationRepository;

    @Autowired
    public DonationService(IDonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Donation createDonation(Donation newDonation) {
        return donationRepository.save(newDonation);
    }

    public List<Donation> getAllDonations() {
        return (List<Donation>) donationRepository.findAll();
    }

    public void updateDonation(Donation donation, long id) {
        if (!donationRepository.existsById(id)) {
            throw new NoSuchElementException("Donation not found with id: " + id);
        }
        donation.setId(id);
        donationRepository.save(donation);
    }

    public String deleteDonationById(long id) {
        if (!donationRepository.existsById(id)) {
            return "Donation not found with id: " + id;
        }
        donationRepository.deleteById(id);
        return "Donation Deleted " + id;
    }
}
