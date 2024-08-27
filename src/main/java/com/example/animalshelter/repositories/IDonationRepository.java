package com.example.animalshelter.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.animalshelter.model.Donation;

public interface IDonationRepository extends CrudRepository<Donation, Long> {
}
