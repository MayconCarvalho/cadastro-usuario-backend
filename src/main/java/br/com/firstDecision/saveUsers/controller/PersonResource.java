package br.com.firstDecision.saveUsers.controller;

import br.com.firstDecision.saveUsers.dto.PersonDTO;
import br.com.firstDecision.saveUsers.entity.Person;
import br.com.firstDecision.saveUsers.exception.InvalidPersonException;
import br.com.firstDecision.saveUsers.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "*")
public class PersonResource {

    @Autowired
    private IPersonService personService;

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
