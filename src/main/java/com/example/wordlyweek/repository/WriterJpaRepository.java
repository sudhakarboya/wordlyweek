/*
 * You can use the following import statements
 *
 * import org.springframework.data.jpa.repository.JpaRepository;
 * import org.springframework.stereotype.Repository;
 * 
 */

// Write your code here
package com.example.wordlyweek.repository;

import com.example.wordlyweek.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

@Repository
public interface WriterJpaRepository extends JpaRepository<Writer, Integer> {
    
}