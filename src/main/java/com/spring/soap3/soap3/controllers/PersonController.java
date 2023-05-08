
package com.spring.soap3.soap3.controllers;

import com.spring.soap3.soap3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import project1.soap.com.gen17.*;

import java.util.List;

@Endpoint
public class PersonController {

    private static final String NAMESPACE_URI = "http://com.soap.project1/gen17";

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
       // response.setPerson(personRepository.findPerson(request.getNom()));
        response.setPerson(personRepository.findPerson(request.getPersonid()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequestDAO")
    @ResponsePayload
    public GetPersonResponse getPersonDAO(@RequestPayload GetPersonRequestDAO request) {
        GetPersonResponse response = new GetPersonResponse();
        // response.setPerson(personRepository.findPerson(request.getNom()));
        response.setPerson(personRepository.findPersonDAO(request.getPersonid()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonByNameRequest")
    @ResponsePayload
    public GetPersonResponse getPersonByname(@RequestPayload GetPersonByNameRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(personRepository.findPersonByName(request.getNom()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePersonById(@RequestPayload DeletePersonRequest request) {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setPerson(personRepository.deletePersonDAO(request.getPersonid()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonsRequest")
    @ResponsePayload
    public GetAllPersonsResponse getAllPersons(@RequestPayload GetAllPersonsRequest request) {
        GetAllPersonsResponse response = new GetAllPersonsResponse();
        List<Person> personList = personRepository.findAllPersonsDAO();
        response.getPersonsList().addAll(personList);
       // response.set(personRepository.findPersonByName(request.));
        return response;
    }
}

