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

import com.example.wordlyweek.model.*;
import com.example.wordlyweek.repository.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class WriterJpaService implements WriterRepository {
    @Autowired
    private WriterJpaRepository writerJpaRepository;
    @Autowired
    private MagazineJpaRepository magazineJpaRepository;

    @Override
    public ArrayList<Writer> getWriters() {
        return (ArrayList<Writer>) writerJpaRepository.findAll();
    }

    @Override
    public Writer getWriterById(int writerId) {
        try {
            return writerJpaRepository.findById(writerId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Writer addWriter(Writer writer) {

        List<Integer> magazineIds = new ArrayList<Integer>();
        for (Magazine magazine : writer.getMagazines()) {
            magazineIds.add(magazine.getMagazineId());
        }
        List<Magazine> magazines = magazineJpaRepository.findAllById(magazineIds);
        if (magazineIds.size() != magazines.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        writer.setMagazines(magazines);

        Writer savedWriter = writerJpaRepository.save(writer);
        // writerJpaRepository.save(writer);

        return savedWriter;

    }

    @Override
    public Writer updateWriter(int writerId, Writer writer) {
        try {
            Writer existWriter = writerJpaRepository.findById(writerId).get();
            if (writer.getWriterName() != null) {
                existWriter.setWriterName(writer.getWriterName());
            }
            if (writer.getBio() != null) {
                existWriter.setBio(writer.getBio());
            }
            if (writer.getMagazines() != null) {
                List<Integer> magazineIds = new ArrayList<Integer>();
                for (Magazine magazine : writer.getMagazines()) {
                    magazineIds.add(magazine.getMagazineId());
                }
                List<Magazine> magazines = magazineJpaRepository.findAllById(magazineIds);
                if (magazineIds.size() != magazines.size()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
                existWriter.setMagazines(magazines);
            }
            return writerJpaRepository.save(existWriter);
            

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteWriter(int writerId) {
        try {

            writerJpaRepository.deleteById(writerId);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public List<Magazine> getWriterMagazines(int writerId) {
        try {
            Writer writer = writerJpaRepository.findById(writerId).get();
            return writer.getMagazines();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}