package dao;

import java.lang.reflect.ParameterizedType;

import jakarta.persistence.EntityManager;

public class GenericDAO<E, K> {
	protected Class<E> entity;
	protected EntityManager manager;

	@SuppressWarnings("unchecked")
	public GenericDAO(EntityManager manager) {
		this.manager = manager;
		this.entity = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void save(E entity) {
		this.manager.persist(entity);
	}

	public E getEntity(K key) {
		return this.manager.find(entity, key);
	}

	public void deleteEntity(K key) {
		var deletedEntity = this.getEntity(key);

		if (deletedEntity != null)
			this.manager.remove(deletedEntity);

		else
			throw new IllegalArgumentException(
					"Key " + key + " does not exist within entity class " + this.entity.getSimpleName());
	}

	public void update(K key, E entity) {
		this.deleteEntity(key);
		this.save(entity);
	}

}
