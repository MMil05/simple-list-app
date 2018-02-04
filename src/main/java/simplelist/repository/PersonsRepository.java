package simplelist.repository;

import simplelist.domain.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonsRepository {

    @PersistenceContext(unitName = "pUnit")
    private EntityManager entityManager;

    public boolean addPerson(Person person) {
        entityManager.persist(person);
        return true;
    }

    public List<Person> getPersonsList() {
        return (List<Person>) entityManager.createNamedQuery("getAll")
                .getResultList();
    }

}
