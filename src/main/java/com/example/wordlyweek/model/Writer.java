/*
 * You can use the following import statements
 *
 * import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 * 
 * import javax.persistence.*;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.wordlyweek.model;

import com.example.wordlyweek.model.*;
import java.util.*;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "writer")
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @ManyToMany
    @JoinTable(name = "writer_magazine", joinColumns = @JoinColumn(name = "writerid"), inverseJoinColumns = @JoinColumn(name = "magazineid"))

    @JsonIgnoreProperties("writers")
    private List<Magazine> magazines;

    public Writer() {
    }

    public Writer(int id, String name, String bio, List<Magazine> magazines) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.magazines = magazines;
    }

    public void setWriterId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return id;
    }

    public void setWriterName(String name) {
        this.name = name;
    }

    public String getWriterName() {
        return name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setMagazines(List<Magazine> magazines) {
        this.magazines = magazines;
    }

    public List<Magazine> getMagazines() {
        return magazines;
    }
}