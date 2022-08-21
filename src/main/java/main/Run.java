package main;

import jakarta.persistence.*;

public class Run {
	public static void main(String[] args) {
		EntityManager em = null;

		try {
			em = Persistence.createEntityManagerFactory("solution-orm").createEntityManager();
			System.out.println("Connected succesfully");
		} catch (Exception e) {
			e.printStackTrace();
			if (em != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} finally {
			if (em != null)
				em.close();
		}
	}
}
