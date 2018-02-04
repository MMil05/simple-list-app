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
import java.time.LocalDate;

@WebServlet("/AddPerson")
public class AddPersonServlet extends HttpServlet {

    @EJB
    private PersonsRepositoryService personsRepositoryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!areParamsValid(req)) {
            req.setAttribute("errorMsg", "Action canceled (incomplete data was entered)!");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        setAttributesFromParams(req);
                addPerson(req, resp);
                resp.sendRedirect("/PrintPersonsList");
    }

    private void setAttributesFromParams(HttpServletRequest req) {
        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("surname", req.getParameter("surname"));
        req.setAttribute("birthDate", req.getParameter("birthDate"));
        req.setAttribute("email", req.getParameter("email"));
    }


    private boolean areParamsValid(HttpServletRequest req) {
        boolean valid = !((req.getParameter("name") == null)
                || req.getParameter("name").trim().isEmpty()
                || (req.getParameter("surname") == null)
                || req.getParameter("surname").trim().isEmpty()
                || (req.getParameter("birthDate") == null)
                || req.getParameter("birthDate").trim().isEmpty()
                || (req.getParameter("email") == null)
                || req.getParameter("email").trim().isEmpty()
        );
        return valid;
    }


    private void addPerson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Person newPerson = new Person();
        setPersonData(newPerson, req);
        personsRepositoryService.addPerson(newPerson);
    }


    private void setPersonData(Person person, HttpServletRequest req) {
        person.setName((String) req.getAttribute("name"));
        person.setSurname((String) req.getAttribute("surname"));
        person.setEmail((String) req.getAttribute("email"));
        String birthDate = (String) req.getAttribute("birthDate");
        LocalDate date = LocalDate.parse(birthDate);
        person.setBirthDate(date);
    }
}
