package com.github.sangarru11.diccionariosantiago.repositories;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    List<Definition> findByWordId(Long wordId);
}
