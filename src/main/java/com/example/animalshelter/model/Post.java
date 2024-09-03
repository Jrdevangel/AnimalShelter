package com.example.animalshelter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name= "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private long id;
    @Column (name = "title", nullable = false)
    private String title;
    @Column (name = "description",columnDefinition = "LONGTEXT", nullable = false)
    private String description;
    @Column (columnDefinition = "LONGTEXT")
    private String urlImg;

}