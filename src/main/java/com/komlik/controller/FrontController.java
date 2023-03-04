package com.komlik.controller;


import com.komlik.domain.Person;
import com.komlik.service.PersonService;
import com.komlik.service.PersonServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {

    private final PersonService personService = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            System.out.println("We are processing user request");

            List<Person> persons = personService.findAll();

            String collect = persons.stream().map(Person::getName).collect(Collectors.joining(","));

            req.setAttribute("personName", collect);
            req.setAttribute("persons", persons);

            dispatcher.forward(req, resp);
        }
    }
}
