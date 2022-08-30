package repository;

import dao.GenericDAO;
import jakarta.persistence.EntityManager;
import model.PersonModel;

public class PersonRepository extends GenericDAO<PersonModel, Integer> {
	public PersonRepository(EntityManager em) {
		super(em);
	}

	public void update(int id, PersonModel person) {

		var oldPerson = getEntity(id);

		oldPerson.setBirthDate(person.getBirthDate());
		oldPerson.setCep(person.getCep());
		oldPerson.setCpf(person.getCpf());
		oldPerson.setFirstName(person.getFirstName());
		oldPerson.setLastName(person.getLastName());
		oldPerson.setGender(person.getGender());
		oldPerson.setNationality(person.getNationality());
		oldPerson.setTelephone(person.getTelephone());
		oldPerson.setBirthDate(person.getBirthDate());

	}

}
