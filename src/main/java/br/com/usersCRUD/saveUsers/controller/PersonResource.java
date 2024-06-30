package br.com.usersCRUD.saveUsers.controller;

import br.com.usersCRUD.saveUsers.dto.PersonDTO;
import br.com.usersCRUD.saveUsers.entity.Person;
import br.com.usersCRUD.saveUsers.exception.InvalidPersonException;
import br.com.usersCRUD.saveUsers.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonResource {

    @Autowired
    private IPersonService personService;

    @GetMapping("/")
    public ResponseEntity<List<Person>> findAll() {
        List<Person> results = personService.findAll();
        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody PersonDTO person) {

        try {
            Person savedPerson = personService.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
        } catch (InvalidPersonException | ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
