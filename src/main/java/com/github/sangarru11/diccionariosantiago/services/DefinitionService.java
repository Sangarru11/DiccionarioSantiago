package com.github.sangarru11.diccionariosantiago.services;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.repositories.DefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinitionService {
    @Autowired
    private DefinitionRepository definitionRepository;

    /**
     * Obtiene las definiciones por el ID de la palabra
     * @param wordId ID de la palabra
     * @return Lista de definiciones de la palabra especificada
     */
    public List<Definition> getDefinitionsByWordId(Long wordId) {
        return definitionRepository.findByWordId(wordId);
    }

    /**
     * Elimina una definición por su ID
     * @param id ID de la definición a eliminar
     */
    public void deleteDefinition(Long id) {
        definitionRepository.deleteById(id);
    }
}