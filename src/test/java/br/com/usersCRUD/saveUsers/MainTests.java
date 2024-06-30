package br.com.usersCRUD.saveUsers;

import br.com.usersCRUD.saveUsers.dto.PersonDTO;
import br.com.usersCRUD.saveUsers.entity.Person;
import br.com.usersCRUD.saveUsers.exception.InvalidPersonException;
import br.com.usersCRUD.saveUsers.service.IPersonService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MainTests {

	@Autowired
	public IPersonService personService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void checkSmallNamePerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Campo nome não possui a quantidade mínima de 3 caracteres.");

		PersonDTO person = new PersonDTO();
		person.setName("jo");
		person.setEmail("joao@email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkBigNamePerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Campo nome possui mais de 50 caracteres.");

		PersonDTO person = new PersonDTO();
		person.setName("joao ricardo pedro leonardo souza carlos arnaldo jose");
		person.setEmail("joao@email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkEmailPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("E-mail inválido.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao#email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkSmallPasswordPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Campo senha não possui a quantide mínima de 6 caracteres.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword("12345");
		person.setConfirmPassword("12345");

		personService.save(person);
	}

	@Test
	public void checkBigPasswordPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Campo senha possui mais de 20 caracteres.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword("12345678910joao_carlos");
		person.setConfirmPassword("12345678910joao_carlos");

		personService.save(person);
	}

	@Test
	public void checkPasswordEqualsPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Os campos de Senha e Confirmação de senha não são iguais.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword("1234567");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkNameNullPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Os campos obrigatórios não foram preenchidos.");

		PersonDTO person = new PersonDTO();
		person.setName(null);
		person.setEmail("joao@email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkEmailNullPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Os campos obrigatórios não foram preenchidos.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail(null);
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkPasswordNullPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Os campos obrigatórios não foram preenchidos.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword(null);
		person.setConfirmPassword("123456");

		personService.save(person);
	}

	@Test
	public void checkConfirmPasswordNullPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Os campos obrigatórios não foram preenchidos.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword("123456");
		person.setConfirmPassword(null);

		personService.save(person);
	}

	@Test
	public void checkCreateDuplicateEmailPerson() {
		expectedException.expect(InvalidPersonException.class);
		expectedException.expectMessage("Pessoa com o mesmo e-mail já está cadastrado.");

		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joao@email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		personService.save(person);

		PersonDTO person2 = new PersonDTO();
		person2.setName("joao2");
		person2.setEmail("joao@email.com");
		person2.setPassword("7891011");
		person2.setConfirmPassword("7891011");

		personService.save(person2);
	}

	@Test
	public void checkCorrectSavePerson() {
		PersonDTO person = new PersonDTO();
		person.setName("joao");
		person.setEmail("joaoTeste@email.com");
		person.setPassword("123456");
		person.setConfirmPassword("123456");

		Person personSaved = personService.save(person);
		Assert.assertNotEquals(personSaved, null);
	}

}
