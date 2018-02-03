package simplelist.service;

import simplelist.domain.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonsRepositoryService {
    void addPerson(Person person);
    List<Person> getPersonsList();


}
