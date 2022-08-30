package dao;

import jakarta.persistence.EntityManager;
import model.Ticket;

public class TicketDAO extends GenericDAO<Ticket, Integer> {

	public TicketDAO(EntityManager manager) {
		super(manager);
	}


	public void update(Integer key, Ticket entity) {
		var oldEntity = this.getEntity(key);
		
		oldEntity.setPayment(entity.getPayment());
		oldEntity.setPrice(entity.getPrice());
	}
}
