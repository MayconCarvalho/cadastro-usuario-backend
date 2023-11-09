package br.com.firstDecision.saveUsers.service;

import br.com.firstDecision.saveUsers.dto.PersonDTO;
import br.com.firstDecision.saveUsers.entity.Person;

public interface IPersonService {

    Person save(PersonDTO person);
}
