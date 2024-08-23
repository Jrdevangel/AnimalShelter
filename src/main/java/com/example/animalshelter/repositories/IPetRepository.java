package com.example.animalshelter.repositories;

import com.example.animalshelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IPetRepository extends CrudRepository <Pet , Long >{
}

