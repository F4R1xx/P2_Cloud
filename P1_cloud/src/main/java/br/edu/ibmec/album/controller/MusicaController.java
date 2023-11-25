package br.edu.ibmec.album.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ibmec.album.exception.MusicaException;
import br.edu.ibmec.album.model.Musica;
import br.edu.ibmec.album.service.MusicaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/musica")
@CrossOrigin
class MusicaController {

    @Autowired
    MusicaService musicaService;

    @GetMapping
    public ResponseEntity<List<Musica>> getAll() {
        try {
            List<Musica> items = new ArrayList<Musica>();

            musicaService.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Musica> getById(@PathVariable("id") long id) {
        Optional<Musica> existingItemOptional = musicaService.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<Musica> create(@PathVariable("id") long idCompositor, @Valid @RequestBody Musica item) throws MusicaException{
        Musica savedItem = musicaService.save(idCompositor, item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Musica> update(@PathVariable("id") long id, @Valid @RequestBody Musica item) throws MusicaException {
        return new ResponseEntity<>(musicaService.update(id, item), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws MusicaException {
        musicaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}