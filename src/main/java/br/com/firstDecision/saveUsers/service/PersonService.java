package br.com.firstDecision.saveUsers.service;

import br.com.firstDecision.saveUsers.dto.PersonDTO;
import br.com.firstDecision.saveUsers.entity.Person;
import br.com.firstDecision.saveUsers.exception.InvalidPersonException;
import br.com.firstDecision.saveUsers.mapper.PersonMapper;
import br.com.firstDecision.saveUsers.repository.PersonRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Person save(PersonDTO personDTO) {
        if (personDTO.getPassword() == null || personDTO.getEmail() == null || personDTO.getName() == null || personDTO.getConfirmPassword() == null) {
            throw new InvalidPersonException("Os campos obrigatórios não foram preenchidos.");
        }
        if (!personDTO.getPassword().equals(personDTO.getConfirmPassword())) {
            throw new InvalidPersonException("Os campos de Senha e Confirmação de senha não são iguais.");
        }
        if (!EmailValidator.getInstance().isValid(personDTO.getEmail())) {
            throw new InvalidPersonException("E-mail inválido.");
        }
        if (personDTO.getName().length() < 3) {
            throw new InvalidPersonException("Campo nome não possui a quantidade mínima de 3 caracteres.");
        }
        if (personDTO.getName().length() > 50) {
            throw new InvalidPersonException("Campo nome possui mais de 50 caracteres.");
        }
        if (personDTO.getPassword().length() < 6) {
            throw new InvalidPersonException("Campo senha não possui a quantide mínima de 6 caracteres.");
        }
        if (personDTO.getPassword().length() > 20) {
            throw new InvalidPersonException("Campo senha possui mais de 20 caracteres.");
        }
        Person personSaved = personRepository.findByEmail(personDTO.getEmail());
        if (personSaved != null) {
            throw new InvalidPersonException("Pessoa com o mesmo e-mail já está cadastrado.");
        }
        Person personToSave = PersonMapper.DtoToEntityPerson(personDTO);
        String passwordHash = passwordEncoder.encode(personToSave.getPassword());
        personToSave.setPassword(passwordHash);
        return personRepository.save(personToSave);
    }
}
