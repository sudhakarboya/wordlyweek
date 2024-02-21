/*
 * You can use the following import statements
 *
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.wordlyweek.repository;

import java.util.*;
import com.example.wordlyweek.model.*;

public interface WriterRepository {
    ArrayList<Writer> getWriters();

    Writer getWriterById(int writerId);

    Writer addWriter(Writer writer);

    Writer updateWriter(int writerId, Writer writer);

    void deleteWriter(int writerId);

    List<Magazine> getWriterMagazines(int writerId);
}