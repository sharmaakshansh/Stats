package com.cric.stats.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
   

    private String country; // Added attribute for country

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_Player_id", referencedColumnName = "id")
    private List<Match> matches; // Added attribute for matches


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
    // Constructors, getters, and setters

    
    // Getters and setters for matches attribute
	public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 * @param dateOfBirth
	 * @param country
	 * @param matches
	 */
	public Player(Long id, String name, LocalDate dateOfBirth, String country, List<Match> matches) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.country = country;
		this.matches = matches;
	}
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 */
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

    

    

    // Getters and setters for other attributes
}
