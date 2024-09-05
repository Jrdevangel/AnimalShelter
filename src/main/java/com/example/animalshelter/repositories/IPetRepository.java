package com.example.animalshelter.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.animalshelter.model.Pet;

public interface IPetRepository extends CrudRepository<Pet, Long> {
}
