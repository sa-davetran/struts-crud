package org.apache.struts.crud.service;

import org.apache.struts.crud.model.Pet;

/**
 * Define methods a PetService must implement
 * to provide services related to a Pet class.
 */
public interface PetService {

    Pet getPet(Integer id);
    
    Pet[] getAllPets();

    void updatePet(Pet pet);
    
    void insertPet(Pet pet);
    
    void deletePet(Integer id);
    
    String[] getSpecies();
    
    String[] getBreeds();
}
