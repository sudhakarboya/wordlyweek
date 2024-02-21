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
@Table(name = "magazine")
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "publicationdate")
    private String publicationDate;

    @ManyToMany(mappedBy = "magazines")
    @JsonIgnoreProperties("magazines")
    private List<Writer> writers;

    public Magazine() {
    }

    public Magazine(int id, String title, String publicationDate, List<Writer> writers) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.writers = writers;
    }

    public void setMagazineId(int id) {
        this.id = id;
    }

    public int getMagazineId() {
        return id;
    }

    public void setMagazineName(String title) {
        this.title = title;
    }

    public String getMagazineName() {
        return title;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public List<Writer> getWriters() {
        return writers;
    }
}