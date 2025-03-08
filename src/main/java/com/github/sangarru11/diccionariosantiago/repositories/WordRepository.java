package com.github.sangarru11.diccionariosantiago.repositories;

import com.github.sangarru11.diccionariosantiago.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByGramatic(String gramatic);
    List<Word> findByTermStartingWith(String letter);
}