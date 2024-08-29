package com.example.animalshelter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pet") // Asegúrate de que el nombre de la tabla coincida con tu base de datos
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id") // Asegúrate de que el nombre de la columna coincida con tu base de datos
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age; // Cambiado a Integer para permitir valores nulos

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private String gender;
}
