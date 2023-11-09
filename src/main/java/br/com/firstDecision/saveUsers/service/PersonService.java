package br.com.firstDecision.saveUsers.service;

import br.com.firstDecision.saveUsers.dto.PersonDTO;
import br.com.firstDecision.saveUsers.entity.Person;
import br.com.firstDecision.saveUsers.exception.InvalidPersonException;
import br.com.firstDecision.saveUsers.mapper.PersonMapper;
import br.com.firstDecision.saveUsers.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(PersonDTO personDTO) {
        if (!personDTO.getPassword().equals(personDTO.getConfirmPassword())) {
            throw new InvalidPersonException("Passwords are different.");
        }
        Person personSaved = personRepository.findByEmail(personDTO.getEmail());
        if (personSaved != null) {
            throw new InvalidPersonException("Person with the same email has already saved.");
        }
        Person personToSave = PersonMapper.DtoToEntityPerson(personDTO);
        return personRepository.save(personToSave);
    }
}
