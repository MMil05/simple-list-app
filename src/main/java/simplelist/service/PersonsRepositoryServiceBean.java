package simplelist.service;

import simplelist.domain.Person;
import simplelist.repository.PersonsRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PersonsRepositoryServiceBean implements PersonsRepositoryService {

    @EJB
    private PersonsRepository personsRepository;

    @Override
    public void addPerson(Person person) {
        personsRepository.addPerson(person);
    }

    public List<Person> getPersonsList() {
        return personsRepository.getPersonsList();
    }

}
