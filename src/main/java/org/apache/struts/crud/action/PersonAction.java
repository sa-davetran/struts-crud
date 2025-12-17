package org.apache.struts.crud.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.crud.model.Car;
import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.model.Pet;
import org.apache.struts.crud.service.CarService;
import org.apache.struts.crud.service.DefaultCarService;
import org.apache.struts.crud.service.DefaultPersonService;
import org.apache.struts.crud.service.DefaultPetService;
import org.apache.struts.crud.service.PersonService;
import org.apache.struts.crud.service.PetService;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.Preparable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Acts as a controller to handle actions related to editing a Person.
 *
 * @author bruce phillips
 * @author antonio sánchez
 */
public class PersonAction extends ActionSupport implements Preparable {

    private static final Logger LOG = LogManager.getLogger(PersonAction.class.getName());
    private PersonService personService = new DefaultPersonService();
    private CarService carService = new DefaultCarService();
    private PetService petService = new DefaultPetService();
    private Person person;
    private Person[] persons;
    private String[] sports;
    private String[] genders;
    private Country[] countries;
    private Car[] carModelsAvailable;
    private Pet[] petModelsAvailable;
    private Integer[] selectedCarIds;
    private Integer[] selectedPetIds;

    @Override
    public void prepare() throws Exception {
        carModelsAvailable = carService.getAllCars();
        petModelsAvailable = petService.getAllPets();
        sports = personService.getSports();
        countries = personService.getCountries();
        genders = personService.getGenders();
        LOG.info("Prepared support data for Person entity.");
    }

    /**
     * Prepare method specifically for the input action (editing an existing person).
     * This is called by the Preparable interceptor before the input action executes.
     */
    public void prepareInput() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String personIdParam = request.getParameter("person.personId");
        LOG.info("Preparing input data for Person with ID: " + personIdParam);

        if (personIdParam != null && !personIdParam.isEmpty()) {
            try {
                person = personService.getPerson(Integer.valueOf(personIdParam));
                LOG.info("Preparing actual data for Person: " + person);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid personId parameter: " + personIdParam);
            }
        }
    }

    /**
     * Prepare method specifically for the save action.
     * This ensures proper person loading when updating.
     */
    public void prepareSave() throws Exception {
        if (person != null && person.getPersonId() != null) {
            Person existingPerson = personService.getPerson(person.getPersonId());
            if (existingPerson != null) {
                LOG.info("Preparing to save existing Person: " + existingPerson);
            }
        }
    }

    /**
     * Prepare method specifically for the delete action.
     * This is called by the Preparable interceptor before the delete action executes.
     */
    public void prepareDelete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String personIdParam = request.getParameter("person.personId");
        LOG.info("Preparing delete for Person with ID: " + personIdParam);

        if (personIdParam != null && !personIdParam.isEmpty()) {
            try {
                person = personService.getPerson(Integer.valueOf(personIdParam));
                LOG.info("Loaded Person for deletion: " + person);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid personId parameter: " + personIdParam);
            }
        }
    }

    /**
     * Get all persons for display in the view.
     */
    public String list() {
        persons = personService.getAllPersons();
        LOG.info("Listing persons");
        return SUCCESS;
    }

    /**
     * Display the form to edit an existing person or add a new person.
     * The person data will be loaded by the prepareInput() method if personId is provided.
     */
    public String input() {
        LOG.info("Editing Person with ID: " + (person != null ? person.getPersonId() : "null"));
        return INPUT;
    }

    /**
     * Save the state of the Person object instance field.
     */
    public String save() {
        // Convert selected car IDs to Car objects
        if (selectedCarIds != null && selectedCarIds.length > 0) {
            Car[] selectedCars = new Car[selectedCarIds.length];
            for (int i = 0; i < selectedCarIds.length; i++) {
                selectedCars[i] = carService.getCar(selectedCarIds[i]);
            }
            person.setCarModels(selectedCars);
        } else {
            person.setCarModels(new Car[0]);
        }
        
        // Convert selected pet IDs to Pet objects
        if (selectedPetIds != null && selectedPetIds.length > 0) {
            Pet[] selectedPets = new Pet[selectedPetIds.length];
            for (int i = 0; i < selectedPetIds.length; i++) {
                selectedPets[i] = petService.getPet(selectedPetIds[i]);
            }
            person.setPetModels(selectedPets);
        } else {
            person.setPetModels(new Pet[0]);
        }
        
        if (person.getPersonId() == null) {
            personService.insertPerson(person);
            LOG.info("Created new Person: " + person);
        } else {
            personService.updatePerson(person);
            LOG.info("Updated Person: " + person);
        }
        return SUCCESS;
    }

    /**
     * Delete from Person identified by the person
     * instance field's personId value.
     */
    public String delete() {
        personService.deletePerson(person.getPersonId());
        LOG.info("Deleted Person: " + person);
        return SUCCESS;
    }

    public Person[] getPersons() {
        return persons;
    }

    @StrutsParameter(depth = 2)
    public Person getPerson() {
        return person;
    }

    @StrutsParameter(depth = 2)
    public void setPerson(Person person) {
        this.person = person;
    }

    public String[] getSports() {
        return sports;
    }


    public Integer[] getSelectedCarIds() {
        return selectedCarIds;
    }

    @StrutsParameter(depth = 2)
    public void setSelectedCarIds(Integer[] selectedCarIds) {
        this.selectedCarIds = selectedCarIds;
    }

    public Country[] getCountries() {
        return countries;
    }

    public Car[] getCarModelsAvailable() {
        return carModelsAvailable;
    }

    public Pet[] getPetModelsAvailable() {
        return petModelsAvailable;
    }

    public Integer[] getSelectedPetIds() {
        return selectedPetIds;
    }

    @StrutsParameter(depth = 2)
    public void setSelectedPetIds(Integer[] selectedPetIds) {
        this.selectedPetIds = selectedPetIds;
    }

    public String[] getGenders() {
        return genders;
    }
}

