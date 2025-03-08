package com.github.sangarru11.diccionariosantiago.services;

import com.github.sangarru11.diccionariosantiago.exceptions.WordNotFoundException;
import com.github.sangarru11.diccionariosantiago.models.Definition;
import com.github.sangarru11.diccionariosantiago.models.Word;
import com.github.sangarru11.diccionariosantiago.repositories.DefinitionRepository;
import com.github.sangarru11.diccionariosantiago.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private DefinitionRepository definitionRepository;

    /**
     * Obtiene todas las palabras
     * @return Lista de palabras
     */
    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    /**
     * Obtiene una palabra por su ID
     * @param id ID de la palabra
     * @return La palabra con el ID especificado
     */
    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    /**
     * Crea una nueva palabra
     * @param word La palabra a crear
     * @return La palabra creada
     */
    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    /**
     * Actualiza una palabra existente
     * @param id ID de la palabra a actualizar
     * @param word La palabra actualizada
     * @return La palabra actualizada
     */
    public Word updateWord(Long id, Word word) {
        word.setId(id);
        return wordRepository.save(word);
    }

    /**
     * Crea una definición para una palabra
     * @param wordId Id de la palabra
     * @param definition Definición a crear
     * @return Definición creada
     */
    public Definition createDefinition(Long wordId, Definition definition) {
        Optional<Word> wordOptional = wordRepository.findById(wordId);
        if (wordOptional.isPresent()) {
            Word word = wordOptional.get();
            definition.setWord(word);
            return definitionRepository.save(definition);
        } else {
            throw new WordNotFoundException("Word not found with id: " + wordId);
        }
    }

    /**
     * Obtiene palabras por categoría gramatical
     * @param gramatic La categoría gramatical
     * @return Lista de palabras que pertenecen a la categoría gramatical especificada
     */
    public List<Word> getWordsByGramatic(String gramatic) {
        return wordRepository.findByGramatic(gramatic);
    }

    /**
     * Obtiene palabras por su letra inicial
     * @param letter La letra inicial
     * @return Lista de palabras que comienzan con la letra especificada
     */
    public List<Word> getWordsByInitial(String letter) {
        return wordRepository.findByTermStartingWith(letter);
    }

    /**
     * Elimina una palabra por su ID
     * @param id ID de la palabra a eliminar
     */
    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }
}