
package com.spring.soap3.soap3.repositories;

import com.spring.soap3.soap3.entities.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project1.soap.com.gen17.Person;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Component
public class PersonRepository {

    private Logger logger = LoggerFactory.getLogger(PersonRepository.class);

   @Autowired
    private PersonRepositoryDAO personRepositoryDAO;

    private static final Map<String, Person> persons = new HashMap<>();

    private static final List<Person> persons2 = new ArrayList<>();

    @PostConstruct
    public void initData() {
        //initialize country
        Person person1 = new Person();
        person1.setNom("douala");
        person1.setPrenom("georges");
        person1.setVille("douala");
        person1.setAge(62);
        person1.setPersonid(1);

        Person person2 = new Person();
        person2.setNom("ebelle");
        person2.setPrenom("jacques");
        person2.setVille("essen");
        person2.setAge(51);
        person2.setPersonid(2);
        // initialize countries
        persons.put("douala", person1);
        persons.put("ebelle",person2);

        persons2.add( person1);
        persons2.add( person2);
    }

    public Person findPerson(int id) {return persons2.get(id);}

    public Person findPersonByName(String nom) {return persons.get(nom);}

   public Person findPersonDAO(int id) {
        Optional<PersonEntity>optionalPerson = personRepositoryDAO.findById(id);
        if(optionalPerson.isPresent()){
            logger.info("the person with id {} exist and his name is {}, {}", id, optionalPerson.get().getPrenom(),
                    optionalPerson.get().getNom());
            PersonEntity personEntity = optionalPerson.get();
            Person person = new Person();
            Person resultPerson = this.convertEntityToPerson(person, personEntity);
            return resultPerson;
        }
        else{
            logger.info("the person with id {} doesn't exist", id);
            return null;
        }
    }

    public List<Person> findAllPersonsDAO() {
        List<PersonEntity>personEntities = personRepositoryDAO.findAll();
        List<Person> persons = new ArrayList<>();
        personEntities.forEach(
                personEntity -> {
                    Person person = new Person();
                    Person newPerson = convertEntityToPerson(person,personEntity);
                    persons.add(newPerson);
                }
        );
        logger.info("there are {} persons in the persons list now and exactly {} in the database",
                persons.size(), personEntities.size() );
        return  persons;
    }

    public Person deletePersonDAO (int personid){
        Optional<PersonEntity>optionalPerson = personRepositoryDAO.findById(personid);
        if(optionalPerson.isPresent()){
            Person person = new Person();
            Person newPerson = convertEntityToPerson(person, optionalPerson.get());
            personRepositoryDAO.deleteById(personid);
            logger.info("person with id {} deleted", personid);
            return newPerson;
        }
        else {
            logger.info("person with id {} doesn't exist", personid);
            return null;
        }
    }

    private Person convertEntityToPerson(Person person, PersonEntity personEntity){
        person.setAge(personEntity.getAge());
        person.setVille(personEntity.getVille());
        person.setNom(personEntity.getNom());
        person.setPrenom(personEntity.getPrenom());
        person.setPersonid(personEntity.getPersonid());
        return person;
    }
}

