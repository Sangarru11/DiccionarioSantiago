package com.github.sangarru11.diccionariosantiago.controllers;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.services.DefinitionService;
import com.github.sangarru11.diccionariosantiago.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("definitions")
public class DefinitionController {
    @Autowired
    private DefinitionService definitionService;

    @Autowired
    private WordService wordService;

    @GetMapping
    public List<Definition> getDefinitionsByWordId(@PathVariable Long wordId) {
        return definitionService.getDefinitionsByWordId(wordId);
    }

    @PostMapping
    public ResponseEntity<Definition> createDefinition(
            @PathVariable Long wordId,
            @RequestBody Definition definition) {
        Definition createdDefinition = wordService.createDefinition(wordId, definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefinition(@PathVariable Long id) {
        definitionService.deleteDefinition(id);
        return ResponseEntity.noContent().build();
    }
}