package br.com.usersCRUD.saveUsers.mapper;

import br.com.usersCRUD.saveUsers.dto.PersonDTO;
import br.com.usersCRUD.saveUsers.entity.Person;

public class PersonMapper {

    public static Person DtoToEntityPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setEmail(personDTO.getEmail());
        person.setPassword(personDTO.getPassword());
        person.setName(personDTO.getName());

        return person;
    }
}
