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

import br.edu.ibmec.album.exception.CompositorException;
import br.edu.ibmec.album.model.Compositor;
import br.edu.ibmec.album.service.CompositorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/compositor")
@CrossOrigin
class CompositorController {

    @Autowired
    CompositorService compositorService;

    @GetMapping
    public ResponseEntity<List<Compositor>> getAll() {
        try {
            List<Compositor> items = new ArrayList<>();

            compositorService.getAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Compositor> getById(@PathVariable("id") long id) {
        Optional<Compositor> existingItemOptional = compositorService.getById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Compositor> create(@Valid @RequestBody Compositor item) {
        Compositor savedItem = compositorService.create(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Compositor> update(@PathVariable("id") long id, @Valid @RequestBody Compositor item) throws CompositorException {
        return new ResponseEntity<>(compositorService.update(id, item), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws CompositorException {
        compositorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}