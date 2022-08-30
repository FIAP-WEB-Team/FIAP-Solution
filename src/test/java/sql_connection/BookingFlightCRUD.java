package sql_connection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import model.BookingFlight;
import dao.BookingFlightDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class BookingFlightCRUD {
	private static EntityManager em = null;
	private static BookingFlightDAO bookingFlightDao;
	private static BookingFlight bookingFlight = new BookingFlight("Guarulhos", "Miami", 2, "GOL");

	@BeforeAll
	public static void initializeEntityManager() {
		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();
			bookingFlightDao = new BookingFlightDAO(em);
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

	public void createBookingFlight() {
		assertDoesNotThrow(() -> bookingFlightDao.save(bookingFlight));
	}

	public void selectBookingFlight() {
		assertEquals(bookingFlightDao.getEntity(bookingFlight.getId()).toString(), bookingFlight.toString());
	}

	public void updateBookingFlight() {
		var bookingFlight_2 = new BookingFlight("Origem", "Destino", 12, "Latam");
		assertDoesNotThrow(() -> bookingFlightDao.update(bookingFlight.getId(), bookingFlight_2));
		em.getTransaction().commit();
		selectBookingFlight();
	}

	public void deleteBookingFlight() {
		assertDoesNotThrow(() -> bookingFlightDao.deleteEntity(bookingFlight.getId()));
	}

}
