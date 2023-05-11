
package com.spring.soap3.soap3.controllers;

import com.spring.soap3.soap3.repositories.PersonService;
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

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personRepository) {
        this.personService = personRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse postPerson(@RequestPayload AddPersonRequest request) {
        AddPersonResponse response = new AddPersonResponse();
        Person person = new Person();
        person.setNom(request.getNom());
        person.setPrenom(request.getPrenom());
        person.setAge(request.getAge());
        person.setVille(request.getVille());
       response.setPerson(personService.postPersonDAO(person));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
        UpdatePersonResponse response = new UpdatePersonResponse();
        Person person = new Person();
        person.setNom(request.getNom());
        person.setPrenom(request.getPrenom());
        person.setAge(request.getAge());
        person.setVille(request.getVille());
        response.setPerson(personService.postPersonDAO(person));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        GetPersonResponse response = new GetPersonResponse();
       // response.setPerson(personRepository.findPerson(request.getNom()));
        response.setPerson(personService.findPerson(request.getPersonid()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequestDAO")
    @ResponsePayload
    public GetPersonResponse getPersonDAO(@RequestPayload GetPersonRequestDAO request) {
        GetPersonResponse response = new GetPersonResponse();
        // response.setPerson(personRepository.findPerson(request.getNom()));
        response.setPerson(personService.findPersonDAO(request.getPersonid()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonByNameRequest")
    @ResponsePayload
    public GetPersonResponse getPersonByname(@RequestPayload GetPersonByNameRequest request) {
        GetPersonResponse response = new GetPersonResponse();
        response.setPerson(personService.findPersonByName(request.getNom()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePersonById(@RequestPayload DeletePersonRequest request) {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setPerson(personService.deletePersonDAO(request.getPersonid()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPersonsRequest")
    @ResponsePayload
    public GetAllPersonsResponse getAllPersons(@RequestPayload GetAllPersonsRequest request) {
        GetAllPersonsResponse response = new GetAllPersonsResponse();
        List<Person> personList = personService.findAllPersonsDAO();
        response.getPersonsList().addAll(personList);
       // response.set(personRepository.findPersonByName(request.));
        return response;
    }
}

