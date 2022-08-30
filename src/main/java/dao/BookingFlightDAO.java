package dao;

import jakarta.persistence.EntityManager;
import model.BookingFlight;

public class BookingFlightDAO extends GenericDAO<BookingFlight, Integer> {

	public BookingFlightDAO(EntityManager em) {
		super(em);
	}
	
	@Override
	public void update(Integer key, BookingFlight entity) {
		var currentEntity = this.getEntity(key);
		currentEntity.setOrigin(entity.getOrigin());
		currentEntity.setDestiny(entity.getDestiny());
		currentEntity.setSeat(entity.getSeat());
		currentEntity.setOperator(entity.getOperator());
	}

}
