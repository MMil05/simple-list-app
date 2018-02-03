package simplelist;

import simplelist.service.PersonsRepositoryService;
import simplelist.service.PersonsRepositoryServiceBean;
import simplelist.domain.Person;


public class Main {

    public static void main(String[] args) {

        PersonsRepositoryService DaoBean = new PersonsRepositoryServiceBean();
        for ( Person person : DaoBean.getPersonsList()) {
            System.out.println(person.getName());
        }

    }


}
