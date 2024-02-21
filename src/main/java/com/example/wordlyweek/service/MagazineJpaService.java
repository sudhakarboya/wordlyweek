/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * 
 * import java.util.*;
 *
 */

// Write your code here
package com.example.wordlyweek.service;

import java.util.*;
import com.example.wordlyweek.model.*;
import com.example.wordlyweek.repository.*;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Service
public class MagazineJpaService implements MagazineRepository {
    @Autowired
    private MagazineJpaRepository magazineJpaRepository;
    @Autowired
    private WriterJpaRepository writerJpaRepository;

    @Override
    public ArrayList<Magazine> getMagazines() {
        return (ArrayList<Magazine>) magazineJpaRepository.findAll();
    }

    @Override
    public Magazine getMagazineById(int magazineId) {
        try {
            return magazineJpaRepository.findById(magazineId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Magazine addMagazine(Magazine magazine) {

        List<Integer> writerIds = new ArrayList<>();
        for (Writer writer : magazine.getWriters()) {
            writerIds.add(writer.getWriterId());
        }
        List<Writer> writers = writerJpaRepository.findAllById(writerIds);
        for (Writer writer : writers) {
            writer.getMagazines().add(magazine);
        }

        Magazine savedMagazine = magazineJpaRepository.save(magazine);
        writerJpaRepository.saveAll(writers);
        return savedMagazine;

    }

    @Override
    public Magazine updateMagazine(int magazineId, Magazine magazine) {
        try {
            Magazine existMagazine = magazineJpaRepository.findById(magazineId).get();
            if (magazine.getMagazineName() != null) {
                existMagazine.setMagazineName(magazine.getMagazineName());
            }
            if (magazine.getPublicationDate() != null) {
                existMagazine.setPublicationDate(magazine.getPublicationDate());

            }
            if (magazine.getWriters() != null) {
                List<Writer> writers=existMagazine.getWriters();
                for(Writer writer:writers){
                    writer.getMagazines().remove(existMagazine);
                }
                writerJpaRepository.saveAll(writers);


                List<Integer> writerIds = new ArrayList<>();
                for (Writer writer : magazine.getWriters()) {
                    writerIds.add(writer.getWriterId());
                }
                List<Writer> newWriters = writerJpaRepository.findAllById(writerIds);
                for (Writer writer : newWriters) {
                    writer.getMagazines().add(existMagazine);
                }
                writerJpaRepository.saveAll(newWriters);
                existMagazine.setWriters(newWriters);
            }

            return magazineJpaRepository.save(existMagazine);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteMagazine(int magazineId) {
        try {
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            List<Writer> writers = magazine.getWriters();
            for (Writer writer : writers) {
                writer.getMagazines().remove(magazine);
            }
            writerJpaRepository.saveAll(writers);
            magazineJpaRepository.deleteById(magazineId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public List<Writer> getMagazineWriters(int magazineId) {
        try {
            Magazine magazine = magazineJpaRepository.findById(magazineId).get();
            return magazine.getWriters();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
