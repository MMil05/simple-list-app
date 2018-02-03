package simplelist.servlets;

import simplelist.domain.Person;
import simplelist.service.PersonsRepositoryService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddPerson")
public class AddPersonServlet extends HttpServlet {

    @EJB
    private PersonsRepositoryService personsRepositoryService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                if (!areParamsValid(req, resp))
                    return;

                req.getSession().setAttribute("name", req.getParameter("name"));
                req.getSession().setAttribute("surname", req.getParameter("surname"));
                req.getSession().setAttribute("age", req.getParameter("age"));

                addPerson(req, resp);
                resp.sendRedirect("/PrintPersonsList");
    }

    private boolean areParamsValidStep1(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = !( // (req.getParameter("id") == null) ||
                (req.getParameter("login") == null)
                        // || req.getParameter("id").isEmpty()
                || req.getParameter("login").isEmpty()
        );

        if (!valid) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return valid;
        }

        if (req.getSession().getAttribute("edit_person_data") != null)
            return valid;

        return valid;
    }

    private boolean areParamsValid(HttpServletRequest req, HttpServletResponse resp) {
        boolean valid = !((req.getParameter("name") == null)
                || req.getParameter("name").isEmpty()
                || (req.getParameter("surname") == null)
                || req.getParameter("surname").isEmpty()
             //   || (req.getParameter("age") == null)
             //   || req.getParameter("age").isEmpty()
        );

        if (!valid)
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        return valid;
    }


    private void addPerson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Person newPerson = new Person();

        setPersonData(newPerson, req);

        personsRepositoryService.addPerson(newPerson);
        printAddedPerson(resp, newPerson);

    }


    private void printAddedPerson(HttpServletResponse resp, Person addedPerson) throws IOException {

        if (addedPerson == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body> Dodano osobę: "
                + addedPerson.getName() + " " + addedPerson.getSurname()
              //  + "`, wiek: " + person.getAge()
                + "</body></html>");
    }

    private void updatePerson(HttpServletRequest req, HttpServletResponse resp) {
        //req.getSession().setAttribute("edit_person_data", null);

        Person person = (Person) req.getSession().getAttribute("edited_person");
        if (person == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        setPersonData(person, req);
        req.getSession().invalidate();
    }

    private void setPersonData(Person person, HttpServletRequest req) {
        person.setName((String) req.getSession().getAttribute("name"));
        person.setSurname((String) req.getSession().getAttribute("surname"));
    //    person.setAge(Integer.parseInt((String) req.getSession().getAttribute("age")));

    }
}
