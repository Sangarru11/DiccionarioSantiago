package com.github.sangarru11.diccionariosantiago.controllers;

import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.models.Word;
import com.github.sangarru11.diccionariosantiago.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/words")
public class WordController {
    @Autowired
    private WordService wordService;

    /**
     * Obtiene todas las palabras
     * @return Lista de palabras
     */
    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    /**
     * Obtiene una palabra por su ID
     * @param id ID de la palabra
     * @return La palabra con el ID especificado o un 404 si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<Word> getWordById(@PathVariable Long id) {
        Optional<Word> wordOptional = wordService.getWordById(id);
        if (wordOptional.isPresent()) {
            return ResponseEntity.ok(wordOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea una nueva palabra
     * @param word La palabra a crear
     * @return La palabra creada con un código de estado 201
     */
    @PostMapping
    public ResponseEntity<Word> createWord(@RequestBody Word word) {
        Word createdWord = wordService.createWord(word);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    /**
     * Actualiza una palabra existente
     * @param id ID de la palabra a actualizar
     * @param word La palabra actualizada
     * @return La palabra actualizada o un 404 si no se encuentra
     */
    @PutMapping("/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody Word word) {
        Optional<Word> wordOptional = wordService.getWordById(id);
        if (wordOptional.isPresent()) {
            Word updatedWord = wordService.updateWord(id, word);
            return ResponseEntity.ok(updatedWord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea una nueva definición para una palabra específica
     * @param wordId ID de la palabra
     * @param definition La definición a crear
     * @return La definición creada con un código de estado 201
     */
    @PostMapping("/{wordId}/definitions")
    public ResponseEntity<Definition> createDefinition(
            @PathVariable Long wordId, @RequestBody Definition definition) {
        Definition createdDefinition = wordService.createDefinition(wordId, definition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinition);
    }

    /**
     * Elimina una palabra por su ID
     * @param id ID de la palabra a eliminar
     * @return Un código de estado 204 si se elimina correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        wordService.deleteWord(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Crea una nueva palabra con definiciones
     * @param word La palabra a crear con sus definiciones
     * @return La palabra creada con un código de estado 201
     */
    @PostMapping("/with-definitions")
    public ResponseEntity<Word> createWordWithDefinitions(@RequestBody Word word) {
        Word createdWord = wordService.createWord(word);
        for (Definition definition : word.getDefinitions()) {
            wordService.createDefinition(createdWord.getId(), definition);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWord);
    }

    /**
     * Obtiene palabras por categoría gramatical
     * @param gramatic La categoría gramatical
     * @return Lista de palabras que pertenecen a la categoría gramatical especificada
     */
    @GetMapping("/gramatic/{gramatic}")
    public List<Word> getWordsByGramatic(@PathVariable String gramatic) {
        return wordService.getWordsByGramatic(gramatic);
    }

    /**
     * Obtiene palabras por su letra inicial
     * @param letter La letra inicial
     * @return Lista de palabras que comienzan con la letra especificada
     */
    @GetMapping("/initial/{letter}")
    public List<Word> getWordsByInitial(@PathVariable String letter) {
        return wordService.getWordsByInitial(letter);
    }
}