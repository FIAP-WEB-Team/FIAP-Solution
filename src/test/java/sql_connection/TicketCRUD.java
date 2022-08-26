package sql_connection;

import jakarta.persistence.*;
import model.Payment;
import model.Ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

import dao.PaymentDAO;
import dao.TicketDAO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketCRUD {

	private static EntityManager em = null;
	private static PaymentDAO paymentDao;
	private static TicketDAO ticketDao;
	private static Payment payment = new Payment("Credit Card");
	private static Payment payment2 = new Payment("Debit Card");
	private static Ticket ticket = new Ticket(4.56, new Date(), payment);

	@BeforeAll
	public static void initializeEntityManager() {
		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();
			paymentDao = new PaymentDAO(em);
			ticketDao = new TicketDAO(em);
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
	public void createTicket() {
		assertDoesNotThrow(() -> paymentDao.save(payment));
		assertDoesNotThrow(() -> paymentDao.save(payment2));
		assertDoesNotThrow(() -> ticketDao.save(ticket));
	}

	@Test
	@Order(2)
	public void selectTicket() {
		assertEquals(ticketDao.getEntity(ticket.getID()).toString(), ticket.toString());
	}

	@Test
	@Order(3)
	public void updateTicket() {
		var ticket2 = new Ticket(4.54, ticket.getDate(), payment2);
		assertDoesNotThrow(() -> ticketDao.update(ticket.getID(), ticket2));
		em.getTransaction().commit();
		assertEquals(ticketDao.getEntity(ticket.getID()).toString(), ticket.toString());
	}

	@Test
	@Order(Integer.MAX_VALUE)
	public void deleteTicket() {
		assertDoesNotThrow(() -> ticketDao.deleteEntity(ticket.getID()));
		assertDoesNotThrow(() -> paymentDao.deleteEntity(payment.getID()));
		assertDoesNotThrow(() -> paymentDao.deleteEntity(payment2.getID()));
	}
}

