package org.apache.struts.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.crud.model.Car;
import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.model.Pet;

/**
 * In memory data repository for Person objects.
 * 
 * @author bruce phillips
 * @author antonio sanchez
 */
public class MemoryPersonDao implements PersonDao {
    private static final Logger LOG = LogManager.getLogger(MemoryPersonDao.class.getName());

    private final static List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person(1, "Bruce", "Phillips", "basketball", "male", MemoryPersonSupportDao.getCountry("US"), 2007, 
            new Car[]{
                new Car(1, "Accord", "Honda", 2020, "blue"),
                new Car(2, "Civic", "Honda", 2021, "red")
            },
            new Pet[]{
                new Pet(1, "Max", "Dog",  3)
            }, 
        "bphillips@ku.edu", "123-456-9999"));
        persons.add(new Person(2, "Antonio", "Sanchez", "mtb", "male", MemoryPersonSupportDao.getCountry("ES"), 2002, 
            new Car[]{
                new Car(4, "Mustang", "Ford", 2022, "black"),
                new Car(6, "Corolla", "Toyota", 2020, "gray"),
                new Car(7, "Altima", "Nissan", 2019, "white")
            },
            new Pet[]{
                new Pet(2, "Bella", "Cat", 2),
                new Pet(3, "Charlie", "Dog", 5)
            }, 
        "asanchez@correo-e.es", "555-999-8888"));
    }

    @Override
    public Person getPerson(Integer id) {
        for (Person p : persons) {
            if (p.getPersonId().equals(id)) {
                try {
                    return (Person) p.clone();
                } catch (CloneNotSupportedException ex) {
                    LOG.error("Unexpected exception cloning Person");
                }
            }
        }
        return null;
    }

    @Override
    public Person[] getAllPersons() {
        return persons.toArray(new Person[persons.size()]);
    }

    @Override
    public void updatePerson(Person person) {
        Integer id = person.getPersonId();
        for (int i = 0; i < persons.size(); i++) {
            Person p = persons.get(i);
            if (p.getPersonId().equals(id)) {
                person.setCountry(MemoryPersonSupportDao.getCountry(person.getCountry().getCountryId()));
                persons.set(i, person);
                break;
            }
        }
    }

    @Override
    public void insertPerson(Person person) {
        int lastId = 0;
        for (Person p : persons) {
            if (p.getPersonId() > lastId) {
                lastId = p.getPersonId();
            }
        }
        person.setPersonId(lastId + 1);
        person.setCountry(MemoryPersonSupportDao.getCountry(person.getCountry().getCountryId()));
        persons.add(person);
    }

    @Override
    public void deletePerson(Integer id) {
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getPersonId().equals(id)) {
                persons.remove(i);
                break;
            }
        }
    }
}
