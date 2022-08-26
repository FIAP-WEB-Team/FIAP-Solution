package sql_connection;

import jakarta.persistence.*;
import model.Payment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import dao.PaymentDAO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentCRUD {
	private static EntityManager em = null;
	private static PaymentDAO dao;
	private static int selectedID = -1;

	@BeforeAll
	public static void initializeEntityManager() {
		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();
			dao = new PaymentDAO(em);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error during database instantiation:\n");
		}
	}

	@AfterAll
	public static void closeConnections() {
		if (em != null) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			em.close();
		}
	}

	@BeforeEach
	public void beginTransaction() {
		em.getTransaction().begin();
	}

	@AfterEach
	public void commitTransaction() {
		if (em.getTransaction().isActive())
			em.getTransaction().commit();
	}

	@Test
	@Order(1)
	public void createPayment() {
		Payment payment = new Payment("Credit Card");
		assertDoesNotThrow(() -> dao.save(payment));

		em.getTransaction().commit();
		selectedID = payment.getID();
		System.out.println(selectedID);
	}

	@Test
	@Order(2)
	public void selectPayment() {
		assertEquals(dao.getEntity(selectedID).getName(), "Credit Card");
	}

	@Test
	@Order(3)
	public void updatePayment() {
		assertDoesNotThrow(() -> dao.update(selectedID, new Payment("Debit Card")));
		em.getTransaction().commit();
		assertEquals(dao.getEntity(selectedID).getName(), "Debit Card");
	}

	@Test
	@Order(Integer.MAX_VALUE)
	public void deletePayment() {
		assertDoesNotThrow(() -> dao.deleteEntity(selectedID));
	}
}