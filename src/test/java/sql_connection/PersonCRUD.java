package sql_connection;

import jakarta.persistence.*;
import model.PersonModel;
import repository.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import java.util.List;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonCRUD {

	private static EntityManager em = null;
	List<PersonModel> listPerson;
	static int idlocal;
	PersonRepository person = new PersonRepository(em);
	public int ident;

	@BeforeAll
	public static void initializeEntityManager() {
		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();

		} catch (Exception e) {
			fail("Could not connect to database instance");
		}
	}

	@BeforeEach
	public void beginTransaction() {

		em.getTransaction().begin();
	}

	@AfterAll
	public static void closeConnections() {
		if (em != null)
			em.close();
	}

	@AfterEach
	public void commitTransaction() {
		em.getTransaction().commit();
	}

	@Test
	@Order(1)
	void savePerson() {
		Date dt = new Date(10 - 01 - 97);
		PersonModel person1 = new PersonModel();
		person1.setFirstName("Giba");
		person1.setLastName("Dos Santos");
		person1.setBirthDate(dt);
		person1.setCpf("12345678971");
		person1.setEmail("Giba@gmail.com");
		person1.setGender("M");
		person1.setCep("81058-820");
		person1.setTelephone("21 98115-2022");
		person1.setNationality("Brasileiro");
		assertDoesNotThrow(() -> person.save(person1));
		idlocal = person1.getPersonID();

	}

	@Test
	@Order(2)
	void getPerson() {
		PersonRepository person = new PersonRepository(em);
		var entity = person.getEntity(idlocal);
		var cep = entity.getCep();
		var email = entity.getEmail();

		assertEquals(cep, "81058-820");
		assertEquals(email, "Giba@gmail.com");

	}

	@Test
	@Order(3)
	void UpdatePerson() {
		Date dt = new Date(10 - 01 - 97);
		PersonModel person1 = new PersonModel();
		person1.setFirstName("Claudin");
		person1.setLastName("Dos Santos");
		person1.setBirthDate(dt);
		person1.setCpf("1122");
		person1.setEmail("Giba@gmail.com");
		person1.setGender("M");
		person1.setCep("81058-820");
		person1.setTelephone("21 98115-2022");
		person1.setNationality("Brasileiro");

		PersonRepository person = new PersonRepository(em);
		person.update(idlocal, person1);

	}

	@Test
	@Order(4)
	void deletePerson() {
		PersonRepository person = new PersonRepository(em);
		assertDoesNotThrow(() -> person.deleteEntity(idlocal));

	}

}
