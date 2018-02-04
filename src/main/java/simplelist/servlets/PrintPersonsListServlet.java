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

        req.setAttribute("personsList", personsFilteredList != null ? personsFilteredList : personsList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/persons-list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private List<Person> getPersonsFilteredList(HttpServletRequest req, List<Person> personsList) {
        List<Person> personsFilteredList = null;
        if (req.getParameter("keyword") != null && !req.getParameter("keyword").isEmpty()) {
            String keyword = req.getParameter("keyword").toLowerCase();

            personsFilteredList = personsList.stream()
                    .filter(person -> person.getName().toLowerCase()
                            .contains(keyword) || person.getSurname().toLowerCase()
                            .contains(keyword))
                    .collect(Collectors.toList());
            req.setAttribute("enteredKeyword", keyword);
        }
        return personsFilteredList;
    }
}
