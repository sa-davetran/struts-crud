package org.apache.struts.crud.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.crud.model.Pet;
import org.apache.struts.crud.service.DefaultPetService;
import org.apache.struts.crud.service.PetService;
import org.apache.struts2.ActionSupport;
import org.apache.struts2.Preparable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.parameter.StrutsParameter;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Acts as a controller to handle actions related to editing a Pet.
 */
public class PetAction extends ActionSupport implements Preparable {

    private static final Logger LOG = LogManager.getLogger(PetAction.class.getName());
    private PetService petService = new DefaultPetService();
    private Pet pet;
    private Pet[] pets;
    private String[] species;
    private String[] breeds;

    @Override
    public void prepare() throws Exception {
        species = petService.getSpecies();
        breeds = petService.getBreeds();
        LOG.info("Prepared support data for Pet entity.");
    }

    /**
     * Prepare method specifically for the input action (editing an existing pet).
     */
    public void prepareInput() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String petIdParam = request.getParameter("pet.petId");
        LOG.info("Preparing input data for Pet with ID: " + petIdParam);

        if (petIdParam != null && !petIdParam.isEmpty()) {
            try {
                pet = petService.getPet(Integer.valueOf(petIdParam));
                LOG.info("Preparing actual data for Pet: " + pet);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid petId parameter: " + petIdParam);
            }
        }
    }

    /**
     * Prepare method specifically for the save action.
     */
    public void prepareSave() throws Exception {
        if (pet != null && pet.getPetId() != null) {
            Pet existingPet = petService.getPet(pet.getPetId());
            if (existingPet != null) {
                LOG.info("Preparing to save existing Pet: " + existingPet);
            }
        }
    }

    /**
     * Prepare method specifically for the delete action.
     */
    public void prepareDelete() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String petIdParam = request.getParameter("pet.petId");
        LOG.info("Preparing delete for Pet with ID: " + petIdParam);

        if (petIdParam != null && !petIdParam.isEmpty()) {
            try {
                pet = petService.getPet(Integer.valueOf(petIdParam));
                LOG.info("Loaded Pet for deletion: " + pet);
            } catch (NumberFormatException e) {
                LOG.warn("Invalid petId parameter: " + petIdParam);
            }
        }
    }

    /**
     * Get all pets for display in the view.
     */
    public String list() {
        pets = petService.getAllPets();
        LOG.info("Listing pets");
        return SUCCESS;
    }

    /**
     * Display the form to edit an existing pet or add a new pet.
     */
    public String input() {
        LOG.info("Editing Pet with ID: " + (pet != null ? pet.getPetId() : "null"));
        return INPUT;
    }

    /**
     * Save the state of the Pet object instance field.
     */
    public String save() {
        if (pet.getPetId() == null) {
            petService.insertPet(pet);
            LOG.info("Created new Pet: " + pet);
        } else {
            petService.updatePet(pet);
            LOG.info("Updated Pet: " + pet);
        }
        return SUCCESS;
    }

    /**
     * Delete from Pet identified by the pet instance field's petId value.
     */
    public String delete() {
        petService.deletePet(pet.getPetId());
        LOG.info("Deleted Pet: " + pet);
        return SUCCESS;
    }

    public Pet[] getPets() {
        return pets;
    }

    @StrutsParameter(depth = 2)
    public Pet getPet() {
        return pet;
    }

    @StrutsParameter(depth = 2)
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public String[] getSpecies() {
        return species;
    }

    public String[] getBreeds() {
        return breeds;
    }
}
