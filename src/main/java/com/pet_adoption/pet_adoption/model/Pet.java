package com.pet_adoption.pet_adoption.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String species;  // Dog, Cat, etc.
    private String breed;
    private int age;
    private String gender;

    @Column(nullable = false)
    private String status; // Available, Adopted, Pending

    private String photoUrl;
    private String description;

    @ManyToOne
    @JoinColumn(name = "shelter_id", nullable = false)
    private Shelter shelter;
}
