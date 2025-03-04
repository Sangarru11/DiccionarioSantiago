package com.github.sangarru11.diccionariosantiago.services;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.models.Word;
import com.github.sangarru11.diccionariosantiago.repositories.DefinitionRepository;
import com.github.sangarru11.diccionariosantiago.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinitionService {
    @Autowired
    private DefinitionRepository definitionRepository;

    public List<Definition> getDefinitionsByWordId(Long wordId) {
        return definitionRepository.findByWordId(wordId);
    }

    public void deleteDefinition(Long id) {
        definitionRepository.deleteById(id);
    }
}