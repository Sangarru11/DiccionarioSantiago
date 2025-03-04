package com.github.sangarru11.diccionariosantiago.services;

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

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public Word updateWord(Long id, Word word) {
        word.setId(id);
        return wordRepository.save(word);
    }

    public Definition createDefinition(Long wordId, Definition definition) {
        Word word = wordRepository.findById(wordId).orElseThrow(() -> new RuntimeException("Word not found with id: " + wordId));

        definition.setWord(word);

        return definitionRepository.save(definition);
    }


    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }
}