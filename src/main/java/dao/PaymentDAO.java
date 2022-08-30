package dao;

import jakarta.persistence.EntityManager;
import model.Payment;

public class PaymentDAO extends GenericDAO<Payment, Integer> {

	public PaymentDAO(EntityManager manager) {
		super(manager);
	}
	

	public void update(Integer key, Payment entity) {
		var oldEntity = this.getEntity(key);
		oldEntity.setName(entity.getName());
	}

}