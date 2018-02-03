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

@WebServlet("PrintPersonsList")
public class PrintPersonsListServlet extends HttpServlet {

    @EJB
    private PersonsRepositoryService personsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareAndDispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepareAndDispatch(req, resp);
    }

    private void prepareAndDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Person> persons = personsBean.getPersonsList();

       // list.stream.filter   (dane do filtracji z formularz.get)
        // (Person user)-> user.getName().contains("test")||user.getSurname().contains("test");
        // req.setAttribute("statList", stats);
        req.setAttribute("personsList", persons);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/persons-list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
