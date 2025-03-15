package com.pet_adoption.pet_adoption.model;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shelters")
@Data
@NoArgsConstructor
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String location;
    private String phone;
    private String email;
    private String website;

    @JsonCreator
    public Shelter(@JsonProperty("id") Long id) {
        this.id = id;
    }



}

