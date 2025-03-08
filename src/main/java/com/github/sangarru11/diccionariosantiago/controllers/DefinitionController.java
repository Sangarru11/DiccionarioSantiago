package com.github.sangarru11.diccionariosantiago.controllers;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.models.Word;
import com.github.sangarru11.diccionariosantiago.services.DefinitionService;
import com.github.sangarru11.diccionariosantiago.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("definitions")
public class DefinitionController {
    @Autowired
    private DefinitionService definitionService;

    @Autowired
    private WordService wordService;

    /**
     * Obtiene las definiciones por el ID de la palabra
     * @param wordId ID de la palabra
     * @return Lista de definiciones de la palabra especificada
     */
    @GetMapping
    public List<Definition> getDefinitionsByWordId(@PathVariable Long wordId) {
        return definitionService.getDefinitionsByWordId(wordId);
    }

    /**
     * Crea una nueva definición para una palabra específica
     * @param wordId ID de la palabra
     * @param definition La definición a crear
     * @return La definición creada con un código de estado 201
     */
    @PostMapping
    public ResponseEntity<Definition> createDefinition(
            @PathVariable Long wordId,
            @RequestBody Definition definition) {
        Optional<Word> wordOptional = wordService.getWordById(wordId);
        if (wordOptional.isPresent()) {
            Definition createdDefinition = wordService.createDefinition(wordId, definition);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una definición por su ID
     * @param id ID de la definición a eliminar
     * @return Un código de estado 204 si se elimina correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefinition(@PathVariable Long id) {
        definitionService.deleteDefinition(id);
        return ResponseEntity.noContent().build();
    }
}