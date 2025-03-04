package com.github.sangarru11.diccionariosantiago.repositories;

import com.github.sangarru11.diccionariosantiago.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}