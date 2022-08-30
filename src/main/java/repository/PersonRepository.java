package repository;

import Models.PersonModel;
import dao.GenericDAO;
import jakarta.persistence.EntityManager;

public class PersonRepository extends GenericDAO<PersonModel,Integer> {
	public PersonRepository( EntityManager em) 
	{
		super(em);
	}

}
