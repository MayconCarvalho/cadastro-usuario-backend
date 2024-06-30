package br.com.usersCRUD.saveUsers.service;

import br.com.usersCRUD.saveUsers.dto.PersonDTO;
import br.com.usersCRUD.saveUsers.entity.Person;

import java.util.List;

public interface IPersonService {

    Person save(PersonDTO person);
    List<Person> findAll();
}
