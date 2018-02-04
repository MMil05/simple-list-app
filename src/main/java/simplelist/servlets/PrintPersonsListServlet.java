package simplelist.servlets;

import simplelist.domain.Person;
import simplelist.service.PersonsRepositoryService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("PrintPersonsList")
public class PrintPersonsListServlet extends HttpServlet {

    @EJB
    private PersonsRepositoryService personsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareAndDispatch(req, resp);
    }

    private void prepareAndDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Person> personsList = personsBean.getPersonsList();
        List<Person> personsFilteredList = getPersonsFilteredList(req, personsList);

        req.getSession().setAttribute("personsList", personsFilteredList != null ? personsFilteredList : personsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/persons-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private List<Person> getPersonsFilteredList(HttpServletRequest req, List<Person> personsList) {
        String keyword;
        List<Person> personsFilteredList = null;
        keyword = req.getParameter("keyword");
        if (keyword != null) {
            if (!keyword.isEmpty()) {
                String phrase = keyword.toLowerCase();

                personsFilteredList = personsList.stream()
                        .filter(person -> person.getName().toLowerCase()
                                .contains(phrase) || person.getSurname().toLowerCase()
                                .contains(phrase))
                        .collect(Collectors.toList());
            }
            req.getSession().setAttribute("enteredKeyword", keyword);
        }
        return personsFilteredList;
    }
}
