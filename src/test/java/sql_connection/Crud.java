package sql_connection;

import jakarta.persistence.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class Crud {
	
	private static EntityManager em = null;
	
	@BeforeAll
	public static void initializeEntityManager() {
		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();
		} catch(Exception e) {
			fail("Could not connect to database instance");
		}
	}
	
	@AfterAll
	public static void closeConnections() {
		if (em != null)
			em.close();
	}

	@Test
	void todo() {
		fail("To be implemented");
	}
}
