package com.github.sangarru11.diccionariosantiago.controllers;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.models.Word;
import com.github.sangarru11.diccionariosantiago.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) {
        return wordService.getWordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Word createWord(@RequestBody Word word) {
        return wordService.createWord(word);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word word) {
        return ResponseEntity.ok(wordService.updateWord(id, word));
    }

    @PostMapping("/{wordId}/definitions")
    public ResponseEntity<Definition> createDefinition(
            @PathVariable Long wordId,  // Obtén el ID de la palabra desde la URL
            @RequestBody Definition definition) {  // Obtén la definición desde el cuerpo de la solicitud
        Definition createdDefinition = wordService.createDefinition(wordId, definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return ResponseEntity.noContent().build();
    }
}