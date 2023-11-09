package br.com.firstDecision.saveUsers.mapper;

import br.com.firstDecision.saveUsers.dto.PersonDTO;
import br.com.firstDecision.saveUsers.entity.Person;

public class PersonMapper {

    public static Person DtoToEntityPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setEmail(personDTO.getEmail());
        person.setPassword(personDTO.getPassword());
        person.setName(personDTO.getName());

        return person;
    }
}
